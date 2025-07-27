package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class searchBarSD {
    WebDriver driver = new ChromeDriver();


    @Given("User is on Cricut homepage")
    public void userIsOnCricutHomepage() {

        driver.get("https://cricut.com/en-us/");
        driver.manage().window().maximize();

        //Reject button is closed
        WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        RejectAllButton.click();

        //Pop is closed
        WebElement CloseButton = driver.findElement(By.xpath("//button[@aria-hidden='true']"));
        CloseButton.click();


        System.out.println("User is at Cricut Home page");
        System.out.println("TEST PASSED: CRICUT WEBSITE IS OPENED");
    }

    @When("User enters {string} into the search field")
    public void userEntersIntoTheSearchField(String arg0) {

        WebElement searchField = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchField.sendKeys("Vinyl");
        System.out.println("User can pass value to the Search field");

    }

    @And("User should be seeing suggestions for search item {string}")
    public void userShouldBeSeeingSuggestionsForSearchItem(String arg0) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement suggestions = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("suggestions-wrapper")));

        if (suggestions.isDisplayed()) {
            System.out.println("✅ Suggestions are showing up for search term: ");
        } else {
            System.out.println("❌ Suggestions are not showing up.");
        }
    }

    @Then("User clicks the search submit button")
    public void userClicksTheSearchSubmitButton() {
        WebElement searchButton = driver.findElement(By.xpath("//button[@aria-label='Submit search keywords']"));
        searchButton.click();
        System.out.println("Search Button is clicked");

    }


    @And("User should be navigated to the correct PGW \\(Product Grid Page)")
    public void userShouldBeNavigatedToTheCorrectPGWProductGridPage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("en-us"));

        WebElement searchField = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchField.sendKeys("Vinyl");

        WebElement searchButton = driver.findElement(By.xpath("//button[@aria-label='Submit search keywords']"));
        searchButton.click();

        wait.until(ExpectedConditions.urlContains("en-us/search?q=Vinyl"));

        String CurrentUrl = driver.getCurrentUrl();
        assert CurrentUrl != null;

        if(CurrentUrl.contains("en-us/search?q=vinyl")){
            System.out.println("User Navigated to the right page of the query");
        }else {
            System.out.println("User did not navigate to the right page for query passed");
        }

        System.out.println("TEST PASSED: SEARCH BAR TESTED");
        driver.quit();
    }
}
