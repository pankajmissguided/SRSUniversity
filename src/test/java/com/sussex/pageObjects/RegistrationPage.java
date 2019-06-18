package com.sussex.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {

	WebDriver driver;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void refreshPage() {
		// TODO Auto-generated method stub
		driver.navigate().refresh();
	}
	By logonButton = By
			.xpath("//*[@id=\"AlreadyLoggedInInformationPanelWrapper\"]/div/div/div[1]/a");

	public void loginButton() {
		driver.findElement(logonButton).isDisplayed();
		driver.findElement(logonButton).isEnabled();
		driver.findElement(logonButton).click();
	}
	//Objects for Family Name
	By FamilyName = By.cssSelector("#FamilyName");
	
	public void EnterFamilyName(String FName) {
		driver.findElement(FamilyName).clear();
	    driver.findElement(FamilyName).sendKeys(FName);
	}
	//Objects for Given Name
		By GivenName = By.cssSelector("#GivenName");
		
		public void EnterGivenName(String GName) {
			driver.findElement(GivenName).clear();
			driver.findElement(GivenName).sendKeys(GName);
		}
		//Objects for Date Birth
				By DateOfBirth = By.cssSelector("#BirthDate");
				
				public void EnterDateOfBirth(String DOB) {
					driver.findElement(DateOfBirth).clear();
					driver.findElement(DateOfBirth).sendKeys(DOB);
					driver.findElement(DateOfBirth).sendKeys(Keys.TAB);
					
				}
				
		//Objects for Gender
				By gendertxtBox = By.id("Gender");
		By GenderText = By.xpath("//*[@id='Gender_Container']/div[2]/div[1]/button/span");
		By GenderDropDownoptions = By.xpath("//*[@id='Gender_Container']/div[2]/div[2]/div[1]/table/tbody/tr/td");
		public void enterGender(String Gender) throws InterruptedException {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			
		
		WebElement element = driver.findElement(GenderText);
		
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2500);
		List<WebElement> li = driver.findElements(GenderDropDownoptions);
		for (WebElement webElement : li) {
			Thread.sleep(500);
			if (webElement.getText().trim().contains(Gender)) {
				executor.executeScript("arguments[0].click();", webElement);
				break;
							}
		}
		driver.findElement(gendertxtBox).sendKeys(Keys.TAB);
		}
		
		//Objects for Email Id
		
		By EmailIdText = By.cssSelector("#EmailAddress");
		
		public void enterEmailId(String emailId) {
			driver.findElement(EmailIdText).clear();
			driver.findElement(EmailIdText).sendKeys(emailId);
			driver.findElement(EmailIdText).sendKeys(Keys.TAB);
			
				}
		
		//Objects for Nationality
		
		By nationalityDrpBtn = By.xpath("//*[@id=\"Nationality_Container\"]/div[2]/div[1]/button");
		By nationalityOptions = By.xpath("//*[@id=\"Nationality_Container\"]/div[2]/div[2]/div[1]/table/tbody/tr/td");
		By nationalitytxt = By.id("Nationality");
		public void nationality(String Nationality) throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
			
			WebElement element = driver.findElement(nationalityDrpBtn);
			executor.executeScript("arguments[0].click();", element);
			Thread.sleep(2500);
			List<WebElement> li = driver.findElements(nationalityOptions);
			for (WebElement webElement : li) {
				Thread.sleep(500);
				if (webElement.getText().trim().contains(Nationality)) {
				     executor.executeScript("arguments[0].click();", webElement);
					break;
								}
			}
			driver.findElement(nationalitytxt).sendKeys(Keys.TAB);
			}
		
		//Objects for Password
		By PasswordText = By.cssSelector("#Password");
		
		public void password(String Password) {
			driver.findElement(PasswordText).clear();
			driver.findElement(PasswordText).sendKeys(Password);
			driver.findElement(PasswordText).sendKeys(Keys.TAB);
		}
		//Objects for Confirm Password
				By ConPasswordText = By.cssSelector("#ConfirmPassword");
				
				public void conPassword(String ConfirmPassword) {
					driver.findElement(ConPasswordText).clear();
					driver.findElement(ConPasswordText).sendKeys(ConfirmPassword);
				}
				//Objects for CheckBox
				By CheckBoxSelected = By.xpath("//*[@id='ParentObject_StudentRegistration']/div/section/div/div[9]/div/div[1]");
				
				public void previouslyAppliedcheckBox() throws InterruptedException {
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					
					WebElement element = driver.findElement(CheckBoxSelected);
					driver.findElement(CheckBoxSelected).isDisplayed();
					executor.executeScript("arguments[0].click();", element);
					//Thread.sleep(1500);
					
					//driver.findElement(CheckBoxSelected).click();
				}
				
				//Objects for Terms ANd Condition
				
				By termsAndConditionCheckBox = By.xpath("//div[@class='firstControl sideBySide styleContainer stackedStyle']//div[@class='cbcEditorField']");
				
				public void termsAndConditionCheckBox() {
                JavascriptExecutor executor = (JavascriptExecutor)driver;
					
					WebElement element = driver.findElement(termsAndConditionCheckBox);
					driver.findElement(termsAndConditionCheckBox).isDisplayed();
					executor.executeScript("arguments[0].click();", element);		
				}
				//Objects for Register button
				
				By RegisterButton = By.xpath("//*[@id='StudentApplicationRegistrationSection']/div[2]/div[2]/ul/li/button/span");
				
				public void verifyRegisterButton() throws InterruptedException {
					 JavascriptExecutor executor = (JavascriptExecutor)driver;
						
						WebElement element = driver.findElement(RegisterButton);
						driver.findElement(RegisterButton).isDisplayed();
						executor.executeScript("arguments[0].click();", element);
						Thread.sleep(2500);
				}

				By registerbtn = By.xpath("//*[@id=\"StudentApplicationRegistrationSection\"]/div[2]/div[2]/ul/li/button");
				public void clickOnRegisterButton() {
					// TODO Auto-generated method stub
					driver.findElement(registerbtn).isDisplayed();
                    driver.findElement(registerbtn).isEnabled();	
                    driver.findElement(registerbtn).click();
								}
				
				//Objects for Registration Confirmation
				By ConfirmRegistration = By.xpath("//*[@id='PrimaryAction_DefaultButton']/span");
				
				public WebElement verifyConfirmRegistrationButton() {
					return driver.findElement(ConfirmRegistration);
				}
				
				//objects for Thankyou msg after registration
				
				By ThankyouMsgAfterRegistration = By.cssSelector("div.splashScreenStatusText.plainText");
				
				public WebElement verifyThankyouText() {
					return driver.findElement(ThankyouMsgAfterRegistration);
				}
				// objects for confirmation email text after registration
					
					By ConfirmationText =  By.cssSelector("div.splashScreenStatusMessagePart.plainText");
					
					public WebElement verifyConfirmationText() {
						return driver.findElement(ConfirmationText);
								
}
				//Objects to verify student after registration
					
					By StudentGivenName = By.xpath("//*[@id='FormTitleControl']/div[2]//span[1]//span");
					
					public WebElement verifyStudentGivenName() {
						return driver.findElement(StudentGivenName);
					}
					
				//Objects to verify Error Msg on Registration page
					
					By ErrorMsg = By.xpath("//*[@id='MessagesPanel']/p/span[2]/text()");
					
					public WebElement  verifyErrorMsg() {
						return driver.findElement(ErrorMsg);
					}
					}
				

