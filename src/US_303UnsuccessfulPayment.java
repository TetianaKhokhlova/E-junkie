import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class US_303UnsuccessfulPayment {
    WebDriver driver = new ChromeDriver();
    ReusableMethod reusableMethod = new ReusableMethod();

    @Before
    public void setUp(){
        //Navigate to website.
        driver.navigate().to("https://shopdemo.e-junkie.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void unsuccessfulPayment() {
        Actions actions = new Actions(driver);

        reusableMethod.myWait(1);
        // Add demo e-book to the basket
        WebElement addEBook = driver.findElement(By.xpath("//div[2]//a[1]//div[1]//div[2]//button[1]"));
        reusableMethod.myWait(1);
        //addEBook.click();
        actions.click(addEBook).build().perform();

        // iFrame
        WebElement iframeElementCCButton = driver.findElement(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']"));
        driver.switchTo().frame(iframeElementCCButton);
        reusableMethod.myWait(1);

        // Find credit card payment button and click on it
        WebElement creditCardButton = driver.findElement(By.xpath("//button[@data-option='CC']"));
        creditCardButton.click();
        reusableMethod.myWait(1);

        // Fill payment information
        // E-Mail
        WebElement eMailTextBox = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        eMailTextBox.sendKeys("test@test.com");
        reusableMethod.myWait(1);
        // Confirm E-Mail
        WebElement confirmEmailTextBox = driver.findElement(By.xpath("//input[@placeholder='Confirm Email']"));
        confirmEmailTextBox.sendKeys("test@test.com");
        reusableMethod.myWait(1);
        // Name on Card
        WebElement nameOnCardTextBox = driver.findElement(By.xpath("//input[@placeholder='Name On Card']"));
        nameOnCardTextBox.sendKeys("aliye");

        // Phone
        WebElement phoneNumberTextBox = driver.findElement(By.xpath("(//input[@autocomplete='phone'])[1]"));
        phoneNumberTextBox.sendKeys("987654321");

        // Company
        WebElement companyTextBox = driver.findElement(By.xpath("(//input[@autocomplete='company'])[1]"));
        companyTextBox.sendKeys("Techno Study");

        // Order Notes
        WebElement orderNotesTextBox = driver.findElement(By.xpath("//textarea[@placeholder='Optional']"));
        orderNotesTextBox.sendKeys("Aliye is shopping :)");
        reusableMethod.myWait(1);
        // iFrame CC details
        WebElement iFrameCCDetails = driver.findElement(By.xpath("(//iframe[@allow='payment *'])[2]"));
        driver.switchTo().frame(iFrameCCDetails);
        reusableMethod.myWait(1);
        // Credit Card Details
        WebElement creditCardDetailsTextBox = driver.findElement(By.xpath("//input[@placeholder='Kartennummer']"));
        creditCardDetailsTextBox.sendKeys("1111 1111 1111 1111");
        reusableMethod.myWait(1);

        // Credit Card MM/JJ
        WebElement creditCardDate = driver.findElement(By.xpath("//input[@placeholder='MM/JJ']"));
        creditCardDate.sendKeys("11/22");

        // Credit Card CVV
        WebElement cvvNumber = driver.findElement(By.xpath("//input[@placeholder='Prüfziffer']"));
        //send keys and put enter after Tab to pay button
        cvvNumber.sendKeys("030"+ Keys.TAB+Keys.ENTER);
        reusableMethod.myWait(1);
        //invalid message is displayed
        Assert.assertTrue(driver.switchTo().activeElement().isDisplayed());

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
