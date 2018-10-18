package com.lkieffer.java4IBMI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.lkieffer.java4IBMI.printing.XML2PDFHelper;

/*
 * Created on : Oct. 2018
 * Author     : Lionel KIEFFER
 *
 *-----------------------------------------------------------------------------
 * Change history 
 *-----------------------------------------------------------------------------
 * Date       |  Author           | Change
 *------------|-------------------|--------------------------------------------
 * 2018-10-15 | Lionel KIEFFER    | Creation                       
 *            |                   | 
 *------------|-------------------|--------------------------------------------
 */

public class TryJava4IMBI {

	public static void main(String[] args) {

		// Paths to test files.
		String xmlfilePath = args[0];
		String xsltfilePath = args[1];
		String pdffilePath = args[2];

		Path pdfFile = Paths.get(pdffilePath);
		try {
			Files.deleteIfExists(pdfFile);
		} catch (IOException e) {
			// Not a problem if file doesn't exists.
		}

		// Check when everything is fine.
		boolean run = XML2PDFHelper.createPDF(xmlfilePath, xsltfilePath, pdffilePath);
		if (run == true) {
			System.out.println("PDF created");
		} else {
			System.out.println("Oops got some issue here Oo");
		}
		System.out.println("That's all folks ! Cya");

	}

}
