package testCases;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.DatabaseReader;
import utility.Keys;
import utility.PdfKeysReader;


public class PdfTest {

	protected PdfKeysReader pdfKeysReader;
	protected DatabaseReader databaseReader;

	@BeforeClass
	public void SetUp() throws Exception {
		File pdf = new File("D:\\workspace\\pdf_Testing\\pdftesting\\pdfForTesting.pdf");
		pdfKeysReader = new PdfKeysReader();
		pdfKeysReader.read(pdf);
		databaseReader = new DatabaseReader();

	}

	@Test
	public void verfyPanNo() {
		String panNoINPdf = pdfKeysReader.getDataFromPdf(Keys.PANNo);
		System.out.println(panNoINPdf);

		String panNoInDB = databaseReader.getValueFromDB(Keys.PANNo);
		System.out.println(panNoInDB);

		Assert.assertEquals(panNoINPdf, panNoInDB);

	}

}
