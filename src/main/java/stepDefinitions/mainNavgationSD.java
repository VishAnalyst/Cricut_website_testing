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

        System.out.println("Cricut website is Loaded");

        //Initiate drive wait
        try {
            Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hover over the "Discover" menu
        WebElement discoverMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='discover']")));
        discoverMenu.click();
        //action.moveToElement(discoverMenu).perform();
        System.out.println("Hovered over Discover menu");

        //Initiate drive wait
        try {
            Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Then("The following headings and sub-links should be visible:")
    public void theFollowingHeadingsAndSubLinksShouldBeVisible(DataTable dataTable) {

        List<Map<String, String>> menuItems = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : menuItems) {
            String heading = row.get("Heading");
            String subLink = row.get("Sub-Link");

            // Locate the heading element
            try {
                WebElement headingElement = driver.findElement(By.xpath("//a[normalize-space()='" + heading + "']"));
                if (headingElement.isDisplayed()) {
                    System.out.println("✅ Heading found: " + heading);
                }
            } catch (Exception e) {
                System.out.println("❌ Heading missing: " + heading);
            }

            // Locate the sub-link element
            try {
                WebElement subLinkElement = driver.findElement(By.xpath("//a[normalize-space()='" + subLink + "']"));
                if (subLinkElement.isDisplayed()) {
                    System.out.println("   ✅ Sub-link found: " + subLink);
                }
            } catch (Exception e) {
                System.out.println("   ❌ Sub-link missing: " + subLink);
            }

        }
    }

    @Then("The main navigation item {string} should be visible")
    public void theMainNavigationItemShouldBeVisible(String navItem) {

        driver.get("https://cricut.com/en-us/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
        driver.manage().window().maximize();

        //Reject button is closed
        WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        RejectAllButton.click();

        //Pop is closed
        WebElement CloseButton = driver.findElement(By.xpath("//button[@aria-hidden='true']"));
        CloseButton.click();

        System.out.println("Cricut website is Loaded");

        //Initiate drive wait
        try {
            Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement item = driver.findElement(By.xpath("//a[normalize-space()='" + navItem + "']"));
        if (item.isDisplayed()) {
            System.out.println("✅ " + navItem + " nav is visible");
        } else {
            System.out.println("❌ " + navItem + " nav is NOT visible");
        }
    }

    @And("The {string} navigation item should not have any sub-links")
    public void theNavigationItemShouldNotHaveAnySubLinks(String navItem) {
        // Since Shop has no submenu dropdown, we can assert it
        WebElement item = driver.findElement(By.xpath("//a[normalize-space()='" + navItem + "']"));
        String ariaHasPopup = item.getAttribute("aria-haspopup");
        if (ariaHasPopup == null || ariaHasPopup.equals("false")) {
            System.out.println("✅ '" + navItem + "' has no sub-links.");
        } else {
            System.out.println("❌ '" + navItem + "' might have sub-links.");
        }
    }


    @And("The {string} navigation item should have the following sub-links:")
    public void theNavigationItemShouldHaveTheFollowingSubLinks(String menuName, List<String> expectedSubLinks) {

        Actions actions = new Actions(driver);

        driver.get("https://cricut.com/en-us/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
        driver.manage().window().maximize();

        //Reject button is closed
        WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        RejectAllButton.click();

        //Pop is closed
        WebElement CloseButton = driver.findElement(By.xpath("//button[@aria-hidden='true']"));
        CloseButton.click();

        System.out.println("Cricut website is Loaded");

        //Initiate drive wait
        try {
            Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hover over the menu
        WebElement menuElement = driver.findElement(By.xpath("//a[@id='sale' and normalize-space()='Sale']"));
        actions.moveToElement(menuElement).perform();
        System.out.println("🟡 Hovered over: " + menuName);

        // Wait for submenu container to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='sale']")));

        // Locate all sub-links under the hovered menu
        List<WebElement> subLinks = driver.findElements(By.xpath("//div[@aria-label='sale']//a"));

        for (String expectedLink : expectedSubLinks) {
            boolean found = subLinks.stream()
                    .anyMatch(el -> el.getText().trim().equalsIgnoreCase(expectedLink));
            if (found) {
                System.out.println("✅ Found: " + expectedLink);
            } else {
                System.out.println("❌ Missing: " + expectedLink);
            }
        }
    }
}
