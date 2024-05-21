UI Automation framework to test the OpenWeather webapp covering the below scenarios:
- Validating search results
- Visibility and Correctness of search results
- Navigating to weather in your city
- Invalid city name
- Layers in maps section

Test report can be read in Open Weather UI Automation_Report.html

Dependencies used - Make sure to download the same.
- org.seleniumhq.selenium
- org.testng
- webdrivermanager
- extentreports

Repository details
- src/test/java/tests contains the test files HomePageTest.java and MapsTest.java
- src/main/java/pages contains the page object files HomePage.java & Maps.java and BasePage.java
- src/main/java/utils contains ExtentReportListener to create ExtentReports and ConfigUtil to fetch data from an external dataprovider in the form of a csv
- src/main/resources contains the dataprovider.csv
  
