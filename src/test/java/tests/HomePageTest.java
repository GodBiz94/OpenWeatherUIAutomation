package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;

import pages.BasePage;
import pages.HomePage;

public class HomePageTest extends BasePage {

	@DataProvider(name="cityname")
	public Object[] getData() {
		return new Object[] {"Bengaluru"};
	}
	
	@DataProvider(name="invalidInput")
	public Object[] getInvalidInput() {
		return new Object[] {"XYZ"};
	}
	
	@Test(dataProvider="cityname")
	public void testCitySearch(String cityname) throws Exception {
		HomePage home = new HomePage(driver);
		home.clickSearchBar();
		home.enterCityToSearch(cityname);
		home.validateSearchResult(cityname);
		home.scrollDownThePage();
		home.validate8DayForecast();
	}
	
	@Test(dataProvider="cityname")
	public void testVisibilityAndCorrectness(String cityname) throws Exception {
		HomePage home = new HomePage(driver);
		home.clickSearchBar();
		home.enterCityToSearch(cityname);
		home.validateVisibilityAndCorrectness();
		
	}
	
	@Test(dataProvider="invalidInput")
	public void testInvalidCityName(String invalidCity) throws Exception {
		HomePage home = new HomePage(driver);
		home.clickSearchBar();
		home.enterInvalidCity(invalidCity);
		home.validateNotFoundResponse();
	}
	
}
