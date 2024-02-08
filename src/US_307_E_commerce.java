import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class US_307_E_commerce {
WebDriver driver;
    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://shopdemo.e-junkie.com/ ");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void eCommerce(){


        WebElement eCommerceElement= driver.findElement(By.xpath("//a[normalize-space()='E-commerce by E-junkie'] "));

       eCommerceElement.click();
       WebElement eJunkie = driver.findElement(By.xpath("//a[@href='/']//img"));
       eJunkie.click();
        String expectedResult =  "https://www.e-junkie.com/";

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(expectedResult,actualResult);

    driver.navigate().back();
        driver.navigate().back();


    }

}
