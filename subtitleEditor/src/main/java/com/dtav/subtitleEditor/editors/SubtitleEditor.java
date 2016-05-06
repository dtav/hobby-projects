package com.dtav.subtitleEditor.editors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.dtav.subtitleEditor.domain.WordList;

public class SubtitleEditor {
	static final String ROOT_DIR = "src/main/resources/";

	File fileIn;
	WordList wordList;

	public SubtitleEditor(String in) {
		this.fileIn = new File(ROOT_DIR + in);
	}

	public SubtitleEditor(String in, String wordListFile) {
		this.fileIn = new File(ROOT_DIR + in);
		wordList = new WordList(new File(ROOT_DIR + wordListFile));
		try {
			wordList.populate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SubtitleEditor(File fileIn) {

		this.fileIn = fileIn;

	}

	public File getFileIn() {
		return fileIn;
	}

	public void setFileIn(File fileIn) {
		this.fileIn = fileIn;
	}

	public WordList getWordList() {
		return wordList;
	}

	public void setWordList(WordList wordList) {
		this.wordList = wordList;
		try {
			wordList.populate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public File normalize(Boolean withDebug) throws IOException, InterruptedException {

		BufferedReader bfr = new BufferedReader(new FileReader(fileIn));
		String withoutExtension = FilenameUtils.getBaseName(this.fileIn.getAbsolutePath());
		System.out.println(withoutExtension);
		withoutExtension = ROOT_DIR + withoutExtension;
		File fileOut = new File(withoutExtension + "_normalized.srt");
		String line;
		PrintWriter pw = new PrintWriter(new FileWriter(fileOut));

		boolean textBefore = false;
		boolean punctBefore = false;

		if (!withDebug) {
			while ((line = bfr.readLine()) != null) {

				if (line.length() >= 3 && !line.contains("-->")) {

					line = line.toLowerCase();

					line = StringUtils.capitalize(line);

					if (!punctBefore && textBefore) {
						line = StringUtils.uncapitalize(line);

					}

					// int index = TextEdit.checkIfLineContainsI(line);
					line = line.replace(" i ", " I ");

					String[] words = line.split(" ");
					for (String s : words) {
						System.out.println("-----WORDS-----");
						System.out.print(s + " -->");
						if (wordList.contains(s)) {
							System.out.println(" IS WORD ");
						} else {
							System.out.println(" IS NOT WORD ");
						}
						System.out.println("-----WORDS-----");
					}

					if (TextEdit.checkIfLineEndsWithEndPunctuation(line)) {
						punctBefore = true;
					} else {
						punctBefore = false;
					}

					if (TextEdit.checkIfLineEndsWithLetters(line)) {
						textBefore = true;
					} else {
						textBefore = false;
					}

				}
				pw.println(line);

			}
		} else {
			int place = 1;
			while ((line = bfr.readLine()) != null) {
				System.out.println("line: " + place);
				System.out.println("NORMAL:\t" + line);
				if (line.length() >= 3 && !line.contains("-->")) {

					line = line.toLowerCase();
					System.out.println("LOWER:\t" + line);
					line = StringUtils.capitalize(line);
					System.out.println("CAPIT:\t" + line);

					if (!punctBefore && textBefore) {
						line = StringUtils.uncapitalize(line);
						System.out.println("UNCAPIT:\t" + line);
					}

					// int index = TextEdit.checkIfLineContainsI(line);
					line = line.replace(" i ", " I ");
					line = line.replace("i'", "I'");
					line = line.replace("i ", "I ");
					System.out.println("i > I:\t" + line);

					System.out.println("-----WORDS-----");

					String[] words = line.split(" ");
					String recreatedLine = "";
					String recreatedLine2 = "";
					for (String s : words) {
						String sOrig = new String(s);
						s = s.replaceAll("[^A-Za-z]+", "");
						s = s.toLowerCase();

						System.out.print(s + " -->");
						if (wordList.contains(s)) {
							System.out.println(" IS WORD ");

							recreatedLine = recreatedLine + " " + sOrig;
						} else {
							System.out.println(" IS NOT WORD ");
							sOrig = StringUtils.capitalize(sOrig);
							recreatedLine = recreatedLine + " " + sOrig;
							
						}

					}
					recreatedLine2 = recreatedLine.replaceAll("^\\s+", "");
					System.out.println("RECR:\t|" + recreatedLine2 + "|");
					System.out.println("-----WORDS-----");

					if (TextEdit.checkIfLineEndsWithEndPunctuation(line)) {
						punctBefore = true;
					} else {
						punctBefore = false;
					}

					if (TextEdit.checkIfLineEndsWithLetters(line)) {
						textBefore = true;
					} else {
						textBefore = false;
					}

				}
				pw.println(line);
				place++;
				System.out.println();

				Thread.sleep(60);
			}
		}

		pw.close();
		bfr.close();

		System.out.println("End...");

		return fileOut;

	}
}
