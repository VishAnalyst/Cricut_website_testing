package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class mainNavgationSD {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Actions action = new Actions(driver);


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

        //Initiate drive wait
        try {
            Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Cricut website is Loaded");

        WebElement mainNavigation = driver.findElement(By.xpath("//ul[@class='nav-main__nav nav navbar-nav']"));
        if(mainNavigation.isDisplayed()){
            System.out.println("STEP PASSED: Main Navigation is displayed");
        }else{
            System.out.println("STEP FAILED: Main Navigation is displayed");
        }

    }

    @And("The main navigation should have the following items:")
    public void theMainNavigationShouldHaveTheFollowingItems(List<String> expectedMenuItems) {
        // Wait for the main navigation to be visible
        WebElement mainNav = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='nav-main__nav nav navbar-nav']"))
        );

        // Fetch all visible nav items
        List<WebElement> actualMenuElements = mainNav.findElements(By.tagName("li"));

        // Extract text from each nav item
        List<String> actualMenuTexts = actualMenuElements.stream()
                .map(el -> el.getText().trim())
                .filter(text -> !text.isEmpty())
                .collect(Collectors.toList());

        // Validate each expected item is present
        for (String expectedItem : expectedMenuItems) {
            if (actualMenuTexts.contains(expectedItem)) {
                System.out.println("✅ Main nav item found: " + expectedItem);
            } else {
                System.out.println("❌ Main nav item missing: " + expectedItem);
            }
        }
    }

    @When("I hover over the main navigation item {string}")
    public void iHoverOverTheMainNavigationItem(String arg0) {

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

        System.out.println("Cricut website is Loaded");

        // Hover over the "Discover" menu
        WebElement discoverMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='discover']")));
        action.moveToElement(discoverMenu).perform();
        System.out.println("Hovered over Discover menu");

    }

    @Then("The following headings and sub-links should be visible:")
    public void theFollowingHeadingsAndSubLinksShouldBeVisible(DataTable dataTable) {
        List<Map<String, String>> menuItems = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : menuItems) {
            String heading = row.get("Heading");
            String subLink = row.get("Sub-Link");

            // Locate the heading element
            try {
                WebElement headingElement = driver.findElement(By.id("discover_learn_about_cricut" + heading + "']"));
                if (headingElement.isDisplayed()) {
                    System.out.println("✅ Heading found: " + heading);
                }
            } catch (Exception e) {
                System.out.println("❌ Heading missing: " + heading);
            }

            // Locate the sub-link element
            try {
                WebElement subLinkElement = driver.findElement(By.linkText("What Is Cricut?" + subLink + "']"));
                if (subLinkElement.isDisplayed()) {
                    System.out.println("   ✅ Sub-link found: " + subLink);
                }
            } catch (Exception e) {
                System.out.println("   ❌ Sub-link missing: " + subLink);
            }
        }

    }
}
