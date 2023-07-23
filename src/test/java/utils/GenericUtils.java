package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GenericUtils {
	
	public String getPropertyValue(String propertyFileName, String key) {
		String returnValue=null;
		FileInputStream fis1;
		try 
		{
			fis1 = new FileInputStream("src/test/resources/env/"+propertyFileName+".properties");
			Properties prop = new Properties();
			prop.load(fis1);
			returnValue = prop.getProperty(key);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
		
		return returnValue;
	}
}









