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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author roshan-pt6347
 *
 */
public class LoginMain
{

	public static final By emailid = By.id("login_id"), password = By.id("password"), emailSkip = By.cssSelector("[onclick=\"skippedShowNextAnnouncement()\"]");

	WebDriver driver;
	WebDriverWait wait;

	public LoginMain(WebDriver driver)
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void enterMailAndPressEnterButton(String mailid)
	{
		driver.findElement(emailid).sendKeys(mailid);
		driver.findElement(emailid).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(password));
	}

	public void enterPasswordAndPressEnterButton(String userpassword) throws InterruptedException
	{
		driver.findElement(password).sendKeys(userpassword);
		driver.findElement(password).sendKeys(Keys.ENTER);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(CliqHomePage.mainTopBar));

	}

}
