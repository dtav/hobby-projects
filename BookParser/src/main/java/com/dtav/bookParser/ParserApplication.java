package com.dtav.bookParser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.dtav.bookParser.dataaccess.FileAccessDao;
import com.dtav.bookParser.service.HashMapServices;
import com.dtav.bookParser.service.WordParserService;

public class ParserApplication {

	public static void main(String[] args) throws IOException {

		File bookFile = new File("src/main/resources/ATaleOfTwoCities.txt");
		WordParserService wps = new WordParserService(new FileAccessDao(bookFile));

		List<String> wordsInFile = wps.getFileWords();

		HashMap<String, Integer> wordsMap = HashMapServices.convertListToHashMap(wordsInFile);
		//printMostOccuringWordInHashMap(wordsMap);

		HashMap<String, Integer> sortedWordsMap = (HashMap<String, Integer>) HashMapServices.sortByValue(wordsMap);

		HashMapServices.printHashMap(sortedWordsMap);
		
		//printHashMap(getTopFiveWordsFromSorted(sortedWordsMap));

	}

	

}
