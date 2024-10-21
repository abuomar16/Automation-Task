package steps;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSteps {
	protected WebDriver driver;
	public static WebDriverWait wait;

	public BaseSteps() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		 // Set timeout as necessary
	}

	@AfterTest
	public void teardown() {
		// Close the browser and end the session
		if (driver != null) {
			driver.quit(); // Quits all browser windows and ends the session
		}
	}
}
