package cucamber.test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class CodersGuruSteps {

    private WebDriver driver;


    @Given("^opened page CodersGuru$")
    public void openedPageCodersGuru() {

        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver");

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://tester.codersguru.pl/");
    }

    @Given("^user email is (.*)$")
    public void userEmailInToSite(String email) {

        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(email);
        emailInput.submit();
    }

    private void fillForm(String value, String inputID){
        driver.findElement(By.id(inputID)).sendKeys(value);
    }

    @Given("^user name is (.*)$")
    public void userName(String username) {
        fillForm(username, "fos_user_registration_form_name");
    }

    @Given("^user second name is (.*)$")
    public void userSecondName(String secondName) {
        fillForm(secondName, "fos_user_registration_form_lastname");
    }

    @Given("^user password is (.*)$")
    public void userPassword(String password) {
        fillForm(password, "fos_user_registration_form_plainPassword_first");
    }

    @Given("^user repeats password is (.*)$")
    public void userRepeatsPassword(String password) {
        fillForm(password, "fos_user_registration_form_plainPassword_second");
    }

    @Given("^user city is (.*)$")
    public void userCity(String city) {
        fillForm(city, "form_city");
    }

    @Given("^user postcode is (.*)$")
    public void userPostcode(String postcode) {
        fillForm(postcode, "form_postal_code");
    }

    @Given("^user street is (.*)$")
    public void userStreet(String street) {
        fillForm(street, "form_street");
    }

    @Given("^user home number$")
    public void userHomeNumber() {
        Random random = new Random();

        fillForm(Integer.toString(random.nextInt(9)), "form_number");
    }

    @Given("^user opt-in$")
    public void userOptIn() {
        driver.findElement(By.xpath("/html/body/div/div/div/form/div[12]/input")).click();
    }

    @Then("^form is properly sent$")
    public void formIsProperlySent() {
        driver.findElement(By.id("register-submit-btn")).submit();

        Assert.assertNotNull(driver.findElement(By.id("user-name")));
    }

    @Then("^close browser$")
    public void closeBrowser(){
        driver.quit();
    }
}
