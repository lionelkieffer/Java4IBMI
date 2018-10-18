package com.lkieffer.java4IBMI.printing;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

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


/**
 * Unit test for simple App.
 */
public class XML2PDFHelperTest {

	/**
	 * Some nearly rigorous tests :-)
	 */
	@Test
	public void testXML2PDFHelper() {

		// Paths to test files.
		String xmlfilePath = "resources/xml/XML2PDFHelperTest.xml";
		String xsltfilePath = "resources/xsl/XML2PDFHelperTest.xsl";
		String pdffilePath = "./XML2PDFHelperTest.pdf";
		
		Path pdfFile = Paths.get(pdffilePath);
		try {
			Files.deleteIfExists(pdfFile);
		} catch (IOException e) {
			// Not a problem if file doesn't exists.
		}
		
	
		// Check when everything is fine.
		boolean case1 = XML2PDFHelper.createPDF(xmlfilePath, xsltfilePath, pdffilePath);
		assertTrue(case1);

	}
}
