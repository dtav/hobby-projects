package com.dtav.bookParser.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Stream;

public class HashMapServices {

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		Map<K, V> result = new LinkedHashMap<>();
		Stream<Map.Entry<K, V>> st = map.entrySet().stream();

		st.sorted(Map.Entry.comparingByValue()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));

		return result;
	}

	public static void printHashMap(HashMap<String, Integer> hm) {
		for (Map.Entry<String, Integer> entry : hm.entrySet()) {
			System.out.println(entry.getKey() + " <|> " + entry.getValue());
		}
	}

	public static void printMostOccuringWordInHashMap(HashMap<String, Integer> hm) {

		Integer max = 1;
		String mostFrequentString = "abc";
		for (Map.Entry<String, Integer> entry : hm.entrySet()) {
			// System.out.println("processing word: "+iteration);
			Integer count = entry.getValue();
			if (count > max) {
				max = count;
				mostFrequentString = entry.getKey();
			}
		}

		System.out.println(mostFrequentString + " <|> " + max);

	}

	public static HashMap<String, Integer> convertListToHashMap(List<String> list) {
		HashMap<String, Integer> wordsMap = new HashMap<String, Integer>();
		ListIterator<String> iterateWords = list.listIterator();

		while (iterateWords.hasNext()) {
			String word = iterateWords.next();
			if (wordsMap.containsKey(word)) {
				Integer count = wordsMap.get(word);
				count++;
				wordsMap.replace(word, count);
			} else {
				wordsMap.put(word, 1);
			}
		}
		return wordsMap;

	}

	public static List<Integer> getListOfValuesFromHashMap(HashMap<String, Integer> map) {
		List<Integer> ret = new ArrayList<Integer>();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			ret.add(entry.getValue());
		}

		return ret;
	}

	public static HashMap<String, Integer> getTopFiveWordsFromSorted(HashMap<String, Integer> map) {
		HashMap<String, Integer> ret = new HashMap<String, Integer>();
		List<Integer> values = getListOfValuesFromHashMap(map);
		
		for (int i = 1; i <= 5; i++) {
			Integer val = values.get(values.size() - i);
			String key = getKeyFromValue(map, val);
			ret.put(key, val);
		}
		return ret;
	}
	
	public static String getKeyFromValue(HashMap<String, Integer> map, Integer val){
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (val == entry.getValue())
				return entry.getKey();
		}
		return null;
	}
	
}
