package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseReader {
	
	Properties valueInDb;
	public DatabaseReader() throws Exception
	{
	
	FileInputStream fileInputStream = new FileInputStream("D:\\workspace\\pdf_Testing\\pdftesting\\src\\main\\resources\\DB_data.properties");
	valueInDb = new Properties();
	valueInDb.load(fileInputStream);
	
	}
	
	
	public String getValueFromDB(Object value)
	{
		return  valueInDb.getProperty(value.toString());
	}
	

}
