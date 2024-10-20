package adf.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;

public class Selenium_Utils {

	public static boolean isAlertPresent(WebDriver webDriver) {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());
			webDriver.switchTo().alert();

			flag = true;
		} catch (Exception e) {
		}
		return flag;
	}

	public static void alertClick(WebDriver webDriver, String action) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = webDriver.switchTo().alert();
			if (action == "Ok") {
				alert.accept();
				String msg =alert.getText();
				System.out.println(msg);
				System.out.println("alert accepted");
			} else if (action == "Cancel") {
				String msg =alert.getText();
				System.out.println(msg);
				alert.dismiss();
				System.out.println("alert dismissed");
			} else {
				System.out.println("Invalid action name entered:" + action);
			}
		} catch (Exception e) {
		}
	}
	public static String getFormatedDate(String format) {
		String sDateFormat;
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		Calendar currentDate = Calendar.getInstance();
		sDateFormat = sformat.format(currentDate.getTime());
		return sDateFormat;
	}

	public static void captureScreenShot(WebDriver webDriver, String imagename) {
		String reportPath=ConfigPropertyLoader.getConfigValue("screenshotpath") ;
		String destinationFileName = reportPath + File.separator +imagename +"_"+Selenium_Utils.getFormatedDate("MMddYYYYhhmmss") +".png";
		if (webDriver != null) {
			//getScreenshotAs this method will allow in .png formate only
			File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			 File reportDirectory = new File(reportPath);
			try {
				if(!reportDirectory.exists()) {reportDirectory.mkdirs(); }
				File destinationFile = new File(destinationFileName);
				FileUtils.moveFile(scrFile, destinationFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void switchToFrame(WebDriver webdriver, String sFrameName) {
		try {
			webdriver.switchTo().frame(sFrameName);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
