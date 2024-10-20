package adf.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import adf.utils.ConfigPropertyLoader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	WebDriver driver = null;

	public WebDriver initializeDriver(String driverType) {
		
		if (driverType == null || driverType.isEmpty()) {
			driverType = ConfigPropertyLoader.getConfigValue("browserName");
		}

		if (driverType.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else if (driverType.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser type '" + driverType + "' not supported.");
		}

		return driver;
	}

	public void openWebsite(String url, WebDriver driver) throws InterruptedException {
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(Duration.ofSeconds(5).toMillis());
	}

	public void closeWebsite(WebDriver driver) {
		if (driver != null) {
			driver.quit();
		}
	}
}
