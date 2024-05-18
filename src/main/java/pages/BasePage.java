package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public static WebDriver driver;
	String url = "https://openweathermap.org/";
	public static ExtentTest logger;
	public static ExtentReports report;
	
	@BeforeMethod
	public void setup(ITestContext context) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		context.setAttribute("WebDriver", driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close(); //closes all browser windows and ends WebDriver session
	}

}
