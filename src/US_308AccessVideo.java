import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class US_308AccessVideo {
    private WebDriver driver;
    @Before
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.e-junkie.com/");
    }
    @Test
    public void testVideoAccess() throws InterruptedException  {

        assert driver.getCurrentUrl().equals("https://www.e-junkie.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Actions actions = new Actions(driver);
        actions.moveByOffset(100,100).click().build().perform();



        WebElement seeHowItWorks = driver.findElement(By.xpath("(//a[@class='blue_btn'])[1]"));
        seeHowItWorks.click();

        WebElement play = driver.findElement(By.tagName("body"));
        play.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        Thread.sleep(2000);


        WebElement stop = driver.findElement(By.tagName("body"));
        stop.click();


        Actions actions2 = new Actions(driver);
        actions2.moveByOffset(200,200).click().build().perform();


        Thread.sleep(8000);

    }
    @After
    public void tearDown() {

       driver.quit();
    }
}



