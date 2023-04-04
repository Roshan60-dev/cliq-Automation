/**
 * 
 */
package com.cliq.task;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author roshan-pt6347
 *
 */
public class CliqHomePage
{
	public static final By profileImage = By.id("ztb-profile-image"), close = By.cssSelector(".zcf-closeB.zcl-icon-lg.zcl-icon--plain.zcl-round"), profiletab = By.className("zc-usermenucontainer"), mainTopBar = By.id("topbar"),
			searchBar = By.id("GS-search-field");

	WebDriver driver;
	WebDriverWait wait;

	public CliqHomePage(WebDriver driver)
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
	}

	public void profileImageClick() throws InterruptedException
	{
		WebElement userProfile = driver.findElement(profileImage);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", userProfile);
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(CliqProfilePage.signout));

	}

	public void closeicon()
	{
		driver.findElement(close).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(mainTopBar));

	}

	public void searchchannelName(String org_channel_name) {
		driver.findElement(searchBar).sendKeys(org_channel_name);
		driver.findElement(searchBar).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(profileImage));
	}
	
	public void refreshThePageToUpdate(ChromeDriver member_Driver) throws InterruptedException {
		member_Driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(mainTopBar));

	}
	
	

}
