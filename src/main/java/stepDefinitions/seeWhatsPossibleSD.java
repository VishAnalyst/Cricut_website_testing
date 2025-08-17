package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
}
