package DataDriven.FrameworkDataDriven;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class BaseTest {

	
	public WebDriver driver;
	public Properties prop;
	
	
	@Test
	
	public void openBrowser(String bType)
	{
		if(prop==null)
		{
			prop=new Properties();
			try{
			//	FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//DataDriven//FrameworkDataDriven//ProjectConfig.properties");
			//	FileInputStream fis=new FileInputStream("C://Users//Soni//eclipse-workspace//FrameworkDataDriven//src//test//java//DataDriven//FrameworkDataDriven//ProjectConfig.properties//FrameworkDataDriven//src//test//java//DataDriven//FrameworkDataDriven//ProjectConfig.properties");
				FileInputStream fis=new FileInputStream("C:\\Users\\Soni\\eclipse-workspace\\FrameworkDataDriven\\ProjectConfig.properties");
			//	FileInputStream fis=new FileInputStream("C:\\Users\\Soni\\eclipse-workspace\\enture\\prop.properties");

				prop.load(fis);
				}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
		}
		if(driver.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",prop.getProperty("chrome_exe"));
				driver=new ChromeDriver();
		}
				
				else if (driver.equals("IE"))
				{
					System.setProperty("webdriver.InternetExplorer.driver",prop.getProperty("ie_exe"));
					driver=new InternetExplorerDriver();
					
				}
		
					
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	
	}
		
	public void navigate(String URL)
	{
		driver.get(prop.getProperty(URL));
	}
	
	
	public void click(String xpathEle)
	{
		//driver.findElement(By.xpath(xpathEle)).click();
		driver.findElement(By.xpath(prop.getProperty(xpathEle))).click();
		
		
	}
	
	public void type(String xpathEle,String data)
	{
		//driver.findElement(By.xpath(xpathEle)).sendKeys(data);
		
		driver.findElement(By.xpath(prop.getProperty(xpathEle))).sendKeys(data);
	}
	
	
	public boolean verifyTitle()
	{
		return false;
	}
	
	public boolean isElementPresent()
	{
		return false;
	}
	
	
	
	public void browserClose()
	{
		driver.close();
	}
	
}
