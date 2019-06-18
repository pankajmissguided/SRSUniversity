package com.sussex.testMethods;

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
import com.sussex.pageObjects.LoginPage;
import com.sussex.pageObjects.RegistrationPage;
import com.sussex.testBase.TestBase;

/**
 * Test Script for Home Page Controls Verification
 * 
 * @author Pankaj Kumar
 *
 */
public class TC01_verifyRegistrationPage extends TestBase {

	RegistrationPage registrationPage;
	HomePage homePage;
	LoginPage loginPage;

	// SerpPage serpPage;
	public TC01_verifyRegistrationPage() {

		super();
	}

	@DataProvider(name = "Register")
	public String[][] getSearchData() {
		String[][] testdata = getData("Registration.xlsx", "Register");
		return testdata;
	}
	@DataProvider(name = "DuplicateRegis")
	public String[][] DuplicateRegis() {
		String[][] testdata = getData("Registration.xlsx", "DuplicateRegis");
		return testdata;
	}

	@BeforeSuite
	public void setUp() throws InterruptedException {
		try {
			// Creating an instance for Browser, Navigating to Url & Maximize the browser
			initialize();
			// Start Reporting - Extent Report
			extent = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReports.html");
			test = extent.startTest("Home Page Controls verification");
			registrationPage = new RegistrationPage(driver);
			homePage = new HomePage(driver);
			loginPage = new LoginPage(driver);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(enabled = false, dataProvider = "Register")
	public void TS01_verifyStudentAbleToRegisterToWithSussexUniversity(String familyName, String givenName,
			String dateOFBirth, String gender, String emailId, String nationality, String password, String conPassword,String ThankyouText,String ConfirmationText
			,String ContinueButtonText,String WelcomeText,String runMode) {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {

			registrationPage.EnterFamilyName(familyName);
			registrationPage.EnterGivenName(givenName);
			Thread.sleep(2000);
			registrationPage.EnterDateOfBirth(dateOFBirth);
			Thread.sleep(4000);
			registrationPage.enterGender(gender);
			Thread.sleep(2000);
			registrationPage.enterEmailId(emailId);
			Thread.sleep(2000);
			registrationPage.nationality(nationality);
			Thread.sleep(2000);
			registrationPage.password(password);
			Thread.sleep(2000);
			registrationPage.conPassword(conPassword);
			Thread.sleep(2000);
			registrationPage.previouslyAppliedcheckBox();
			Thread.sleep(2000);
			registrationPage.termsAndConditionCheckBox();
			Thread.sleep(2000);
			registrationPage.clickOnRegisterButton();
			Thread.sleep(5000);
			
			if(registrationPage.verifyThankyouText().isDisplayed()) {
				Assert.assertEquals(registrationPage.verifyThankyouText().getText(), ThankyouText);
				test.log(LogStatus.PASS, "terms and conditions link : "+registrationPage.verifyThankyouText().getText() +" : Is Displayed");
			}
			if(registrationPage.verifyConfirmationText().isDisplayed()) {
				Assert.assertEquals(registrationPage.verifyConfirmationText().getText(), ConfirmationText);
				test.log(LogStatus.PASS, "terms and conditions link : "+registrationPage.verifyConfirmationText().getText() +" : Is Displayed");
			}
			if(registrationPage.verifyConfirmRegistrationButton().isDisplayed()) {
				Assert.assertEquals(registrationPage.verifyConfirmRegistrationButton().getText(),ContinueButtonText);
				test.log(LogStatus.PASS, "terms and conditions link : "+registrationPage.verifyConfirmRegistrationButton().getText() +" : Is Displayed");
			}
			registrationPage.verifyConfirmRegistrationButton().click();
			Thread.sleep(5000);
			loginPage.acceptCookies();
			if(registrationPage.verifyStudentGivenName().isDisplayed()) {
				Assert.assertEquals(registrationPage.verifyStudentGivenName().getText(),WelcomeText);
				test.log(LogStatus.PASS, "terms and conditions link : "+registrationPage.verifyStudentGivenName().getText() +" : Is Displayed");
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	@Test(enabled = true, dataProvider = "DuplicateRegis")
	public void TS01_verifyDuplicateStudentNotAbleToRegister(String familyName, String givenName,
			String dateOFBirth, String gender, String emailId, String nationality, String password, String conPassword,String ErrorMsg,String runMode) {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {

			registrationPage.EnterFamilyName(familyName);
			registrationPage.EnterGivenName(givenName);
			Thread.sleep(2000);
			registrationPage.EnterDateOfBirth(dateOFBirth);
			Thread.sleep(4000);
			registrationPage.enterGender(gender);
			Thread.sleep(2000);
			registrationPage.enterEmailId(emailId);
			Thread.sleep(2000);
			registrationPage.nationality(nationality);
			Thread.sleep(2000);
			registrationPage.password(password);
			Thread.sleep(2000);
			registrationPage.conPassword(conPassword);
			Thread.sleep(2000);
			registrationPage.previouslyAppliedcheckBox();
			Thread.sleep(2000);
			registrationPage.termsAndConditionCheckBox();
			Thread.sleep(2000);
			registrationPage.clickOnRegisterButton();
			Thread.sleep(3000);
			registrationPage.verifyConfirmRegistrationButton().click();
			//Thread.sleep(3000);
			//loginPage.acceptCookies();
			Thread.sleep(5000);
			System.out.println("successfully load the error msg");
			registrationPage.verifyErrorMsg().isDisplayed();
			Assert.assertEquals(registrationPage.verifyErrorMsg().getText(),ErrorMsg);
			System.out.println("successfully load the error msg");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	@Test(enabled = false, dataProvider = "Register")
	public void TS01_verifyMandatoryFieldsOnRegistrationPage(String familyName, String givenName,
			String dateOFBirth, String gender, String emailId, String nationality, String password, String conPassword,String ThankyouText,String ConfirmationText
			,String ContinueButtonText,String WelcomeText,String runMode) {
		if (runMode.equalsIgnoreCase("n")) {
			throw new SkipException("user marked this record as no run");
		}
		try {

			registrationPage.EnterFamilyName(familyName);
			registrationPage.EnterGivenName(givenName);
			Thread.sleep(2000);
			registrationPage.EnterDateOfBirth(dateOFBirth);
			Thread.sleep(4000);
			registrationPage.enterGender(gender);
			Thread.sleep(2000);
			registrationPage.enterEmailId(emailId);
			Thread.sleep(2000);
			registrationPage.nationality(nationality);
			Thread.sleep(2000);
			registrationPage.password(password);
			Thread.sleep(2000);
			registrationPage.conPassword(conPassword);
			Thread.sleep(2000);
			registrationPage.previouslyAppliedcheckBox();
			Thread.sleep(2000);
			registrationPage.termsAndConditionCheckBox();
			Thread.sleep(2000);
			registrationPage.clickOnRegisterButton();
			Thread.sleep(5000);
			
			if(registrationPage.verifyThankyouText().isDisplayed()) {
				Assert.assertEquals(registrationPage.verifyThankyouText().getText(), ThankyouText);
				test.log(LogStatus.PASS, "terms and conditions link : "+registrationPage.verifyThankyouText().getText() +" : Is Displayed");
			}
			if(registrationPage.verifyConfirmationText().isDisplayed()) {
				Assert.assertEquals(registrationPage.verifyConfirmationText().getText(), ConfirmationText);
				test.log(LogStatus.PASS, "terms and conditions link : "+registrationPage.verifyConfirmationText().getText() +" : Is Displayed");
			}
			if(registrationPage.verifyConfirmRegistrationButton().isDisplayed()) {
				Assert.assertEquals(registrationPage.verifyConfirmRegistrationButton().getText(),ContinueButtonText);
				test.log(LogStatus.PASS, "terms and conditions link : "+registrationPage.verifyConfirmRegistrationButton().getText() +" : Is Displayed");
			}
			registrationPage.verifyConfirmRegistrationButton().click();
			Thread.sleep(5000);
			loginPage.acceptCookies();
			if(registrationPage.verifyStudentGivenName().isDisplayed()) {
				Assert.assertEquals(registrationPage.verifyStudentGivenName().getText(),WelcomeText);
				test.log(LogStatus.PASS, "terms and conditions link : "+registrationPage.verifyStudentGivenName().getText() +" : Is Displayed");
			}
			
			
			
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
