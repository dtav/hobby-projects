package com.dtav.subtitleEditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class SubtitleEditor {
	static final String ROOT_DIR = "src/main/resources/";

	File fileIn;
		
	public SubtitleEditor(String in){
		this.fileIn = new File(ROOT_DIR+in);		
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


	public File normalize(Boolean withDebug) throws IOException {
		BufferedReader bfr = new BufferedReader(new FileReader(fileIn));
		String withoutExtension = FilenameUtils.getBaseName(this.fileIn.getAbsolutePath());
		System.out.println(withoutExtension);
		withoutExtension = ROOT_DIR+withoutExtension;
		File fileOut = new File(withoutExtension + "_normalized.srt");
		String line;
		PrintWriter pw = new PrintWriter(new FileWriter(fileOut));

		boolean textBefore = false;
		boolean fullStopBefore = false;
		

		if (!withDebug) {
			while ((line = bfr.readLine()) != null) {
				
				if (line.length() >= 3 && !line.contains("-->")) {

					line = line.toLowerCase();
					
					line = StringUtils.capitalize(line);
				
					if (!fullStopBefore && textBefore) {
						line = StringUtils.uncapitalize(line);
						
					}

					if (TextEdit.checkIfLineEndsWithFullStop(line)) {
						fullStopBefore = true;
					} else {
						fullStopBefore = false;
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

					if (!fullStopBefore && textBefore) {
						line = StringUtils.uncapitalize(line);
						 System.out.println("UNCAPIT:\t"+line);
					}

					if (TextEdit.checkIfLineEndsWithFullStop(line)) {
						fullStopBefore = true;
					} else {
						fullStopBefore = false;
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

			}
		}

		pw.close();
		bfr.close();

		System.out.println("End...");
		
		return fileOut;

	}
}
