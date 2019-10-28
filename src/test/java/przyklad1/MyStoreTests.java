package przyklad1;
/*

Zadanie Dodatkowe 2
Ważną funkcjonalnością sklepu jest możliwość zarejestrowania nowego użytkownika. W tym celu proszę przygotuj skrypt,
który będzie miał możliwość tworzenie dowolnej liczby użytkowników
w systemie.

Po stworzeniu użytkownika uzupełnij również jego dane adresowe (Your Account / Addresses). Postaraj się aby dane były
w jak największym stopniu losowe.

Klika podpowiedzi:

Skrypt powinien mieć możliwość zdefiniowania dowolnej liczby użytkowników, którą chcemy utworzyć.
Zwróć uwagę, że: The email is already used, please choose another one or sign in.
Wiesz już jak generować losowe liczby - Wykorzystaj to i dodawaj losowe liczby do adresów e-mail.

Stwórz kilka tablic, które będą zawierały poszczególne dane wymagane do wypełnienia adresu a następnie dla każdego
z użytkowników losuj dowolny element z danej tablicy - to już umiesz.

String[] alias = {...};

String[] company = {...};

...

String[] city = {...};

__
Zadanie dodatkowe 3

Sprawdź funkcjonalność filtrowania produktów w sklepie. Przygotuj skrypt, który zweryfikuje czy po wyborze odpowiedniego
filtru wyświetlają się tylko i wyłącznie produkty spełniające jego warunki np. czy wyświetlają się tylko produkty w
danym przedziale cenowym.

Klika podpowiedzi:

Pamiętaj aby sprawdzić parametry każdego z wyświetlonych produktów, nie tylko pierwszego wyświetlonego.
Do porównywania skorzystaj z instrukcji warunkowych if ... (jeżeli cena jest ok -> wyświetl odpowiedni komunikat).
Nie musisz jeszcze korzystać z asercji, które zostaną omówione w następnym rozdziale.
__
Zadanie Dodatkowe 4
Sprawdź funkcjonalność zakupu produktów w sklepie. Dokonaj zamówienia dowolnych produktów. W koszyku powinny być
minimum 2 produktu z różnych kategorii.

Sprawdź czy suma poszczególnych produktów się zgadza.

Klika podpowiedzi:

Zwróć uwagę, że wielokrotne zakupy mogą spowodować brak towaru w naszym sklepie. Przygotuj skrypt również
na taką sytuację.
*/

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class MyStoreTests {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Uruchom nowy egzemplarz przeglądarki Chrome
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();

        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do Google
        driver.get("http://prod-kurs.coderslab.pl");
    }
    @Test
    public void testSearchShopTest() { // Zadanie Dodatkowe 1

        String[] products = {"mug", "t-shirt", "notebook"};
        Random random = new Random();
        int randomIntiger = random.nextInt(products.length);


        // Znajdź element wprowadzania tekstu na podstawie jego nazwy
        WebElement element = driver.findElement(By.name("s"));
        // Wyczyść teskst zapisany w elemencie
        element.clear();

        // Wpisz informacje do wyszukania
        element.sendKeys(products[randomIntiger]);

        // Prześlij formularz
        element.submit();

        WebElement productCounter = driver.findElement(By.xpath("//*[@id=\"js-product-list-top\"]/div[1]/p"));

        Assert.assertEquals("Search", driver.getTitle());
    }

    @Test
    public void registrationForm() throws InterruptedException { //Zadanie dodatkowe 2
        String[] firstNames = {"John", "Scott", "Franklin"};
        String[] lastNames = {"Smith", "Obama", "Trump"};
        String[] passwords = {"qwerty","zaqwsxcde","12345qwerty!@#$%"};
        String[] birthdates = {"23/12/2020", "01/01/1999", "03/05/2000"};
        String[] aliases = {"Ginger","Tall","Skinny"};
        String[] companies = {"softmicro", "ynos", "kiano"};
        String[] address = {"Grochowska 15", "Marszałkowska 30", "Plac Defilad 1"};
        String[] cities = {"Warsaw", "Berlin", "Budapest"};

        Random random = new Random();       //TODO: randomize should be in one method
        int randomName = random.nextInt(firstNames.length);
        int randomLastName = random.nextInt(lastNames.length);
        int randomPassword = random.nextInt(passwords.length);
        int randomBirthdate = random.nextInt(birthdates.length);
        int randomAliase = random.nextInt(aliases.length);
        int randomCompany = random.nextInt(companies.length);
        int randomAddress = random.nextInt(address.length);
        int randomCity = random.nextInt(cities.length);


        int vatNumber = random.nextInt(10);
        String postcode = random.nextInt(99) + "-" + random.nextInt(999);
        int phoneNumber = random.nextInt(999999999);

        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication&create_account=1");

        driver.findElements(By.className("radio-inline")).get(1).click();//Social Title

        fillForm("firstname", firstNames[randomName]); //First Name
        fillForm("lastname", lastNames[randomLastName]);//Last Name
        fillForm("email", firstNames[randomName].toLowerCase() + "."
                + lastNames[randomLastName].toLowerCase()
                + "@mailinator.com");
        fillForm("password", passwords[randomPassword]);//Password
        fillForm("birthday", birthdates[randomBirthdate]);//Birthdate

        driver.findElement(By.className("form-control-submit")).submit();

     //   Assert.assertTrue(driver.findElements(By.className("account")).contains("View my customer account"));

        Thread.sleep(2000);
        driver.findElement(By.className("account")).click();
        driver.findElement(By.id("address-link"));

        Thread.sleep(2000);
        fillForm("alias", aliases[randomAliase]);
        fillForm("company", companies[randomCompany]);
        fillForm("vat_number", String.valueOf(vatNumber));
        fillForm("address1", address[randomAddress]);
        fillForm("city", cities[randomCity]);
        fillForm("postcode", postcode );

        WebElement country = driver.findElement(By.name("id_country"));

        new Select(country).selectByVisibleText("United Kingdom");

        fillForm("phone", String.valueOf(phoneNumber));

        Assert.assertTrue(driver.findElement(By.cssSelector("article.alert-success")).isDisplayed());

    }

    @Test
    public void filterTesting(){ // Zadanie Dodatkowe 3

    }

    private void fillForm(String fieldName, String value){
        WebElement input = driver.findElement(By.name(fieldName));
        if (input.isDisplayed()) {
            input.clear();
            input.sendKeys(value);
            System.out.println( fieldName + ":" + value);
        }
    }

    @After
    public void tearDown(){
        // Zamknij przeglądarkę
        driver.quit();
    }
}