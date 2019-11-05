package DataDriven.Framework.TestCases;

import org.testng.annotations.Test;

import DataDriven.FrameworkDataDriven.BaseTest;

public class Action extends BaseTest{

	@Test
	public void testOFaction()
	{	
	openBrowser("chrome");
	navigate("appurl");
	//type("Input_xpath","soni");
	click("Btn_id");
	isElementPresent("Btn_id");
	verifyTitle();
	reportFail("Report Failure method");
	random_uniqueno();
	
	browserClose();
	
	
	}
	
	
}
