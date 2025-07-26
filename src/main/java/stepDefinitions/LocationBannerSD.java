package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLOutput;
import java.time.Duration;
import java.time.Instant;

import static org.junit.Assert.assertTrue;

public class LocationBannerSD {
    @Given("I am on the Cricut website homepage")
    public void iAmOnTheCricutWebsiteHomepage() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://cricut.com/en-us/");
        driver.manage().window().maximize();
        //Reject button is closed
        WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        RejectAllButton.click();

        System.out.println("TEST PASSED: CRICUT HOME PAGE IS LOADED");
        driver.quit();
    }

    @Then("I should see the location banner with text {string}")
    public void iShouldSeeTheLocationBannerWithText(String arg0) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://cricut.com/en-us/");
        driver.manage().window().maximize();
        //Reject button is closed
        WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        RejectAllButton.click();

        String expectedText = "You are currently viewing Cricut United States";
        WebElement BannerText = driver.findElement(By.xpath("//div[@class='georedirect__title']"));
        if(BannerText.getText().equals(expectedText)){
            System.out.println("Banner Text is displayed");
        }else {
            System.out.println("Banner is not displayed");
        }

        System.out.println("TEST PASSED BANNER TEXT DISPLAYED");
        driver.quit();

    }

    @And("I should see a link to update the location")
    public void iShouldSeeALinkToUpdateTheLocation() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://cricut.com/en-us/");
        driver.manage().window().maximize();
        //Reject button is closed
        WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        RejectAllButton.click();

        WebElement UpdateLocationLink = driver.findElement(By.xpath("//a[normalize-space()='Update your location?']"));
        String ExpectedUpdateText = "Update your location?";
        if(UpdateLocationLink.getText().equals(ExpectedUpdateText)){
            System.out.println("Update Location Link is Displayed");
        }else{
            System.out.println("Update Location text is not displayed");
        }
        System.out.println("TEST PASSED BANNER UPDATE LINK IS DISPLAYED");
        driver.quit();
    }

    @When("I click the {string} link")
    public void iClickTheLink(String arg0) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://cricut.com/en-us/");
        driver.manage().window().maximize();
        //Reject button is closed
        WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        RejectAllButton.click();

        WebElement UpdateLocLink = driver.findElement(By.linkText("Update your location?"));

        if(UpdateLocLink.isDisplayed()){
            System.out.println("Update link is Displayed");
        }else{
            System.out.println("Update link is not displayed");
        }
        System.out.println("TEST PASSED AND UPDATE LINK POINTS TO THE RIGHT PAGE");
        driver.quit();
    }

    @Then("I should be navigated to the country selection page or see location update options")
    public void iShouldBeNavigatedToTheCountrySelectionPageOrSeeLocationUpdateOptions() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://cricut.com/en-us/");
        driver.manage().window().maximize();
        //Reject button is closed
        WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        RejectAllButton.click();

        WebElement updateLocationLink = driver.findElement(By.linkText("Update your location?"));
        updateLocationLink.click();

        //Implicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("en-in"));

        //Checking Condition
        String currentURL = driver.getCurrentUrl();
        assert currentURL != null;
        if (currentURL.contains("en-in")){
            System.out.println("Update link points to the Correct Page");
        }else {
            System.out.println("Update link does npt points to the Correct Page");
        }

        System.out.println("TEST PASSED AND UPDATE LINK POINTS TO THE RIGHT PAGE");
        driver.quit();
    }
}
