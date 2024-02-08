import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import  org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class US_304_Payment_305_Download {

    WebDriver driver;



    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://shopdemo.e-junkie.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public  void paymentCheck() throws AWTException {
        WebElement addToCart = driver.findElement(By.xpath("(//button[@class='view_product'])[2]"));
        addToCart.click();


        WebElement totalCost = driver.findElement(By.xpath("(//span[@class='price'][normalize-space()='USD 0.50'])[2]"));
        String totalCoastText = totalCost.getText();

        System.out.println("The price is " + totalCoastText);



        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']"));
        driver.switchTo().frame(iframe);
        WebElement choosePayment = driver.findElement(By.xpath("//button[@class='Payment-Button CC']"));
        ReusableMethod.myWait(2);
        choosePayment.click();

        ReusableMethod.myWait(1);
        WebElement email = driver.findElement(By.xpath("(//input[@type ='email'])[1]"));
        email.sendKeys("abc@test.com" + Keys.TAB + "abc@test.com" + Keys.TAB + "John Doe" + Keys.TAB + Keys.ENTER);

        WebElement iframe1 = driver.findElement(By.xpath("//iframe[@title='Secure card payment input frame']"));
        driver.switchTo().frame(iframe1);
        WebElement cardNumber = driver.findElement(By.xpath("//input[@class='InputElement is-empty Input Input--empty']"));
        cardNumber.sendKeys("4242 4242 4242 4242 12/24  000");


        ReusableMethod.myWait(1);

        driver.switchTo().parentFrame();

        ReusableMethod.myWait(1);
        WebElement paymentButton = driver.findElement(By.xpath("//button[@class='Pay-Button']"));
        paymentButton.click();
        ReusableMethod.myWait(15);


        WebElement message = driver.findElement(By.xpath(" //span[@class='green_text_margin']"));
        Assert.assertTrue(message.isDisplayed());

//US_305_Download

        WebElement expectedCost = driver.findElement(By.xpath("//span[@class='usd'][normalize-space()='USD 0.50']"));
        String expectedCostText = expectedCost.getText();
       if (totalCoastText.equals(expectedCostText)) {
           System.out.println("Prices are the same: " + totalCoastText);
       }else {
           System.out.println("Prices are different. Total cost: " + totalCoastText + ", Expected cost: " + expectedCostText);
       }


        WebElement download = driver.findElement(By.xpath("//span[@class='download_btn top10']"));
        Actions actions = new Actions(driver);
        actions.click(download).build().perform();

        String filePath = "\"C:\\Users\\tanit\\Downloads\\demo.pdf\"";

        StringSelection pathOfFile = new StringSelection(filePath);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pathOfFile, null);

        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        ReusableMethod.myWait(2);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }
}

















