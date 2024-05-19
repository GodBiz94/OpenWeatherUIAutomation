package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Maps {

	By mapsOption = By.xpath("//li[@id='desktop-menu']//a[text() = 'Maps']");
	By temperatureToggle = By.xpath("//div//label[@for='Temperature']");
	By pressureToggle = By.xpath("//div//label[@for='Pressure']");
	By windSpeedToggle = By.xpath("//div//label[@for='Wind speed']");
	By cloudsToggle = By.xpath("//div//label[@for='Clouds']");
	By globalPrecipitationToggle = By.xpath("//div//label[@for='Global Precipitation']");
	By citiesToggle = By.xpath("//div//label[text()='Cities']");
	By cityData = By.xpath("//div[@class='city-data']");
	By singleCityData = By.xpath("(//div[@class='city-data'])[1]");
	
	
	
	WebDriver driver;
	WebDriverWait wait;
	
	public Maps(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}
	
	public void navigateToMaps() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(mapsOption)));
		driver.findElement(mapsOption).click();
	}
	
	public void validateTemperatureToggle() {
		wait.until(ExpectedConditions.elementToBeClickable(citiesToggle));
		driver.findElement(citiesToggle).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(temperatureToggle)));
		driver.findElement(temperatureToggle).click();
		String scaleLabelTitle = driver.findElement(temperatureToggle).getText().toString();
		Assert.assertTrue(scaleLabelTitle.contains("Temperature"));
		
		List<WebElement> temperatureDividers = driver.findElements(By.xpath("//div[@class='scale-dividers']//div"));
		List<Integer> temperatures = new ArrayList<Integer>();
		for(WebElement element: temperatureDividers) {
			temperatures.add(Integer.parseInt(element.getText().toString()));
		}
		int minTemp = -40;
		int maxTemp = 40;
		for(int temp:temperatures) {
			Assert.assertTrue((temp <= maxTemp || temp >= minTemp));
		}
	}
	
	public void validatePressureToggle() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pressureToggle)));
		driver.findElement(pressureToggle).click();
		String scaleLabelTitle = driver.findElement(pressureToggle).getText().toString();
		Assert.assertTrue(scaleLabelTitle.contains("Pressure"));
		
		List<WebElement> pressureDividers = driver.findElements(By.xpath("//div[@class='scale-dividers']//div"));
		List<Integer> pressures = new ArrayList<Integer>();
		for(WebElement element: pressureDividers) {
			pressures.add(Integer.parseInt(element.getText().toString()));
		}
		int minPressure = 950;
		int maxPressure = 1070;
		for(int temp:pressures) {
			Assert.assertTrue((temp <= maxPressure || temp >= minPressure));
		}
	}
	
	public void validateWindSpeedToggle() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(windSpeedToggle)));
		driver.findElement(windSpeedToggle).click();
		String scaleLabelTitle = driver.findElement(windSpeedToggle).getText().toString();
		Assert.assertTrue(scaleLabelTitle.contains("Wind speed"));
		
		List<WebElement> windSpeedDividers = driver.findElements(By.xpath("//div[@class='scale-dividers']//div"));
		List<Integer> windSpeeds = new ArrayList<Integer>();
		for(WebElement element: windSpeedDividers) {
			windSpeeds.add(Integer.parseInt(element.getText().toString()));
		}
		int minWindSpeed = 0;
		int maxWindSpeed = 100;
		for(int temp:windSpeeds) {
			Assert.assertTrue((temp <= maxWindSpeed || temp >= minWindSpeed));
		}
	}
	
	public void validateCloudsToggle() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(cloudsToggle)));
		driver.findElement(cloudsToggle).click();
		String scaleLabelTitle = driver.findElement(cloudsToggle).getText().toString();
		Assert.assertTrue(scaleLabelTitle.contains("Clouds"));
		
		List<WebElement> cloudsDividers = driver.findElements(By.xpath("//div[@class='scale-dividers']//div"));
		List<Integer> clouds = new ArrayList<Integer>();
		for(WebElement element: cloudsDividers) {
			clouds.add(Integer.parseInt(element.getText().toString()));
		}
		int minCloud = 0;
		int maxCloud = 100;
		for(int temp:clouds) {
			Assert.assertTrue((temp <= maxCloud || temp >= minCloud));
		}
	}
	
	public void validateGlobalPrecipitationToggle() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(globalPrecipitationToggle)));
		driver.findElement(globalPrecipitationToggle).click();
		String scaleLabelTitle = driver.findElement(globalPrecipitationToggle).getText().toString();
		Assert.assertTrue(scaleLabelTitle.contains("Precipitation"));
		
		List<WebElement> precipitationDividers = driver.findElements(By.xpath("//div[@class='scale-dividers']//div"));
		List<Integer> precipitation = new ArrayList<Integer>();
		for(WebElement element: precipitationDividers) {
			precipitation.add(Integer.parseInt(element.getText().toString()));
		}
		int minPrecipitation = 0;
		int maxPrecipitation = 60;
		for(int temp:precipitation) {
			Assert.assertTrue((temp <= maxPrecipitation || temp >= minPrecipitation));
		}
	}
	
	public void validateCitiesToggle() throws Exception {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(citiesToggle)));
		driver.findElement(citiesToggle).click();
		Thread.sleep(3000);
		List<WebElement> cities = driver.findElements(cityData);
		Assert.assertTrue(cities.size() > 0);
		driver.findElement(citiesToggle).click();
		
		
	}
}
