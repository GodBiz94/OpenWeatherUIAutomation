package tests;

import org.testng.annotations.Test;

import pages.BasePage;
import pages.Maps;

public class MapsTest extends BasePage {
	
	@Test
	public void validateMapsTest() throws Exception {
		Maps maps = new Maps(driver);
		maps.navigateToMaps();
		maps.validateTemperatureToggle();
		maps.validatePressureToggle();
		maps.validateWindSpeedToggle();
		maps.validateCloudsToggle();
		maps.validateGlobalPrecipitationToggle();
		maps.validateCitiesToggle();
	}

}
