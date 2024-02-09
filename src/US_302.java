import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class US_302 {

    public static WebDriver driver;
    static {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static void myWait(int sec){
        try{
            Thread.sleep(sec * 1000);
        }catch (InterruptedException e){
            throw new RuntimeException();
        }
    }

    @Before
    public void setUp() {
        // Navigate to the URL: https://shopdemo.e-junkie.com
        driver.get("https://shopdemo.e-junkie.com/");
        myWait(1);
    }

    @Test
    public void faultyPayment() {
        // Click "Add To Cart" button for "Demo eBook".
        WebElement addToCartButton = driver.findElement(By.xpath("//div[2]//a[1]//div[1]//div[2]//button[1]"));
        addToCartButton.click();
        myWait(1);

        // Verification that "Demo eBook" is successfully added to the basket.
        WebElement iframe1= driver.findElement(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']"));
        driver.switchTo().frame(iframe1);
        WebElement cartItems = driver.findElement(By.xpath("(//span[@class='Cart-Items-Nos'])[2]"));
        String actualAmount = cartItems.getText();
        String expectedAmount = "1";
        Assert.assertEquals(expectedAmount,actualAmount);
        myWait(1);

        // Click "Pay Using Debit/Credit Card" button.
        WebElement choosePayment = driver.findElement(By.xpath("//button[@class='Payment-Button CC']"));
        choosePayment.click();
        myWait(1);

        // Verification that Email box is blank.
        WebElement blankEmail = driver.findElement(By.xpath("//input[@placeholder='Email']"));
        String actualEmail = blankEmail.getText();
        String expectedEmail = "";
        Assert.assertEquals(expectedEmail, actualEmail);
        myWait(1);

        // Verification that "Confirm Email" box is blank.
        WebElement blankConfirmEmail = driver.findElement(By.xpath("//input[@placeholder='Confirm Email']"));
        String actualConfirmEmail = blankConfirmEmail.getText();
        String expectedConfirmEmail = "";
        Assert.assertEquals(expectedConfirmEmail, actualConfirmEmail);
        myWait(1);

        // Verification that "Name On Card" box is blank.
        WebElement blankNameOnCard = driver.findElement(By.xpath("//input[@placeholder='Name On Card']"));
        String actualBlankNameOnCard = blankNameOnCard.getText();
        String expectedBlankNameOnCard = "";
        Assert.assertEquals(expectedBlankNameOnCard, actualBlankNameOnCard);
        myWait(1);


        // Click "Pay" button.
        WebElement payButton = driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        payButton.click();
        myWait(1);

        // Verification that during the payment process, "Invalid email Invalid Billing Name"
        // errors are displayed at the same time.
        WebElement snackBar = driver.findElement(By.xpath("//div[@id='SnackBar']"));
        String actualMessage = snackBar.getText();
        String expectedMessage = "Invalid Email\n" + "Invalid Email\n" + "Invalid Billing Name";
        Assert.assertEquals(expectedMessage, actualMessage);
        myWait(1);
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
