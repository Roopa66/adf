package adf.testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import adf.base.BaseTest;
import adf.utils.Selenium_Utils;

public class SeleniumExamples extends BaseTest {
	WebDriver driver = null;

	@BeforeSuite
	public void beforeSuite() throws InterruptedException {
		driver = initializeDriver(null);
		// openWebsite("https://rahulshettyacademy.com/locatorspractice/", driver);
		openWebsite("https://www.leafground.com/frame.xhtml", driver);

	}

	@Ignore
	@Test
	public void webPopUp() throws InterruptedException {
//If it’s a web popup like your cookie consent, treat it as a regular HTML element.
		openWebsite("https://www.transfeero.com/en/london-heathrow-airport-"
				+ "transfers-lhr/?source=bingads&msclkid=7238ca97c2d81221c06dad92a0956550", driver);

		Thread.sleep(5000); // Adjust time if necessary

		WebElement acceptButton = driver.findElement(By.xpath("//button[contains(text(),'Accept All')]"));

		acceptButton.click();

		System.out.println("Accepted the cookies!");

	}
@Ignore
	@Test
	public void alert() throws InterruptedException, IOException {
		// driver.get("https://www.leafground.com/frame.xhtml");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//i[@class='pi pi-globe layout-menuitem-icon']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//i[@class='pi pi-fw pi-clone layout-menuitem-icon']")).click();

		WebElement element = driver.findElement(By.xpath("//button[@id='j_idt88:j_idt91']"));
		Selenium_Utils.captureScreenShot(driver, "beforeAlert");
		element.click();
		Boolean alert = Selenium_Utils.isAlertPresent(driver);
		System.out.println(alert);
		Selenium_Utils.alertClick(driver, "Ok");
		Selenium_Utils.captureScreenShot(driver, "afterAlert");
		Thread.sleep(5000);
		WebElement ale = driver.findElement(By.xpath("//button[@id='j_idt88:j_idt93']"));
		Selenium_Utils.captureScreenShot(driver, "beforecancelled");
		ale.click();
		Thread.sleep(5000);
		Selenium_Utils.alertClick(driver, "Cancel");
		Selenium_Utils.captureScreenShot(driver, "aftercancelled");

	}
@Test
public void switchFrames() throws InterruptedException {
	Thread.sleep(5000);
	driver.findElement(By.xpath("//i[@class='pi pi-globe layout-menuitem-icon']")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//li[@id ='menuform:m_frame']")).click();
	
}
@Test 
public void windowHandling() {
	WebElement windows =driver.findElement(By.xpath("//form[@id='j_idt88']"));
	Set<String> wind = driver.getWindowHandles();
	List<String> win = new ArrayList<>(wind);
	
	
	
}
	@AfterSuite
	public void afterSuite() {
		closeWebsite(driver);
	}

}
