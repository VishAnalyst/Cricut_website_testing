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

        try {
            Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("User is at Cricut Home page");
        System.out.println("TEST PASSED: CRICUT WEBSITE IS OPENED");

        WebElement mainNavigation = driver.findElement(By.xpath("//ul[@class='nav-main__nav nav navbar-nav']"));
        if (mainNavigation.isDisplayed()) {
            System.out.println("Main Navigation is displayed");
        } else {
            System.out.println("Main navigation is not Displayed");
        }

    }
//***************************************************DISCOVER**********************************************************************
    @And("The following main menu items should be present for {string}:")
    public void theFollowingMainMenuItemsShouldBePresent(String mainMenuText, List<String> expectedSubLinks) {

        //List all links under the
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Hover over the main menu
        WebElement mainMenu = driver.findElement(By.xpath("//*[normalize-space()='" + mainMenuText + "']"));
        action.moveToElement(mainMenu).perform();
        System.out.println("Hovered over the item " + mainMenuText);

        WebElement subMenuContainer = driver.findElement(By.xpath("//div[@class='nav-list js-mobile-accordion']"));

        List<WebElement> subLinks = subMenuContainer.findElements(By.tagName("a"));

        for (WebElement link : subLinks) {
            System.out.println("DISCOVER SUB LINKS ARE LISTED");
            System.out.println("Sub-link Text: " + link.getText());
            System.out.println("Sub-link URL: " + link.getAttribute("href"));
        }

                try {
                    Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        System.out.println("Discover Test is passed");

        //Hover over Sale
        WebElement mainMenuSale = driver.findElement(By.id("sale"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sale")));
        action.moveToElement(mainMenuSale).perform();
        System.out.println("Hovered over the item " + mainMenuSale.getText());

        List<WebElement> subLinksSale = subMenuContainer.findElements(By.xpath("//div[@aria-label='sale']"));

        for (WebElement link : subLinksSale) {
            System.out.println("SALE SUB LINKS ARE LISTED");
            System.out.println("Sub-link Text: " + link.getText());
            System.out.println("Sub-link URL: " + link.getAttribute("href"));
        }

                try {
                    Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    }


    @And("The main menu item {string} should not have sub menu")
    public void theMainMenuItemShouldNotHaveSubMenu(String mainMenuTextShop) {

        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement mainMenuShop = driver.findElement(By.xpath("//a[@id='shop']" + mainMenuTextShop + "']"));
        action.moveToElement(mainMenuShop).perform();
        System.out.println("Hovered over the item " + mainMenuTextShop);

        // Wait for potential submenu container
        List<WebElement> subMenus = driver.findElements(By.xpath("//a[@id='shop']"));

        if (subMenus.isEmpty()) {
            System.out.println("✅ No submenu items found for: " + mainMenuTextShop);
        } else {
            System.out.println("❌ Submenu items should not be present for: " + mainMenuTextShop);
            for (WebElement link : subMenus) {
                System.out.println("Unexpected sub-link: " + link.getText());
            }
            throw new AssertionError("Submenu items found when none expected for: " + mainMenuTextShop);
        }
    }
}