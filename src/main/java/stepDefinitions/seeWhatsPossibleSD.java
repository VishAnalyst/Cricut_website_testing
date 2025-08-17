package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class seeWhatsPossibleSD {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Actions action = new Actions(driver);

    @Given("I open the Cricut website for See what's possible with Cricut")
    public void iOpenTheCricutWebsiteForSeeWhatSPossibleWithCricut() {
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
        System.out.println("CRICUT WEBSITE IS LOADED");
    }

    @And("I scroll down to the {string} section for See what's possible with Cricut")
    public void iScrollDownToTheSectionForSeeWhatSPossibleWithCricut(String arg0) {
        //scrolldown to see particular element
        WebElement SeewhatpossiblewithCricut = driver.findElement(By.cssSelector("div.sc-1jy8od4-2.hlIpYz[role='heading']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", SeewhatpossiblewithCricut);
        //Initiate drive wait
        try {
            Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (SeewhatpossiblewithCricut.isDisplayed()) {
            System.out.println("WHATS POSSIBLE WITH CRICUT IS LOCATED");
        } else {
            System.out.println("WHATS POSSIBLE WITH CRICUT IS NOT LOCATED");
        }
    }

    @Then("I should see the heading {string} for See what's possible with Cricut")
    public void iShouldSeeTheHeadingForSeeWhatSPossibleWithCricut(String arg0) {
        WebElement SeewhatpossiblewithCricutHeading = driver.findElement(By.cssSelector("div.sc-1jy8od4-2.hlIpYz[role='heading']"));
        String featureTile = SeewhatpossiblewithCricutHeading.getText();
        System.out.println("Tile of feature is: "+featureTile);

        //Initiate drive wait
        try {
            Thread.sleep(5000); // Pause for 5000 milliseconds (5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (SeewhatpossiblewithCricutHeading.isDisplayed()) {
            System.out.println("WHATS POSSIBLE WITH CRICUT IS DISPLAYED");
        } else {
            System.out.println("WHATS POSSIBLE WITH CRICUT IS NOT DISPLAYED");
        }
    }
    @And("I should see the subheading {string}")
    public void iShouldSeeTheSubheading(String expectedText) {
        WebElement description = driver.findElement(
                By.xpath("//div[@role='heading' and normalize-space(text())='" + expectedText + "']")
        );
        String actualText = description.getText();
        if (!actualText.equals(expectedText)) {
            throw new AssertionError("Expected: " + expectedText + " but got: " + actualText);
        }
    }


    @Then("I should see exactly {int} items in the {string} carousel")
    public void iShouldSeeExactlyItemsInTheCarousel(int expectedCount,String carouselName) {
        // locate based on carousel name (for flexibility)
        By locator = null;

        if (carouselName.equals("See what's possible with Cricut")) {
            locator = By.cssSelector("div[role='list'] div[role='listitem']");
        }
        // you can add more carousels here if needed

        List<WebElement> items = driver.findElements(locator);
        int actualCount = items.size();

        Assert.assertEquals(
                "Number of items in " + carouselName + " carousel mismatch",
                expectedCount,
                actualCount
        );
        System.out.println("CONATINER LOCATED");
        System.out.println("Expected: "+ expectedCount);
        System.out.println("Actual: "+ actualCount);


    }


    @And("the items should have the following content for whats possible:")
    public void theItemsShouldHaveTheFollowingContentForWhatsPossible(io.cucumber.datatable.DataTable dataTable) {
        // Convert DataTable to List<Map>
        List<Map<String, String>> expectedItems = dataTable.asMaps(String.class, String.class);

        // Locate all carousel items (title = alt text, description = text)
        List<WebElement> carouselItems = driver.findElements(
                By.cssSelector("div[role='list'] div[role='listitem']")
        );

        // Assert count matches
        Assert.assertEquals("Number of carousel items mismatch",
                expectedItems.size(), carouselItems.size());

        for (int i = 0; i < expectedItems.size(); i++) {
            Map<String, String> expected = expectedItems.get(i);
            WebElement item = carouselItems.get(i);

            // Title (alt text of image)
            WebElement img = item.findElement(By.tagName("img"));
            String actualTitle = img.getAttribute("alt").trim();
            Assert.assertEquals("Title mismatch at item " + (i+1),
                    expected.get("Title (Alt text)"), actualTitle);

            // Description (text below image)
            WebElement desc = item.findElement(By.cssSelector("p, span, div"));
            // adjust depending on site’s structure
            String actualDesc = desc.getText().trim();
            Assert.assertTrue("Description mismatch at item " + (i+1) +
                            "\nExpected: " + expected.get("Description") +
                            "\nActual: " + actualDesc,
                    actualDesc.contains(expected.get("Description")));
        }
    }
}
