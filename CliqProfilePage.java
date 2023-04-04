/**
 * 
 */
package com.cliq.task;

import java.time.Duration;
import java.util.ArrayList;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author roshan-pt6347
 *
 */
public class CliqProfilePage
{
	public static final By adminpanel = By.className("msi-adminpanel"), signout = By.cssSelector(".signout.hvrinfo.cur.mrgL5"), resign = (By.className("zlogin-apps"));

	WebDriver driver;
	WebDriverWait wait;

	public CliqProfilePage(WebDriver driver)
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void clickAdminPanel()
	{
		driver.findElement(adminpanel).click();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AdminPanel.policies));

	}

	public void clickSignOutBtn()
	{
		driver.findElement(signout).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(resign));

	}

	public void clickReSignButton() throws InterruptedException
	{
		driver.findElement(resign).click();

	}

}
