package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import util.ConfigUtil;

public class BasePage {
	
	
	public static WebDriver driver;
	public static ExtentTest logger;
	public static ExtentReports report;
	
	protected static final String URL = ConfigUtil.getProperty("url");
	protected static final String CITYNAME = ConfigUtil.getProperty("cityname");
	protected static final String INVALID_INPUT = ConfigUtil.getProperty("invalidName");
	protected static final String NOT_FOUND_ERROR_TEXT = ConfigUtil.getProperty("notFoundErrorText");
	
	@BeforeMethod
	public void setup(ITestContext context) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		context.setAttribute("WebDriver", driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close(); //closes all browser windows and ends WebDriver session
	}

}
