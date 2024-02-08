

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v118.page.Page;
import org.openqa.selenium.interactions.Actions;

import java.sql.DriverManager;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class US_306CommunicationMessage {


    private WebDriver driver;



    @Before
    public void setUp() {

        driver = new ChromeDriver();
        driver.get("https://www.e-junkie.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void sendMessageThroughContactForm() {
        driver.get("https://Shopdemo.e-Junkie.com");

        driver.findElement(By.xpath("//a[contains(text(),'Contact Us')]")).click();

        WebElement firstName = driver.findElement(By.xpath("//input[@name='sender_name']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        firstName.sendKeys("John Doe");

        WebElement email = driver.findElement(By.xpath("//input[@name='sender_email']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        email.sendKeys("johndoe@example.com");

        WebElement question = driver.findElement(By.xpath("//input[@name='sender_subject']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        question.sendKeys("Question");

        WebElement message = driver.findElement(By.xpath("//textarea[@name='sender_message']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        message.sendKeys("This is a test message");


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.findElement(By.xpath("//button[@id='send_message_button']")).click();

        System.out.println("Recaptcha didn't match");


    }

    @After
        public void tearDown () {

            driver.quit();
        }
    }










