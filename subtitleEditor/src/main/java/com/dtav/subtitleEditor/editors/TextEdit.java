package com.dtav.subtitleEditor.editors;

import java.util.regex.Pattern;

public class TextEdit {
	
	public static boolean checkIfLineEndsWithLetters(String line) {
		// System.out.println("-------ending---------");
		String ending = line.substring(line.length() - 2, line.length() - 1);
		// System.out.println("\t\t " + ending);
		boolean isAlpha = (ending.matches("[a-zA-Z]") || Pattern.matches("\\p{Punct}", ending));
		// System.out.println("\t\t " + isAlpha);
		// System.out.println("-------ending---------");
		return isAlpha;
	}

	public static boolean checkIfLineEndsWithEndPunctuation(String line) {
		// System.out.println("-----f.stop-----");
		if (line.endsWith(".") || line.endsWith("?") || line.endsWith("!")) {
			// System.out.println("FULL STOP");
			// System.out.println("-----f.stop-----");
			return true;
		} else {
			// System.out.println("NO FULL STOP");
			// System.out.println("-----f.stop-----");
			return false;
		}

	}
	
	//String.replace(string, string)
	public static int checkIfLineContainsI(String line){
		System.out.println("--------I--------");
		if (line.contains(" i ")){
			System.out.println("CONTAINS i");
			
		}
		System.out.println("--------I---*-*-*");
		return line.indexOf(" i ");
		
	}
}
