package steps;

import static org.testng.Assert.assertEquals;

import java.time.Duration; // Import Duration
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUp  extends BaseSteps{


    @Given("I am on the Demoblaze store sign up page")
    public void i_am_on_the_demoblaze_store_sign_up_page() {
    	
    	driver.get("https://www.demoblaze.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    @When("I click on the Sign Up button")
    public void i_click_on_the_sign_up_button() {
        WebElement signUpButton = driver.findElement(By.id("signin2"));
        signUpButton.click();
    }

    @When("I fill in the sign up form with username {string} and password {string}")
    public void i_fill_in_the_sign_up_form_with_username_and_password(String username, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-username"))).click();
        WebElement usernameField = driver.findElement(By.id("sign-username"));
        usernameField.sendKeys(username);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-password"))).click();
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        passwordField.sendKeys(password);
    }

    @When("I submit the form")
    public void i_submit_the_form() {
        WebElement signUpSubmitButton = driver.findElement(By.xpath("//button[contains(text(),'Sign up')]"));
        signUpSubmitButton.click();
    }

    @Then("I should see the message {string}")
    public void i_should_see_the_message(String expectedMessage) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set timeout as necessary
        wait.until(ExpectedConditions.alertIsPresent());
        String actualMessage = driver.switchTo().alert().getText();
        assertEquals(actualMessage, expectedMessage);
        driver.switchTo().alert().accept();
    }
}
