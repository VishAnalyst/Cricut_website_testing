package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class HowitWorksSD {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Actions action = new Actions(driver);

    @Then("I should see the section heading {string}")
    public void iShouldSeeTheSectionHeading(String headingText) {

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

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1500);"); // Scrolls down by 500 pixels

        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='pd-horizontal-gallery__heading projects-gallery-header' and normalize-space()='" + headingText + "']")));
        assertTrue("Heading is not displayed!", heading.isDisplayed());
    }


    @When("I scroll to the {string} section")
    public void iScrollToTheSection(String sectionHeading) {

        driver.get("https://cricut.com/en-us/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(150));
        //driver.manage().window().maximize();


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

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1500);"); // Scrolls down by 500 pixels

        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='pd-horizontal-gallery__heading projects-gallery-header' and normalize-space()='" + sectionHeading + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", section);
    }


    @And("I click the {string} scroll button")
    public void iClickTheScrollButton(String direction) {
        String buttonXpath = "";
        if (direction.equalsIgnoreCase("Next")) {
            buttonXpath = "//button[@class='gallery-next btn slick-arrow']";
        } else if (direction.equalsIgnoreCase("Previous")) {
            buttonXpath = "//button[@class='gallery-next btn slick-arrow']";
        }
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath)));
        button.click();
    }

    @Then("more content should be visible in the {string} section")
    public void moreContentShouldBeVisibleInTheSection(String arg0) {
        // Here we check by visibility of a new gallery item (dynamic detection)
        WebElement galleryContainer = driver.findElement(By.xpath("//div[contains(@class,'pd-horizontal-gallery__header')]/following-sibling::div"));
        Long scrollLeft = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollLeft;", galleryContainer);
        assertTrue("Gallery did not scroll right!", scrollLeft > 0);
    }

    @Then("the earlier content should be visible again")
    public void the_earlier_content_should_be_visible_again() {
        WebElement galleryContainer = driver.findElement(By.xpath("//div[contains(@class,'pd-horizontal-gallery__header')]/following-sibling::div"));
        Long scrollLeft = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollLeft;", galleryContainer);
        assertTrue("Gallery did not scroll back to start!", scrollLeft == 0);
    }

    @Then("I should see the item {string} in the gallery")
    public void i_should_see_the_item_in_the_gallery(String itemName) {
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'pd-horizontal-gallery__header')]/following-sibling::div//*[normalize-space()='" + itemName + "']")));
        assertTrue("Item not found in gallery: " + itemName, item.isDisplayed());
    }
}
