import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Registration {
    WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();  //browser launch

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void visitWebsite() {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        String titleActualResult = driver.getTitle();
    }
    @Test
    public void formFillup() {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        driver.findElement(By.id("first_name")).sendKeys("Aurnab");     //first name
        driver.findElement(By.id("last_name")).sendKeys("Biswas");      // last name
        driver.findElement(By.id("user_email")).sendKeys("biswasarno75@example.com");  //mail

        List<WebElement> nationalityFields = driver.findElements(By.className("form-row"));         //for nationality
        for (WebElement field : nationalityFields) {
            WebElement label = field.findElement(By.tagName("label"));
            if (label.getText().contains("Nationality")) {
                WebElement input = field.findElement(By.tagName("input"));
                input.sendKeys("Bangladeshi");
                break;
            }
        }
        WebElement countrySelect = driver.findElement(By.id("country_1665629257"));                //for country
        Select selectCountry = new Select(countrySelect);
        selectCountry.selectByVisibleText("Bangladesh");


        WebElement phoneInput = driver.findElement(By.id("phone_1665627880"));               //for phone number

        Actions actions = new Actions(driver);
        actions.moveToElement(phoneInput)
                .click()
                .sendKeys("(017) 456-7890")
                .build()
                .perform();

        WebElement dobInput = driver.findElement(By.id("date_box_1665628538_field"));      //for DOB
        dobInput.click();
        WebElement currentMonthContainer = driver.findElement(By.className("flatpickr-current-month"));
        WebElement realMonthSelect = currentMonthContainer.findElement(By.tagName("select"));

        realMonthSelect.click();
        List<WebElement> options = realMonthSelect.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (option.getText().equals("March")) {
                option.click();
                break;
            }
        }


        WebElement yearInput = currentMonthContainer.findElement(By.className("cur-year"));
        yearInput.clear();
        yearInput.sendKeys("2000");
        yearInput.sendKeys(Keys.ENTER);


        List<WebElement> days = driver.findElements(By.className("flatpickr-day"));
        for (WebElement day : days) {
            if (day.getText().equals("30") && day.isDisplayed()) {
                day.click();
                break;
            }
        }
        WebElement maleLabel = driver.findElement(By.cssSelector("label[for='radio_1665627729_Male']"));       //for gender
        maleLabel.click();
        WebElement maleRadio = driver.findElement(By.id("radio_1665627729_Male"));
        Assertions.assertTrue(maleRadio.isSelected());


        WebElement passwordField = driver.findElement(By.id("user_pass"));       //for password
        passwordField.clear();
        passwordField.sendKeys("MySecureP@ss123");

        System.out.println("Entered password: " + passwordField.getAttribute("value"));

        JavascriptExecutor js = (JavascriptExecutor) driver;                         // for T&C
        js.executeScript("window.scrollBy(0, 1500);");
        WebElement checkbox = driver.findElement(By.id("privacy_policy_1665633140"));
        js.executeScript("arguments[0].click();", checkbox);
        Assertions.assertTrue(checkbox.isSelected(), "Terms & Conditions checkbox should be selected.");

        WebElement submitButton = driver.findElement(By.className("ur-submit-button"));
        submitButton.click();

    }



}
