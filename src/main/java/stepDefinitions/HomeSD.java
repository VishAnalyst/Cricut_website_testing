package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomeSD {
    JavascriptExecutor js;

    @Given("I am on the Login page")
    public void iAmOnTheLoginPage() {
        System.out.println("User is a cricut login page");
        WebDriver driver = new ChromeDriver();
        driver.get("https://cricut.com/en-us/");
        driver.manage().window().maximize();
        //Implicit wait to see the process
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        System.out.println("Home page loads correctly and it is in maximize view");
        //Reject button is closed
        WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        RejectAllButton.click();
        System.out.println("Rejected all coolies is closed");
        //Pop is closed
        WebElement CloseButton = driver.findElement(By.xpath("//button[@aria-hidden='true']"));
        CloseButton.click();
        System.out.println("Pop market promotion is closed");
        driver.close();
        System.out.println("Driver is closed");
    }

    @When("Scrolling to wards the botton on the page")
    public void scrollingToWardsTheBottonOnThePage() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://cricut.com/en-us/");
        driver.manage().window().maximize();
        //Implicit wait to see the process
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Reject button is closed
        WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
        RejectAllButton.click();
        System.out.println("Rejected all coolies is closed");
        //Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Pop up is closed
        WebElement CloseButton = driver.findElement(By.xpath("//button[@aria-hidden='true']"));
        CloseButton.click();
        System.out.println("Close button is closed");
        //Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //Scroll to the bottom
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,10000)","");
        //Implicit wait
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement footerReveal = driver.findElement(By.id("footer-accordion-about"));
        wait.until(d -> footerReveal.isDisplayed());
        System.out.println("Footer copy right message is displayed");
        driver.close();
    }

    @Then ("Close the browser")
        public void Close_the_browser(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://cricut.com/en-us/");
        driver.manage().window().maximize();
        //Implicit wait duration in seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.close();
        System.out.println("Browser is closed");
    }

            //Test 20% marketing banner
            @Given ("I am on the Cricut Home page")
            public void IamontheCricutHomepage(){
                WebDriver driver = new ChromeDriver();
                driver.get("https://cricut.com/en-us/");
                driver.manage().window().maximize();
                //Implicit wait to see the process
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                System.out.println("Home page loads correctly and it is in maximize view");
                driver.quit();
            }

            @When("I enter incorrect emailid")
            public void iEnterIncorrectEmailid() {
                WebDriver driver = new ChromeDriver();
                driver.get("https://cricut.com/en-us/");
                driver.manage().window().maximize();
                //Implicit wait to see the process
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                System.out.println("Home page loads correctly and it is in maximize view");

                //Reject button is closed
                WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
                RejectAllButton.click();
                System.out.println("Rejected all coolies is closed");

                //Finding get 20% off banner
                WebElement get20Banner = driver.findElement(By.className("font-size-h4 mb-6 col-12"));
                System.out.println("Get 20% offer banner is displayed");

                //Finding the Email Field & Passing Incorrect value
                WebElement emailField = driver.findElement(By.id("email"));
                emailField.sendKeys("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
                //Finding Sign up button and click
                WebElement SignUpButton = driver.findElement(By.xpath("//input[@value='Sign Up']"));
                SignUpButton.click();

                Object emailValidity = js.executeScript("return arguments[0].checkValidity();", emailField);
                boolean isEmailValid = Boolean.TRUE.equals(emailValidity);

                if (!isEmailValid) {
                    System.out.println("✅ Email validation failed as expected");
                } else {
                    System.out.println("❌ Email validation did not trigger for invalid input");
                }

                emailField.clear();

                System.out.println("Incorrect Email field is checked");

                driver.quit();
            }


            @When("I enter Incorrect DOB")
            public void iEnterIncorrectDOB() {
                WebDriver driver = new ChromeDriver();
                driver.get("https://cricut.com/en-us/");
                driver.manage().window().maximize();
                //Implicit wait to see the process
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                System.out.println("Home page loads correctly and it is in maximize view");

                //Reject button is closed
                WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
                RejectAllButton.click();
                System.out.println("Rejected all coolies is closed");

                //Finding get 20% off banner
                WebElement get20Banner = driver.findElement(By.className("font-size-h4 mb-6 col-12"));
                System.out.println("Get 20% offer banner is displayed");

                //Finding the DOB Field & Passing Incorrect value
                WebElement dobField = driver.findElement(By.id("dob"));
                dobField.sendKeys("01/50");
                //Finding Sign up button and click
                WebElement SignUpButton = driver.findElement(By.xpath("//input[@value='Sign Up']"));
                SignUpButton.click();

                Object dobValidity = js.executeScript("return arguments[0].checkValidity();", dobField);
                boolean isDobValid = Boolean.TRUE.equals(dobValidity);

                if (!isDobValid) {
                    System.out.println("✅ DOB validation failed as expected");
                } else {
                    System.out.println("❌ DOB validation did not trigger for invalid date");
                }

                dobField.clear();

                System.out.println("Incorrect DOB field is checked");

                driver.quit();
            }


            @When("I enter Correct email and password")
            public void iEnterCorrectEmailAndPassword() {
                WebDriver driver = new ChromeDriver();
                driver.get("https://cricut.com/en-us/");
                driver.manage().window().maximize();
                //Implicit wait to see the process
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                System.out.println("Home page loads correctly and it is in maximize view");

                //Reject button is closed
                WebElement RejectAllButton = driver.findElement(By.id("onetrust-reject-all-handler"));
                RejectAllButton.click();
                System.out.println("Rejected all coolies is closed");

                //Finding get 20% off banner
                WebElement get20Banner = driver.findElement(By.className("font-size-h4 mb-6 col-12"));
                System.out.println("Get 20% offer banner is displayed");

                //Finding the Email Field & Passing correct value
                WebElement emailField = driver.findElement(By.id("email"));
                emailField.sendKeys("Test@gmail.com");

                //Finding the DOB Field & Passing Incorrect value
                WebElement dobField = driver.findElement(By.id("dob"));
                dobField.sendKeys("01/90");
                //Finding Sign up button and click
                WebElement SignUpButton = driver.findElement(By.xpath("//input[@value='Sign Up']"));
                SignUpButton.click();

                Object emailValidity = js.executeScript("return arguments[0].checkValidity();", emailField);
                Object dobValidity = js.executeScript("return arguments[0].checkValidity();", dobField);

                boolean isEmailValid = Boolean.TRUE.equals(emailValidity);
                boolean isDobValid = Boolean.TRUE.equals(dobValidity);

                if (isEmailValid && isDobValid) {
                    System.out.println("✅ Valid email and DOB submitted");
                } else {
                    System.out.println("❌ Validation failed unexpectedly");
                }


                driver.quit();
            }
}
