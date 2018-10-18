package com.lkieffer.java4IBMI.printing;

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

//Java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

/**
 * This class demonstrates the conversion of an XML file to PDF using
 * JAXP (XSLT) and FOP (XSL-FO).
 */
public class XML2PDFHelper {
    
    public static boolean createPDF (String xmlPath, String xslPath, String outPath) {
    	
    	// Setup input and output files
    	File baseDir = new File(".");
        File xmlfile = new File(baseDir, xmlPath);
        File xsltfile = new File(baseDir, xslPath);
        File pdffile = new File(baseDir, outPath);

        // Configure fopFactory as desired
        final FopFactory fopFactory = FopFactory.newInstance(baseDir.toURI());

        // Configure foUserAgent as desired
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        try {
       
        	
            // Setup output
            OutputStream out = new java.io.FileOutputStream(pdffile);
            out = new java.io.BufferedOutputStream(out);
        	        	
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));

            // Set the value of a <param> in the stylesheet
            transformer.setParameter("versionParam", "2.0");

            // Setup input for XSLT transformation
            Source src = new StreamSource(xmlfile);

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            transformer.transform(src, res);
            
            out.close();
            
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        	return false;
		} catch (FOPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        	return false;
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        	return false;
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        	return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        	return false;
		} finally {
        	
        }

    	return true;
    }
    
}

