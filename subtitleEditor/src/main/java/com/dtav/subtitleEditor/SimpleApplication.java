package com.dtav.subtitleEditor;

import java.io.File;
import java.io.IOException;

public class SimpleApplication {
	static final String ROOT_DIR = "src/main/resources/";

	public static String buildFullFilePath(String str){
		return ROOT_DIR+str;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		String subName = "subtitle1.srt";
		String fullSubName = buildFullFilePath(subName);
		
		
		//normalizing standard
		SubtitleEditor subEditor = new SubtitleEditor(fullSubName, fullSubName+"_normalized.srt");
		long startTime = System.nanoTime();
		subEditor.normalize(false);
		long endTime = System.nanoTime();
		
		long durationMil = (endTime - startTime) / 1000000;
		System.out.println("NORMALIZE: " + durationMil+"ms");
		
		//multiplying standard		
		startTime = System.nanoTime();
		FileManipulation fileManipulator = new FileManipulation(fullSubName);
		File manipulated = fileManipulator.multiplyTextBy(1000);
		endTime = System.nanoTime();
		
		durationMil = (endTime - startTime) / 1000000;
		System.out.println("MULTIPLY: " + durationMil+"ms");
		
		//normalize multiplied 1000
		subEditor = new SubtitleEditor(manipulated.getAbsolutePath(), manipulated.getAbsolutePath()+"_normalized.srt");
		startTime = System.nanoTime();
		subEditor.normalize(false);
		endTime = System.nanoTime();
		
		durationMil = (endTime - startTime) / 1000000;
		System.out.println("NORMALIZE: " + durationMil+"ms");
		
		
	}

}