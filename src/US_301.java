import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class US_301 {
    public static void main(String[] args) {

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // Open the e-junkie  site
            driver.get("https://shopdemo.e-junkie.com");

            System.out.println("Waiting for 3 seconds...");
            Thread.sleep(3000);

            // Click add to cart Demo E-Book
            WebElement addToCartButton = driver.findElement(By.xpath("//*[@id=\"products\"]/div[1]/div/div[2]/a/div/div[2]/button"));
            addToCartButton.click();

            System.out.println("Waiting for 3 seconds...");
            Thread.sleep(4000);

            // Click continue shopping
            WebElement closecart = driver.findElement(By.xpath("//*[@id=\"Overlay\"]/div/div[1]/abbr/ion-icon//div/svg"));
            closecart.click();

            System.out.println("Waiting for 3 seconds...");
            Thread.sleep(3000);

            // Verify that the demo e-book is added to the basket
            WebElement cartIcon = driver.findElement(By.xpath("/html/body/nav/div/div[2]/div/a"));
            // Checking if Demo E-Book is in cart
            if (cartIcon.isDisplayed()) {
                System.out.println("Demo e-book successfully added to basket.");
            } else {
                System.out.println("Failed to add demo e-book to basket.");

                System.out.println("Waiting for 3 seconds...");
                Thread.sleep(3000);
            }

            // Click the Add Promo Code
            WebElement addPromoCode = driver.findElement(By.xpath("//*[@id=\"Overlay\"]/div/div[2]/div/div[1]/div[4]/button[2]"));
            addPromoCode.click();

            // Enter an invalid (random) promotional code
            WebElement promoCode = driver.findElement(By.xpath("//*[@id=\"Overlay\"]/div/div[2]/div/div[1]/div[4]/div/input"));
            promoCode.click();
            promoCode.sendKeys("RANDOMPROMOCODE");

            // Click Apply
            WebElement applyButton = driver.findElement(By.xpath("//*[@id=\"Overlay\"]/div/div[2]/div/div[1]/div[4]/div/button"));
            applyButton.click();

            // Show the "invalid promotional code" as a message
            WebElement errorMsg = driver.findElement(By.xpath("//*[@id=\"SnackBar\"]"));
            if (errorMsg.isDisplayed()) {
                System.out.println("Invalid promotional code warning message displayed.");
            } else {
                System.out.println("Invalid promotional code warning message not displayed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            //driver.quit();
        }
    }
}
