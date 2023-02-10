package testCases;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.DatabaseReader;
import utility.Keys;
import utility.PdfReader;

public class PdfTest {
	
	protected PdfReader pdfReader;
	protected DatabaseReader databaseReader;
	
	@BeforeClass
	public void SetUp() throws Exception
	{
		File pdf = new File("D:\\workspace\\pdf_Testing\\pdftesting\\pdfForTesting.pdf");
		pdfReader = new PdfReader();
		pdfReader.read(pdf);
		databaseReader = new DatabaseReader();
		
		
		
	}
	
	@Test
	public void verfyPanNo()
	{
		String panNoINPdf = pdfReader.getPdfData().get(Keys.PANNo);
		
		String panNoInDB = databaseReader.getValueFromDB(Keys.PANNo);
		
		Assert.assertEquals(panNoINPdf, panNoInDB);;
		
		
		
	}

}
