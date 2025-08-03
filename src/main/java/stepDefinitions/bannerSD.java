package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class bannerSD {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    @Then("The main banner should be visible")
    public void theMainBannerShouldBeVisible() {

        driver.get("https://cricut.com/en-us/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
        driver.manage().window().maximize();


        //Reject button is closed
        WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        RejectAllButton.click();

        //Pop is closed
        WebElement CloseButton = driver.findElement(By.xpath("//button[@aria-hidden='true']"));
        CloseButton.click();

        //Initiate drive wait
        try {
            Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Locating the banner image element
        WebElement bannerElement = driver.findElement(By.cssSelector("img.reserve-hero.reserve-hero--none.reserve-hero--none-desktop"));
        if(bannerElement.isDisplayed()){
            System.out.println("TEST PASSED: Banner for Cricut website is displayed");
        }else {
            System.out.println("TEST FAILED: Banner for the cricut website is not displayed");
        }
    }

    @And("The main banner should contain a {string} link or button")
    public void theMainBannerShouldContainALinkOrButton(String arg0) {
        //Locating the shop now Button
        WebElement bannerShopNowButton = driver.findElement(By.linkText("Shop Now"));
        if(bannerShopNowButton.isDisplayed()){
            System.out.println("TEST PASSED: Banner 'Shop now' for Cricut website is displayed");
        }else {
            System.out.println("TEST FAILED: Banner 'Shop now' for the cricut website is not displayed");
        }
        driver.quit();
    }
}
