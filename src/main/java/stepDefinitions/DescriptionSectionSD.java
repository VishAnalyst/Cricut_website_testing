package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class DescriptionSectionSD {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Actions action = new Actions(driver);

    @Given("I am on the Cricut homepage")
    public void iAmOnTheCricutHomepage() {
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

    @When("I scroll to the {string} section under the banner")
    public void iScrollToTheSectionUnderTheBanner(String sectionName) {
        WebElement sectionHeading = driver.findElement(
                By.xpath("//h4[normalize-space()='" + sectionName + "']")
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", sectionHeading);

    }

    @Then("I should see the heading {string}")
    public void iShouldSeeTheHeading(String expectedHeading) {
        WebElement headingElement = driver.findElement(
                By.xpath("//h4[normalize-space()='" + expectedHeading + "']")
        );
        assertTrue("Heading is not displayed", headingElement.isDisplayed());
        assertEquals(expectedHeading, headingElement.getText().trim());
    }

    @And("I should see the description text:")
    public void iShouldSeeTheDescriptionText(String expectedDescription) {
        WebElement descriptionElement = driver.findElement(
                By.xpath("//h3[contains(normalize-space(), 'Cricut® makes smart cutting machines')]")
        );
        assertTrue("Description is not displayed", descriptionElement.isDisplayed());
        assertEquals(expectedDescription.trim(), descriptionElement.getText().trim());
        driver.quit();
    }
}
