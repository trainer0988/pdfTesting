package testCases;

import java.io.File;
import java.io.IOException;

import utility.DatabaseReader;
import utility.Keys;
import utility.PdfKeysReader;
import utility.PdfQuestionsReader;

public class Test {
	
	public static PdfKeysReader pdfKeysReader;
	public static PdfQuestionsReader pdfQuestionsReader;
	public static  DatabaseReader databaseReader;
	
	public static void main(String[] args) throws Exception {
		
		File pdf = new File("D:\\workspace\\pdf_Testing\\pdftesting\\pdfForTesting.pdf");
		
		pdfKeysReader = new PdfKeysReader();
		pdfQuestionsReader = new PdfQuestionsReader();
		pdfKeysReader.read(pdf);
		pdfQuestionsReader.read(pdf);
		databaseReader = new DatabaseReader();
		
		String PANNo = pdfKeysReader.getDataFromPdf(Keys.PANNo);
		
		System.out.println("PANNo : "+PANNo);
		
		String Question1 = pdfQuestionsReader.getDataFromPdf(Keys.QUESTION1);
		
		System.out.println("Question1 : "+Question1);
		
		
		
	}

}
