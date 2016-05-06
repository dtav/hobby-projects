package com.dtav.subtitleEditor.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class WordList {
	File source;
	List<String> wordList;

	public WordList(File source) {
		this.source = source;
	}

	public void populate() throws IOException {
		wordList = new ArrayList<String>();
		BufferedReader bfr = new BufferedReader(new FileReader(this.source));
		String word = "";
		while ((word = bfr.readLine()) != null) {
			word = word.trim();
			
			wordList.add(word);
		}
		bfr.close();
	}

	public boolean isPopulated() {
		return !this.wordList.isEmpty();
	}
	
	public boolean contains(String word){
		return wordList.contains(word);
	}

	public File getSource() {
		return source;
	}

	public void setSource(File source) {
		this.source = source;
	}

	public List<String> getWordList() {
		return wordList;
	}

	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}

}
