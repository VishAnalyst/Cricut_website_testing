package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class HowItWorksSD {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Actions action = new Actions(driver);

    @Given("I open the Cricut website")
    public void iOpenTheCricutWebsite() {
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
        js.executeScript("window.scrollBy(0, 500);"); // Scrolls down by 500 pixels
    }


    @And("I scroll down to the {string} section")
    public void iScrollDownToTheSection(String arg0) {

        //scrolldown to see particular element
        WebElement howItWorksElement = driver.findElement(By.xpath("//div[@class='pd-horizontal-gallery__heading projects-gallery-header']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", howItWorksElement);
        //Initiate drive wait
        try {
            Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (howItWorksElement.isDisplayed()) {
            System.out.println("HOW IT WORKS IS LOCATED");
        } else {
            System.out.println("HOW IT WORKS IS NOT LOCATED");
        }

    }

    @Then("I should see the {string} section on the page")
    public void iShouldSeeTheSectionOnThePage(String arg0) {
        WebElement howItWorksElement = driver.findElement(By.xpath("//div[@class='pd-horizontal-gallery__heading projects-gallery-header']"));
        if (howItWorksElement.isDisplayed()) {
            System.out.println("HOW IT WORKS IS DISPLAYED");
        } else {
            System.out.println("HOW IT WORKS IS NOT DISPLAYED");
        }
    }

    @Then("I should see exactly {int} items in the {string} section")
    public void iShouldSeeExactlyItemsInTheSection(int expectedCount, String arg1) {
        // Locate all visible items inside the How it works container
        List<WebElement> howItWorksItems = driver.findElements(
                By.cssSelector(".experience-component.experience-commerce_assets-pdCard.slick-slide.slick-active")
        );

        // Get the actual count
        int actualCount = howItWorksItems.size();

        // Assertion
        Assert.assertEquals("Number of items in How it works section mismatch", expectedCount, actualCount);

    }


    @And("the items should have the following content:")
    public void theItemsShouldHaveTheFollowingContent(io.cucumber.datatable.DataTable dataTable) {

        // Convert the Gherkin table into a list of maps
        List<Map<String, String>> expectedItems = dataTable.asMaps(String.class, String.class);

        // Locate all the visible cards in the "How it works" section
        List<WebElement> howItWorksCards = driver.findElements(
                By.cssSelector(".experience-component.experience-commerce_assets-pdCard.slick-slide.slick-active")
        );

        // Sanity check
        Assert.assertEquals("Number of visible cards mismatch!", expectedItems.size(), howItWorksCards.size());

        // Loop through each card and compare title + description
        for (int i = 0; i < expectedItems.size(); i++) {
            WebElement card = howItWorksCards.get(i);

            // Extract title & description text
            String actualTitle = card.findElement(By.cssSelector(".card-title")).getText().trim();
            String actualDescription = card.findElement(By.cssSelector(".card-text")).getText().trim();

            // Get expected values from the table
            String expectedTitle = expectedItems.get(i).get("Title").trim();
            String expectedDescription = expectedItems.get(i).get("Description").trim();

            // Compare (ignoring any numbering in titles like "1. Get inspired")
            Assert.assertTrue("Card title mismatch. Expected: " + expectedTitle + " but got: " + actualTitle,
                    actualTitle.contains(expectedTitle));

            Assert.assertEquals("Card description mismatch for " + expectedTitle,
                    expectedDescription, actualDescription);
        }
        driver.quit();
    }


    @When("I click the {string} scroll button in the {string} section")
    public void iClickTheScrollButtonInTheSection(String arg0, String arg1) {
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
        js.executeScript("window.scrollBy(0, 500);"); // Scrolls down by 500 pixels

        //scrolldown to see particular element
        WebElement howItWorksElement = driver.findElement(By.xpath("//div[@class='pd-horizontal-gallery__heading projects-gallery-header']"));
        JavascriptExecutor jsHowitWorks = (JavascriptExecutor) driver;
        jsHowitWorks.executeScript("arguments[0].scrollIntoView(true);", howItWorksElement);
        //Initiate drive wait
        try {
            Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (howItWorksElement.isDisplayed()) {
            System.out.println("HOW IT WORKS IS LOCATED");
        } else {
            System.out.println("HOW IT WORKS IS NOT LOCATED");
        }

        WebElement nextButton = driver.findElement(By.cssSelector("button.gallery-next.btn.slick-arrow"));
        if (nextButton.isDisplayed()) {
            System.out.println("NEXT BUTTON IS DISPLAYED AND LOCATED");
        } else {
            System.out.println("NEXT BUTTON IS NOT DISPLAYED OR LOCATED");
        }
        //Initiate drive wait
        try {
            Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nextButton.click();
        System.out.println("NEXT BUTTON IS CLICKED");
    }


    @Then("I should see a new item with the title {string}")
    public void iShouldSeeANewItemWithTheTitle(String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement newCardTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='card-title' and contains(text(),'" + expectedTitle + "')]")
        ));

        Assert.assertTrue("Expected new item with title: " + expectedTitle,
                newCardTitle.isDisplayed());

    }

    @And("its description should be {string}")
    public void itsDescriptionShouldBe(String expectedDescription) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement descriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[@class='card-text' and normalize-space()='" + expectedDescription + "']")
        ));

        String actualDescription = descriptionElement.getText().trim();

        Assert.assertEquals("Description does not match!", expectedDescription, actualDescription);
        driver.quit();
    }

}
