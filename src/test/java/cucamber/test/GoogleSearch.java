package cucamber.test;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class GoogleSearch {
    public String keyword;
    private WebDriver driver;

    @Given("^an open browser with google\\.com$")
    public void anOpenBrowserWithGoogleCom() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");


        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.google.com");
    }

    @When("^a keyword (.*) is entered in input field$")
    public void aKeywordSeleniumIsEnteredInInputField(String keyword) {

        WebElement element = driver.findElement(By.name("q"));
        element.clear();
        element.sendKeys(keyword);
        element.submit();

    }

    @Then("^the first one should contain (.*)$")
    public void theFirstOneShouldContainSelenium(String expectedText) {
        System.out.println(expectedText);
    }

    @And("^close browser$")
    public void closeBrowser() {
        driver.quit();
    }
}
