package com.dtav.subtitleEditor;

import java.io.IOException;

import com.dtav.subtitleEditor.editors.SubtitleEditor;

public class SimpleApplication {
	static final String ROOT_DIR = "src/main/resources/";

	public static String buildFullFilePath(String str){
		return ROOT_DIR+str;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		String subName = "subtitle1.srt";
		String listFile = "wordsDict.txt";
		
		
//		//normalizing standard
//		SubtitleEditor subEditor = new SubtitleEditor(subName);
//		long startTime = System.nanoTime();
//		subEditor.normalize(false);
//		long endTime = System.nanoTime();
//		
//		long durationMil = (endTime - startTime) / 1000000;
//		System.out.println("NORMALIZE: " + durationMil+"ms");
//		
//		//multiplying standard		
//		startTime = System.nanoTime();
//		FileManipulation fileManipulator = new FileManipulation(subName);
//		File manipulated = fileManipulator.multiplyTextBy(1000);
//		endTime = System.nanoTime();
//		
//		durationMil = (endTime - startTime) / 1000000;
//		System.out.println("MULTIPLY: " + durationMil+"ms");
//		
//		//normalize multiplied 1000
//		subEditor = new SubtitleEditor(manipulated.getName());
//		startTime = System.nanoTime();
//		subEditor.normalize(false);
//		endTime = System.nanoTime();
//		
//		durationMil = (endTime - startTime) / 1000000;
//		System.out.println("NORMALIZE: " + durationMil+"ms");
//		
		
		
		SubtitleEditor se = new SubtitleEditor(subName, listFile);
		System.out.println(se.getWordList().getWordList().size());
		se.normalize(true);
	}

}
