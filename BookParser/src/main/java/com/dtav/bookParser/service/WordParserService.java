package com.dtav.bookParser.service;

import java.util.List;

import com.dtav.bookParser.dataaccess.PersistanceDao;

public class WordParserService {

	PersistanceDao pd;

	public WordParserService(PersistanceDao pd) {
		this.pd = pd;
	}

	public String getFileAbsolutePath() {
		return this.pd.getFileAbsolutePath();
	}

	public long getFileLineLength() {
		return this.pd.getFileLineLength();
	}

	public String getFileName() {
		return this.pd.getFileName();
	}

	public List<String> getFileLines() {
		return this.pd.getFileLines();
	}

	public void printFileWithNumberLines() {
		this.pd.printFileWithNumberLines();
	}

	public long getFileWordCount() {
		return this.pd.getFileWordCount();
	}

	public List<String> getFileWords() {
		return this.pd.getFileWords();
	}
}
