package com.sussex.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.sussex.excelReaders.Excel_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {

	public static Properties prop;
	public static WebDriver driver;
	public static FileInputStream fis;
	public static ExtentTest test;
	public static ExtentReports extent;
	static DesiredCapabilities cap;
	Excel_Reader excel;
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/test/java/com/sussex/testReport/test"
				+ formater.format(calendar.getTime()) + ".html", false);
	}

	/**
	 * This method is to determine the reading the property file.
	 * 
	 * @throws IOException
	 */
	public void properties() throws IOException {
		prop = new Properties();
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/java/com/sussex/configs/config.properties");
			prop.load(fis);
			System.out.println("Properties from config file " + prop);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String[][] getData(String excelName, String sheetName) {
		String path = System.getProperty("user.dir") + "/src/main/resources/testData/" + excelName;
		excel = new Excel_Reader(path);
		String[][] data = excel.getDataFromSheet(sheetName, excelName);
		return data;
	}

	public void initialize() throws IOException, InterruptedException {
		properties();

		invokeBrowser(prop.getProperty("browserType"));
		driver.get(prop.getProperty("siteurl"));
		Thread.sleep(4000);
		driver.manage().window().maximize(); // window maximize

	}

	public void Login(String username, String password) {

	}

	/**
	 * Invoke the browser for all levels.
	 * 
	 * @param browser
	 */
	public void invokeBrowser(String browser) {

		if (System.getProperty("os.name").contains("Windows")) {
			if (browser.equalsIgnoreCase("firefox")) {

				// DesiredCapabilities cap = new DesiredCapabilities();
				// cap.setCapability("proxy", proxy);
				System.setProperty("webdriver.firefox.marionette",
						System.getProperty("user.dir") + "/driver/geckodriver.exe");
				FirefoxOptions options = new FirefoxOptions();
				String strFFBinaryPath = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
				options.setBinary(strFFBinaryPath);
				driver = new FirefoxDriver(options);

				// driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().setup();
				
				DesiredCapabilities cap = new DesiredCapabilities();

				driver = new ChromeDriver(cap);

			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");

				DesiredCapabilities cap = new DesiredCapabilities();

				driver = new InternetExplorerDriver(cap);

			} else {
				System.out.println("Browser Name not found");
			}

		}

	}

	public void getScreenShot(String name) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "/src/main/resources/screenshots/";
			File destFile = new File(
					(String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void selectDashBoardElement(String elementName) throws InterruptedException {
		Thread.sleep(5000);
		WebElement ele = driver.findElement(By.className("padding-bottom-15"));
		List<WebElement> li = ele.findElements(By.tagName("a"));
		for (WebElement webElement : li) {
			if (webElement.getText().equalsIgnoreCase(elementName)) {
				webElement.click();

				break;
			}
		}
	}

	public void acceptAlert() {
		Alert al = driver.switchTo().alert();
		al.accept();
	}

	public void dismissAlert() {
		Alert al = driver.switchTo().alert();
		al.dismiss();
	}

}

/*
 * DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
 * capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
 * capabilities.setCapability(InternetExplorerDriver.
 * INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
 * capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
 * capabilities.setCapability("allow-blocked-content", true);
 * capabilities.setCapability("allowBlockedContent", true);
 * System.setProperty("webdriver.ie.driver", Log.gsAutomationAutoPath +
 * "IEDriverServer.exe"); WebDriver driver = new
 * InternetExplorerDriver(capabilities);
 */
