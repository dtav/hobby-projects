package com.dtav.subtitleEditor.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FilenameUtils;

public class FileManipulation {
	static final String ROOT_DIR = "src/main/resources/";
	File fileIn;

	public FileManipulation(File fileIn) {
		this.fileIn = fileIn;
	}

	public FileManipulation(String strfileIn) {
		this.fileIn = new File(ROOT_DIR+strfileIn);
	}

	public File multiplyTextBy(int multiple) throws IOException {

		BufferedReader bfr = new BufferedReader(new FileReader(this.fileIn));
		String withoutExtension = FilenameUtils.getBaseName(this.fileIn.getAbsolutePath());
		System.out.println(withoutExtension);
		withoutExtension = ROOT_DIR+withoutExtension;
		File fileOut = new File(withoutExtension + "_multiplied-" + multiple+".srt");
		PrintWriter pw = new PrintWriter(fileOut);

		long startTime;
		long endTime;
		long durationNano;
		long durationMil;
		long durationMicro;

		String line;
		for (int i = 1; i <= multiple; i++) {
			startTime = System.nanoTime();
			bfr = new BufferedReader(new FileReader(this.fileIn));
			while ((line = bfr.readLine()) != null) {
				pw.println(line);

			}
			endTime = System.nanoTime();
			durationNano = endTime - startTime;
			durationMicro = durationNano / 1000;
			durationMil = durationNano / 1000000;
			//System.out.println("Iteration "+i+" done in "+durationMicro+"µ");
		}
		// System.out.println("multiplyTextBy "+ multiple +" is done.");
		bfr.close();
		pw.close();

		long inSize = this.fileIn.length();
		long outSize = fileOut.length();
		// System.out.println("Input size: "+inSize);
		// System.out.println("Output size: "+outSize);
		if ((inSize * multiple >= outSize - 100) && (inSize * multiple <= outSize + 100)) {
			// System.out.println("multiplication went well! ");
		} else {
			System.out.println("Check file sizes!!!");
		}
		return fileOut;

	}
}
