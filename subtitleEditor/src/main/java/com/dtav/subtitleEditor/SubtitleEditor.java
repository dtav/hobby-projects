package com.dtav.subtitleEditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang3.StringUtils;

public class SubtitleEditor {
	File fileIn;
	File fileOut;
	
	public SubtitleEditor(String in, String out){
		this.fileIn = new File(in);
		this.fileOut = new File(out);
	}

	public SubtitleEditor(File fileIn, File fileOut) {
		super();
		this.fileIn = fileIn;
		this.fileOut = fileOut;
	}
	
	public File getFileIn() {
		return fileIn;
	}

	public void setFileIn(File fileIn) {
		this.fileIn = fileIn;
	}

	public File getFileOut() {
		return fileOut;
	}

	public void setFileOut(File fileOut) {
		this.fileOut = fileOut;
	}

	public void normalize(Boolean withDebug) throws IOException {
		BufferedReader bfr = new BufferedReader(new FileReader(fileIn));
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

	}
}
