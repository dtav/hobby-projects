package com.dtav.bookParser.dataaccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang3.StringUtils;

public class FileAccessDao implements PersistanceDao {
	File file;

	public FileAccessDao(File file) {
		this.file = file;
	}

	public String getFileAbsolutePath() {
		return this.file.getAbsolutePath();
	}

	public long getFileLineLength() {
		return this.file.length();
	}

	public String getFileName() {
		return this.file.getName();
	}

	public List<String> getFileLines() {
		List<String> linesList = new ArrayList<String>();
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e1) {
			System.out.println("File is not found! ");
			e1.printStackTrace();
		}

		BufferedReader bfr = new BufferedReader(fr);
		try {
			while (bfr.readLine() != null) {
				linesList.add(bfr.readLine());
			}
		} catch (IOException e) {
			System.out.println("Problem in reading file - bufferedReader");
			e.printStackTrace();
		}

		return linesList;
	}

	public void printFileWithNumberLines() {
		List<String> linesList = getFileLines();
		ListIterator<String> iterateLines = linesList.listIterator();
		int lineNumber = 1;
		StringBuilder sb = new StringBuilder();

		while (iterateLines.hasNext()) {
			String output = lineNumber + ". " + iterateLines.next();
			sb.append(output + "\n");
			lineNumber++;
		}

		System.out.println(sb.toString());

	}

	public long getFileWordCount() {
		return getFileWords().size();
	}

	public List<String> getFileWords() {
		List<String> wordsList = new ArrayList<String>();
		FileReader fr = null;
		int progress = 1;
		String temp;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e1) {
			System.out.println("File is not found! ");
			e1.printStackTrace();
		}

		BufferedReader bfr = new BufferedReader(fr);
		try {
			while ((temp = bfr.readLine()) != null) {
				String[] wordsInLine = temp.split(" ");
				for (int i = 0; i < wordsInLine.length; i++) {
					String word = wordsInLine[i];
					String resultString = word.replaceAll("[^\\p{L}\\p{Nd}]+", "");
					resultString = resultString.toLowerCase();
					if (!StringUtils.isBlank(resultString)) {
						
						//System.out.println(word);
						wordsList.add(resultString);
						//System.out.println("word number: " + progress);
						progress++;
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Problem in reading file - bufferedReader");
			e.printStackTrace();
		}

		return wordsList;
	}

}
