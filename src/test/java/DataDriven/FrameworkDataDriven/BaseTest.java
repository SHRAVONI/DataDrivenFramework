package DataDriven.FrameworkDataDriven;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;



public class BaseTest {

	
	public WebDriver driver;
	public Properties prop=null;
    public static ExtentReports extent=ExtentReports.get(BaseTest.class);
    SoftAssert softassert= new SoftAssert();
    

	
	public void openBrowser(String bType)
	{
		extent.init("C:\\Users\\Soni\\eclipse-workspace\\FrameworkDataDriven\\ReportsExtent\\reportext.html",true);
		extent.config().documentTitle("Automation test Report------Created by @Shravoni das");
        extent.config().reportHeadline(" Report History");
        extent.startTest(".......Browser Launched.......");
		if(prop==null)
		{
			prop=new Properties();
			try{
				FileInputStream fis=new FileInputStream("C:\\Users\\Soni\\eclipse-workspace\\FrameworkDataDriven\\ProjectConfig.properties");
				
		//	FileInputStream fis=new FileInputStream("C:\\Users\\Soni\\eclipse-workspace\\SampleProject\\prop.properties");
				prop.load(fis);
				}
			catch(Exception e){	e.printStackTrace();}
		
        
		}
        
		if(bType.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",prop.getProperty("chrome_exe"));
				driver=new ChromeDriver();
		}
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	driver.manage().window().maximize();
	extent.log(LogStatus.INFO,"Open Browser() is completed.")	;
	extent.attachScreenshot("C:\\Users\\Soni\\Pictures\\Camera Roll\\WIN_20190831_14_33_51_Pro.jpg");
	extent.endTest();
	}
	
	
	public void navigate(String URL)
	{
		extent.startTest(".......navigate to URL.......");
		try {
			
		driver.get(prop.getProperty(URL));
		
		extent.log(LogStatus.INFO,"navigate() is completed.")	;
		extent.attachScreenshot("C:\\Users\\Soni\\Pictures\\Camera Roll\\WIN_20190831_14_33_51_Pro.jpg");
		extent.endTest();
		}
		catch(Exception e)
		{
			extent.log(LogStatus.FAIL, "URL issue","issue"+e.getStackTrace());
		}
		takeScreenshot();
	}
	
	
	public void click(String locatorKey)
	{
		//driver.findElement(By.xpath(xpathEle)).click();
		getElement(locatorKey).click();
		
	}
	
	public void type(String xpathEle,String data)
	{
		//driver.findElement(By.xpath(xpathEle)).sendKeys(data);
		
		driver.findElement(By.xpath(prop.getProperty(xpathEle))).sendKeys(data);
		driver.findElement(By.xpath(prop.getProperty(xpathEle))).sendKeys(Keys.ENTER);
	//	driver.findElement(By.id("Value")).sendKeys(Keys.RETURN);
	}
	
	public WebElement getElement(String locatorKey)
	{
		
		if(!isElementPresent("locatorKey"))
			reportFail("Locator not found");
		
		softassert.assertTrue(VerifyText("Btn_id","Btn_Text"),"Error-> Verify Text () Failed");
				
		WebElement e=null;
		try {
		if(locatorKey.endsWith("_id"))
		e=driver.findElement(By.id(prop.getProperty(locatorKey)));
		
		else if(locatorKey.endsWith("_name"))
			e=driver.findElement(By.name(prop.getProperty(locatorKey)));
			
			
			else if(locatorKey.endsWith("_xpath"))
				e=driver.findElement(By.xpath(prop.getProperty(locatorKey)));
				
			else
			{
				reportFail("Locator Not Found "+locatorKey);
			}
		//return e;
		}
		catch(Exception ex)
		{
			reportFail(ex.getMessage());
			ex.printStackTrace();
			Assert.fail("Test open browser failed "+ex.getMessage());
			extent.log(LogStatus.FAIL, "Check Browser Parameter","Browser Not Getting Launch"+ex.getMessage() );
			takeScreenshot();
		}
		return e;
		
				
				
	}
	
	public boolean verifyTitle()
	{
		return false;
	}
	
	public boolean isElementPresent(String locatorKey)
	{
		extent.startTest(".......Element Present.......");
		List <WebElement> elementList=null;
		
		if(locatorKey.endsWith("_id"))
			elementList=driver.findElements(By.id(prop.getProperty(locatorKey)));
			
			else if(locatorKey.endsWith("_name"))
				elementList=driver.findElements(By.name(prop.getProperty(locatorKey)));
				
				
				else if(locatorKey.endsWith("_xpath"))
					elementList=driver.findElements(By.xpath(prop.getProperty(locatorKey)));
					
				else
				{
					reportFail("Element Not Found "+locatorKey);
				}
				if(elementList.size()==0)
					return false;
				
				else
					extent.log(LogStatus.PASS,"Element Present() is completed.")	;
				extent.attachScreenshot("C:\\Users\\Soni\\Pictures\\Camera Roll\\WIN_20190831_14_33_51_Pro.jpg");
				extent.endTest();
					return true;		
	}
	
	
	public boolean VerifyText(String LocatorKey,String ExpectedTextKey)
	{
		String ActualText=getElement(LocatorKey).getText().trim();
		String ExpectedText=prop.getProperty(ExpectedTextKey);
		
		if (ActualText.equals(ExpectedTextKey))
			return true;
		else	
		return false;
		
	}
	
	
	
	
	public void reportFail(String msg)   // This is for Critical ERROR else to use Softassertion
	{
		extent.log(LogStatus.FAIL,"Failed");
		takeScreenshot();
		Assert.fail();

	}
	public void reportPass(String msg)
	{
		extent.log(LogStatus.PASS,"Passed");
		//takeScreenshot();
		
	}
	public void takeScreenshot()
	{
		Date d =new Date();
		String screenshotfile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			
			FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"ScreenShot"+screenshotfile));
		//	FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")\\"ScreenShot"+screenshotfile));
			extent.log(LogStatus.INFO, "Screenshot Captured","File "+screenshotfile );		
		}
		
		catch(Exception e)
		{
			e.printStackTrace();	
		}
	}
	
	public void random_uniqueno()
	{
		 UUID uuid = UUID.randomUUID();
		 System.out.println("Unique ID "+uuid);
	}
	
	
	
	public void browserClose()
	{
		try {
			softassert.assertAll();
		}
		catch(Error e)
		{
			extent.log(LogStatus.FAIL,"SoftAssertion Failure" );
		
		
		}
		
		
		driver.close();
		
	}
	
}
