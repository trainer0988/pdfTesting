package utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class PdfReader extends PDFTextStripper {
	List<String> words = new ArrayList<String>();
	Map<String, String> pdfData = new HashMap<String, String>();

	public PdfReader() throws IOException {

	}

	/**
	 * read pdf file word by word and save in in words
	 */
	public void read(File file) throws IOException {
		PDDocument document = null;
		try {
			document = PDDocument.load(file);
			PDFTextStripper stripper = new PdfReader();
			stripper.setSortByPosition(true);
			stripper.setStartPage(1);
			stripper.setEndPage(document.getNumberOfPages());
			Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
			stripper.writeText(document, dummy);
			setKeys();

		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	public Map<String, String> getPdfData() {
		return pdfData;
	}

	/**
	 * Override the default functionality of PDFTextStripper.writeString()
	 */
	@Override
	protected void writeString(String str, List<TextPosition> textPositions) throws IOException {
		String[] wordsInStream = str.split(getWordSeparator());
		if (wordsInStream != null) {
			for (String word : wordsInStream) {
				words.add(word);
			}
		}
	}

	/**
	 * set key value in pdfData
	 */
	public void setKeys() {
		for (int i = 0; i < words.size(); i++) {
			switch (words.get(i)) {
			case "Date":
				if (words.get(i + 1).equals(":")) {
					pdfData.put("Date :",
							words.get(i + 2).concat(" ").concat(words.get(i + 3)).concat(" ").concat(words.get(i + 4)));
				}
				break;
			case "PAN":
				if (words.get(i + 1).equals("No") && (words.get(i + 2).equals(":"))) {
					pdfData.put(Keys.PANNo.toString(), words.get(i + 3));

				}
				break;
			case "Invoice":
				if (words.get(i + 1).equals("No") && (words.get(i + 2).equals(":"))) {
					pdfData.put("Invoice No :", words.get(i + 3));
				}
				break;
			case "Invoice/Payment":
				if (words.get(i + 1).equals("Date") && (words.get(i + 2).equals("&"))
						&& (words.get(i + 3).equals("Time")) && (words.get(i + 4).equals(":"))) {
					pdfData.put("Invoice/Payment Date & Time :",
							words.get(i + 5).concat(" ").concat(words.get(i + 6)).concat(" ").concat(words.get(i + 7)));
				}
				break;

			case "Invoice/Payment2":
				if (words.get(i + 1).equals("Date") && (words.get(i + 2).equals("&"))
						&& (words.get(i + 3).equals("Time")) && (words.get(i + 4).equals(":"))) {
					pdfData.put("Invoice/Payment Date & Time :",
							words.get(i + 5).concat(" ").concat(words.get(i + 6)).concat(" ").concat(words.get(i + 7)));
				}
				break;
			default:

				break;

			}

		}

	}
}
