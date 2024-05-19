package pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import jdk.internal.org.jline.utils.Log;



public class HomePage {

	By searchBar = By.xpath("//input[@placeholder='Search city']");
	By searchButton = By.xpath("//button[text()='Search']");
	By temperatureDisplay = By.xpath("//span[@class='heading']");
	By searchListDropDownMenuItem = By.xpath("//ul[@class='search-dropdown-menu']//li");
	By searchResultCity = By.xpath("//div[contains(@class,'current-container')]//h2");
	By feelsLike = By.xpath("(//div[@class='bold'])[1]");
	By notFoundError = By.xpath("//div[contains(@class, 'not-found')]");

	WebDriver driver;
	WebDriverWait wait;

	//constructor to pass the driver instance
	public HomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}

	public void clickSearchBar() {
		wait.until(ExpectedConditions.elementToBeClickable(searchBar)).click();					
	}

	public void enterCityToSearch(String cityName) throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(searchBar)).sendKeys(cityName);
		wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchListDropDownMenuItem)).click();
		Thread.sleep(3000);
	}
	
	public void enterInvalidCity(String invalidCity) throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(searchBar)).sendKeys(invalidCity);
		wait.until(ExpectedConditions.elementToBeClickable(searchBar)).sendKeys(Keys.ENTER);
	}

	public void validateSearchResult(String cityName) {
		    Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(searchResultCity)).isDisplayed());
		    String city = driver.findElement(searchResultCity).getText();
		    Assert.assertTrue(city.contains(cityName));
			Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(temperatureDisplay)).isDisplayed());
			String temperature = driver.findElement(temperatureDisplay).getText().toString().substring(0,2);
			int temperatureValue = Integer.parseInt(temperature);
			Assert.assertTrue(temperatureValue < 70 && temperatureValue > 0, "Abnormal changes in temperature");		
	}
	
	public void scrollDownThePage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,30)", "");
	}
	
	public void validate8DayForecast() {
		
		List<WebElement> daysList = driver.findElements(By.xpath("//ul[@class='day-list']//li"));
		Assert.assertTrue(daysList.size() == 8, "Does not contains 8 day forecast");
		
	}
	
	public void validateVisibilityAndCorrectness() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(feelsLike)));
		String feelsLikeText = driver.findElement(feelsLike).getText().toString();
		Assert.assertTrue(feelsLikeText.contains("Feels like"));
		Thread.sleep(3000);
		List<WebElement> weatherItems = driver.findElements(By.xpath("//ul[contains(@class,'weather-items')]//li"));
		if(feelsLikeText.contains("rain")) {
			String rainMM = weatherItems.get(0).getText().toString();
			Assert.assertTrue(rainMM.contains("mm"));
			String wnm = weatherItems.get(1).getText().toString();
			Assert.assertTrue(wnm.contains("m/s"));
			String pressure = weatherItems.get(2).getText().toString();
			Assert.assertTrue(pressure.contains("Pa"));
			String humidity = weatherItems.get(3).getText().toString();
			Assert.assertTrue(humidity.contains("%"));
			String dewPoint = weatherItems.get(4).getText().toString();
			Assert.assertTrue(dewPoint.contains("C"));
			String visibility = weatherItems.get(5).getText().toString();
			Assert.assertTrue(visibility.contains("km"));
		}else {
			String wnm = weatherItems.get(0).getText().toString();
			Assert.assertTrue(wnm.contains("m/s"));
			String pressure = weatherItems.get(1).getText().toString();
			Assert.assertTrue(pressure.contains("Pa"));
			String humidity = weatherItems.get(2).getText().toString();
			Assert.assertTrue(humidity.contains("%"));
			String uv = weatherItems.get(3).getText().toString();
			Assert.assertTrue(uv.contains("UV"));
			String dewPoint = weatherItems.get(4).getText().toString();
			Assert.assertTrue(dewPoint.contains("C"));
			String visibility = weatherItems.get(5).getText().toString();
			Assert.assertTrue(visibility.contains("km"));
		}
				
	}
	
	public void validateNotFoundResponse() {
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(notFoundError)));
		Assert.assertEquals(driver.findElement(notFoundError).getText(), "Not found. To make search more precise put the city's name, comma, 2-letter country code (ISO3166).");
	}
}
