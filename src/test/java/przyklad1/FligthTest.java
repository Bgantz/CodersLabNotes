package przyklad1;


import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FligthTest {

    private WebDriver driver;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.phptravels.net/");
    }

    @Test
    public void execution(){

        WebDriverWait waitShort = new WebDriverWait(driver, 10);
        WebDriverWait waitLong = new WebDriverWait(driver, 60);

        /*
        Steps:
        DONE - Change tab -> Flights
        DONE - Insert destination -> Waw
        DONE - Select depart time
        Send form
        Select first trip
        fill form with credentials
        expect bug notification in alert
         */

        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div/div/nav/ul/li[2]/a")).click();

        driver.findElements(By.className("custom-control-label")).get(1).click();

        String startAirportCode = "WAW";
        String startCityName = "Warsaw";
        String endAirportCode = "JFK";
        String endCityName = "New York";


        settingFlightRoute("origin","location_from_code", startAirportCode, startCityName);
        settingFlightRoute("destination","location_to_code", endAirportCode, endCityName);

        settingDate("FlightsDateStart", "2020-01-20");
        settingDate("FlightsDateEnd","2020-01-30");

        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div/div/div/div[2]/div/div/form/div/div[2]/div[4]/button")).submit();

        waitLong.until(ExpectedConditions.titleIs("Flights Results - From to " + startAirportCode + " to " + endAirportCode ));

        List<WebElement> flightOffers = driver.findElements(By.name("booking_token"));
        Assert.assertTrue(flightOffers.size() > 0);

    }

    private void changeTypeToText(String target){
        JavascriptExecutor JS = (JavascriptExecutor) driver;
        JS.executeScript("document.getElementsByName('" + target + "')[0].setAttribute('type','text')");

    }

    private void selectAirport (String airportCode,
                                String airportCity,
                                @NotNull WebElement input){
        input.clear();
        input.sendKeys("\"{\"code\":\"" + airportCode + "\",\"label\":\"" + airportCity + "\"}\"");
    }

    private void settingFlightRoute (String typeTarget,
                                     String elementId,
                                     String airportCode ,
                                     String airportCity){
        changeTypeToText(typeTarget);

        WebElement elementVar = driver.findElement(By.id(elementId));

        selectAirport(airportCode,
                airportCity,
                elementVar);

    }

    private void settingDate(String targetID,
                             String date){

        JavascriptExecutor JS = (JavascriptExecutor) driver;
        JS.executeScript("document.getElementById('" + targetID + "').value = ' " + date + "'");

    }

    @After
    public void shutDown(){
        driver.quit();
    }

}
