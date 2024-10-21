package steps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Purchase extends BaseSteps  {

	int ExpectedTotalPrice = 1490 ;


	
    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {

    	driver.get("https://www.demoblaze.com");
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login2"))).click();

    }

    @When("User Fill username {string} and password {string} and click at log in")
    public void user_fill_username_and_password_and_click_at_log_in_button(String username, String password) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
    	usernameField.sendKeys(username);
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginpassword")));
        passwordField.sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
    }

    @Then("User {string} should be logged in successfully")
    public void user_should_be_logged_in_successfully(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        WebElement welcomeText = driver.findElement(By.id("nameofuser"));
        String text = welcomeText.getText();
        assertEquals("Welcome " + username, text);
    }

    @When("User selects Laptops category and Choose First product and click addToCart")
    public void userSelectsLaptopsCategoryAndChooseFirstProductAndClickAddToCartButton() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Laptops")));
        driver.findElement(By.linkText("Laptops")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("MacBook air")));
        driver.findElement(By.linkText("MacBook air")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add to cart")));
        driver.findElement(By.linkText("Add to cart")).click();


    }

    @Then("First product should be added successfully")
    public void product_should_be_added_successfully() {
    	wait.until(ExpectedConditions.alertIsPresent());
        String actualMessage0 = driver.switchTo().alert().getText();
        assertEquals(actualMessage0, "Product added.");
        System.out.println("Doneeeeeeee");
        driver.switchTo().alert().accept();

        driver.navigate().back();
        driver.navigate().back();
    }

    @When("User Choose Second product  and click addToCart")
    public void userChooseSecondProductAndClickAddToCartButton() {
    	
    	driver.findElement(By.linkText("Sony vaio i7")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add to cart")));
        driver.findElement(By.linkText("Add to cart")).click();

        
    }

    @Then("Second product should be added successfully")
    public void secondProductShouldBeAddedSuccessfully() {
    	wait.until(ExpectedConditions.alertIsPresent());
        String actualMessage1 = driver.switchTo().alert().getText();
        assertEquals(actualMessage1, "Product added.");
        driver.switchTo().alert().accept();
        driver.navigate().back();
        driver.navigate().back();
    }

    @When("User Click on Cart button in header")
    public void user_click_on_cart_button_in_header() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur"))).click();
    }

    @Then("User view products  and total amount is calculated correctly")
    public void userViewProductsAndTotalAmountIsCalculatedCorrectly() throws InterruptedException {

    	Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
        WebElement table = driver.findElement(By.id("tbodyid"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("tr")));
        Thread.sleep(1000);
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        List<String> productList = new ArrayList<>();
        for (WebElement row : rows) {

            System.out.print(row.getText() + "\n");

            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("td")));
            List<WebElement> cells = row.findElements(By.tagName("td"));

            System.out.print(cells.get(1).getText());
            productList.add( cells.get(1).getText() ) ;

            System.out.print(cells.get(2).getText());
            productList.add(cells.get(2).getText()) ;
        }

        System.out.print(productList);

        assertTrue(productList.contains("Sony vaio i7"));
        assertTrue(productList.contains("790"));
        assertTrue(productList.contains("MacBook air"));
        assertTrue(productList.contains("700"));


        int actualTotalPrice = Integer.parseInt(productList.get(1)) + Integer.parseInt(productList.get(3));
        assertEquals(actualTotalPrice, ExpectedTotalPrice);
    }

    @When("User Click on Place Holder  button in header")
    public void user_click_on_place_holder_button_in_header() {
        driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
    }

    @Then("User view total amount is calculated correctly")
    public void user_view_total_amount_is_calculated_correctly() {
        WebElement placeholderTotal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totalm")));
        String totalPlace = placeholderTotal.getText();
        String[] parts = totalPlace.split(" ");
        int finalTotalPrice = Integer.parseInt(parts[1]);
        assertEquals(finalTotalPrice, ExpectedTotalPrice);
    }

    @When("I fill in the purchase form :")
    public void i_Fill_In_The_PurchaseForm(io.cucumber.datatable.DataTable dataTable) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        for (Map<String, String> data : dataTable.asMaps(String.class, String.class)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys(data.get("Name"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country"))).sendKeys(data.get("Country"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city"))).sendKeys(data.get("City"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("card"))).sendKeys(data.get("Credit Card"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("month"))).sendKeys(data.get("Month"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("year"))).sendKeys(data.get("Year"));
        }
    }

    @And("Click on Purchase button in place holder Form")
    public void clickOnPurchaseButtonInPlaceHolderForm() {
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
    }

    @Then("User should see the message {string}")
    public void userShouldSeeTheMessage(String actualMessage) {
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Thank you for your purchase!']")));
        assertTrue(successMessage.getText().contains("Thank you for your purchase"));
    }
    



}
