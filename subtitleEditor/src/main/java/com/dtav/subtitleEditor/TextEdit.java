package com.dtav.subtitleEditor;

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

	public static boolean checkIfLineEndsWithFullStop(String line) {
		// System.out.println("-----f.stop-----");
		if (line.endsWith(".")) {
			// System.out.println("FULL STOP");
			// System.out.println("-----f.stop-----");
			return true;
		} else {
			// System.out.println("NO FULL STOP");
			// System.out.println("-----f.stop-----");
			return false;
		}

	}
}
