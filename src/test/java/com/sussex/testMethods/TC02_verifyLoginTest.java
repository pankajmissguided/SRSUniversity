 package com.sussex.testMethods;

import com.sussex.pageObjects.LoginPage;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.sussex.pageObjects.HomePage;
import com.sussex.pageObjects.RegistrationPage;
import com.sussex.testBase.TestBase;

public class TC02_verifyLoginTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	RegistrationPage registrationPage;

	// SerpPage serpPage;
	public TC02_verifyLoginTest() {

		super();
	}

	@DataProvider(name = "loginwithvalid")
	public String[][] getSearchData() {
		String[][] testdata = getData("TestData.xlsx", "loginData");
		return testdata;
	}

	@DataProvider(name = "UILoginValidation")
	public String[][] UILoginValidation() {
		String[][] testdata = getData("TestData.xlsx", "LoginPageUI");
		return testdata;
	}

	@BeforeSuite
	public void setUp() throws InterruptedException {
		try {
			initialize();
			extent = new ExtentReports(System.getProperty("user.dir") + "\\Extentextents.html");
			test = extent.startTest("TC02_verifyLoginTest");
			loginPage = new LoginPage(driver);
			registrationPage = new RegistrationPage(driver);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(enabled = true, priority = 0, dataProvider = "loginwithvalid")
	public void VerifyLoginTest(String loginPageTitle, String username, String password, String homePageTitle
			) throws InterruptedException {
		
		try {
			test.log(LogStatus.PASS, "***************TC001 Verify Login with valid Login Details****************");

			// LoginPage loginPage = new LoginPage(driver);
			/* loginPage.acceptCookies(); */
			/*
			 * test.log(LogStatus.PASS, "On Registeration page clicking on Login Button");
			 * registrationPage.loginButton();
			 */
			test.log(LogStatus.PASS, "Verify logo UOS is displayed");
			loginPage.uosLogo().isDisplayed();
			loginPage.acceptCookies();
			test.log(LogStatus.PASS, "Verify login page title :" + loginPage.getTitle());
			Assert.assertEquals(loginPage.getTitle(), loginPageTitle);
			test.log(LogStatus.PASS, "Verify user able to enter username :" + username);
			loginPage.enterUserName(username);
			Thread.sleep(2000);
			test.log(LogStatus.PASS, "Verify user able to enter password :" + password);

			loginPage.enterPassword(password);
			Thread.sleep(2000);

			test.log(LogStatus.PASS, "Verify user able to click on Login button");
			homePage= loginPage.clickLoginbutton();
			Thread.sleep(4000);

			test.log(LogStatus.PASS, "Verify home page title :" + homePage.getTitle());
			Assert.assertEquals(loginPage.getTitle(), homePageTitle);

		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "fail to validate login page without password Credentials");
		}
	}

	@Test(enabled = true, priority = 1, dataProvider = "UILoginValidation")
	public void VerifyElementsOnLogOnPage(String loginPageTitle, String logOnText, String termsAndCondition) throws InterruptedException {
		
		try {
			/*test.log(LogStatus.PASS, "***************TC002--01 Verify Elements on Login Page****************");
			test.log(LogStatus.PASS, "***************TC002--02 Accepting cookies on Registration page****************");
			// LoginPage loginPage = new LoginPage(driver);
			loginPage.acceptCookies();*/
			
			test.log(LogStatus.PASS, "*****TC002--04 Accepting cookies on login page*****");
			loginPage.acceptCookies();
			loginPage.uosLogo().isDisplayed();
			test.log(LogStatus.PASS, "Title of Login Page is " + loginPage.getTitle());
			Assert.assertEquals(loginPage.getTitle(), loginPageTitle);
			
			if(loginPage.getLogOnText().isDisplayed()) {
				Assert.assertEquals(loginPage.getLogOnText().getText(), logOnText);
				test.log(LogStatus.PASS, "Header message on Login Page : "+loginPage.getLogOnText().getText() +" : Is Displayed");
			}
						
			if(loginPage.verifyLink().isDisplayed()) {
				Assert.assertEquals(loginPage.verifyLink().getText(), termsAndCondition);
				test.log(LogStatus.PASS, "terms and conditions link : "+loginPage.verifyLink().getText() +" : Is Displayed");
			}
			


		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "fail to validate login page without password Credentials");
		}
	}

	@AfterSuite
	public void closeBrowser() {
		driver.close();
		extent.endTest(test);
		extent.flush();
	}

}
