package utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class PdfQuestionsReader extends PDFTextStripper {

	static List<String> lines = new ArrayList<String>();
	static Map<Object, String> pdfData = new HashMap<Object, String>();

	public PdfQuestionsReader() throws IOException {
	}

	/**
	 * @throws IOException If there is an error parsing the document.
	 */
	public void read(File file) throws IOException {
		PDDocument document = null;
		try {
			document = PDDocument.load(file);
			PDFTextStripper stripper = new PdfQuestionsReader();
			stripper.setSortByPosition(true);
			stripper.setStartPage(0);
			stripper.setEndPage(document.getNumberOfPages());

			Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
			stripper.writeText(document, dummy);

			// print lines
			
			/*
			 * for (String line : lines)
			 * 
			 * { System.out.println(line);
			 * 
			 * }
			 */
			 
			setKeys();
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}
	
public String getDataFromPdf(String key) {
		
	System.out.println(pdfData);
		return pdfData.get(key);
		
	}

	/**
	 * Override the default functionality of PDFTextStripper.writeString()
	 */
	@Override
	protected void writeString(String str, List<TextPosition> textPositions) throws IOException {
		lines.add(str);
		// you may process the line here itself, as and when it is obtained
	}

	public static void setKeys() throws FileNotFoundException, IOException {

		for (int i = 0; i < lines.size(); i++) {
			
			System.out.println("Line number : " +i + " value : "+lines.get(i));

			switch (lines.get(i)) {

			case Keys.QUESTION1:
				pdfData.put(Keys.QUESTION1, lines.get(i + 1));
				break;
				
			case Keys.QUESTION2:
				if(lines.get(i-1).trim().equals(Keys.line81))
				{
				pdfData.put(Keys.QUESTION2, lines.get(i+1));
				}
				break;
			}

		}

	}

}