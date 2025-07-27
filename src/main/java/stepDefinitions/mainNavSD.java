package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class mainNavSD {

    WebDriver driver = new ChromeDriver();

    @Then("The main navigation bar should be visible")
    public void theMainNavigationBarShouldBeVisible() {
        driver.get("https://cricut.com/en-us/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
        driver.manage().window().maximize();

        //Reject button is closed
        WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        RejectAllButton.click();

        //Pop is closed
        WebElement CloseButton = driver.findElement(By.xpath("//button[@aria-hidden='true']"));
        CloseButton.click();


        System.out.println("User is at Cricut Home page");
        System.out.println("TEST PASSED: CRICUT WEBSITE IS OPENED");

        WebElement mainNavigation = driver.findElement(By.xpath("//ul[@class='nav-main__nav nav navbar-nav']"));
        if (mainNavigation.isDisplayed()) {
            System.out.println("Main Navigation is displayed");
        } else {
            System.out.println("Main navigation is not Displayed");
        }

    }

    @And("The following main menu items should be present for {string}:")
    public void theFollowingMainMenuItemsShouldBePresent(String mainMenuText, List<String> expectedSubLinks) {
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Hover over the main menu
        WebElement mainMenu = driver.findElement(By.xpath("//*[normalize-space()='" + mainMenuText + "']"));
        action.moveToElement(mainMenu).perform();
        System.out.println("Hovered over the item " + mainMenuText);

        // Wait for the entire Discover dropdown menu to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("discover")));

        // Fetch all sub-links under the Discover menu
        action.moveToElement(mainMenu).perform();
        List<WebElement> subLinks = driver.findElements(By.xpath("//div[@class='nav-list js-mobile-accordion']"));
        // Validate each expected sub-link
        for (String expectedLink : expectedSubLinks) {
            boolean found = subLinks.stream()
                    .anyMatch(link -> link.getText().trim().equalsIgnoreCase(expectedLink));
            if (found) {
                System.out.println("✅ Sub-link found: " + expectedLink);
            } else {
                System.out.println("❌ Sub-link missing: " + expectedLink);
            }

        }
    }
}