/*
Zadanie 10
Zadania do wykonania:

Pod linkiem https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html dostępny jest formularz, którego
wypełnianie i wysyłanie należy zautomatyzować tym razem z wykorzystaniem Selenium WebDriver.
Jest to bardzo ważne ćwiczenie. Ćwiczenie w praktyce wykorzystuję wiedzę z lokalizowania elementów na stronie.
Wszyscy kursanci muszą to zadanie wykonać!

Wypełnij dane w formularzu:
First name Karol
Last name Kowalski
Gender Male
Date of birth 05/22/2010
Address Prosta 51
Email karol.kowalski@mailinator.com
Password Pass123
Company Coders Lab
Comment To jest mój pierwszy automat testowy
Zatwierdź formularz - SUBMIT

Wymagania dotyczące skryptu:

Pamiętaj o odpowiednich komentarzach - tak aby móc w przyszłości wrócić do tego skryptu.
Pamiętaj każdy element musi być poprawnie i jednoznacznie zlokalizowany aby skrypt testowy wykonywał swoje zadanie.
Staraj się pracować krok po kroku - zlokalizuj pierwszy element, sprawdź czy skrypt poprawnie go rozpoznaje,
następnie kolejny i kolejny.

Zapisz efekty swojej pracy. Będą one wymagane w dalszej części kursu.

Zadanie 11
Rozbuduj swój kod z zadania 10 o następujące elementy:
Przed każdym elementem sprawdź czy jest on widoczny lub dostępny aby wpisać do niego tekst.
Przed każdym elementem odczytaj jego nazwę a następnie wyświetl ją w konsoli w formacie - nazwa pola : wpisywana wartość
 */

package przyklad1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Zadanie10 {

   private WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver");

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
    }

    @Test
    public void fillUpInputs(){


        driver.findElements(By.tagName("input")).clear(); // clear all inputs

        fillForm("firstName", "Karol");
        fillForm("lastName", "Kowalski");

        driver.findElements(By.name("gender")).get(0).click();                                     // gender-list

        fillForm("dob", "05/22/2010");
        fillForm("address", "Prosta 51");
        fillForm("email", "karol.kowalski@malinator.com");
        fillForm("password", "Pass123");
        fillForm("company", "CodersLab");

        WebElement role = driver.findElement(By.name("role"));

        new Select(role).selectByVisibleText("QA");                                                     // select role

        WebElement expectations = driver.findElement(By.name("expectation"));

        new Select(expectations).selectByVisibleText("High salary");                             // select expectations

        fillForm("comment", "To jest mój pierwszy automat testowy");

        driver.findElement(By.id("submit")).submit();

        Assert.assertEquals( "Successfully submitted!",driver.findElement(By.id("submit-msg")).getText());
    }

    private void fillForm(String name, String value){
        WebElement input = driver.findElement(By.name(name));
        if (input.isDisplayed()){
            input.sendKeys(value);
            System.out.println(name);
        }
    }

    @After
    public void tearDown(){

        driver.quit();

    }

}
