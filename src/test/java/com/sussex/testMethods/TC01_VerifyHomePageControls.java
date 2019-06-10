package com.sussex.testMethods;

import static org.testng.Assert.assertTrue;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.sussex.pageObjects.HomePage;
import com.sussex.testBase.TestBase;


/**
 * Test Script for Home Page Controls Verification
 * @author rameshk
 *
 */
public class TC01_VerifyHomePageControls extends TestBase{
	
	HomePage homePage;
//	SerpPage serpPage;
	public TC01_VerifyHomePageControls() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@BeforeSuite
	public void setUp() throws InterruptedException {
		try {
			// Creating an instance for Browser, Navigating to Url & Maximize the browser
			initialize();
			// Start Reporting - Extent Report
			test = extent.startTest("Alpharooms Test",
					"This test will verify Flight Destination search results");
			/*test.log(LogStatus.PASS, "Browser has invoked successfully !");
			report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReports.html");*/
			
			homePage = new HomePage(driver);
//			serpPage = new SerpPage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(description="This test is to verify the Home Page logo")
	public void TS01_verifyHomePageLogo(){
		 
		try {
			test.log(LogStatus.PASS, "------TC01_Verify Home Page Logo--------");
			test.log(LogStatus.PASS, "Verify the Home Page Title - " + homePage.getTitle());
			Assert.assertEquals(homePage.getTitle(), "Teletext Holidays | Book Your Perfect Holiday from £49 Deposit today!");
			
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
	
	@Test(description="This test is to verify the super low desposit text message")
	public void TS02_verifySuperLow(){
		 try {
			   test.log(LogStatus.PASS, "------TC02_Verify super low deposit text message --------");
				test.log(LogStatus.PASS, "Verify the Home Page Title - " + homePage.getTitle());
				Assert.assertEquals(homePage.getTitle(), "Teletext Holidays | Book Your Perfect Holiday from £49 Deposit today!");
				
				
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}
	@AfterSuite
	public void closeBrowser() {
		try {
			Thread.sleep(1000);
			driver.close();
			extent.endTest(test);
			extent.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
