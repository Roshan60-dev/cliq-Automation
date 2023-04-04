/**
 * 
 */
package com.cliq.task;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author roshan-pt6347
 *
 */
public class ChannelElements
{
	public static final By channelCreatePlus = By.id("createchatcontainer"), createChannel = By.id("createchnlwin"), organizationhannel = By.id("channelscope1"), superadmincreatechannel = By.id("channelscope4"),
			channelName = By.cssSelector("[placeholder=\"IT-Support\"]"), channelUserSuggestion = By.id("channels-usersuggest-search-field"), channelDescription = By.id("channeldescval"), createChannelBtn = By.id("createchannel"),
			enterChannelName = By.id("channelnameval");
	WebDriver driver;
	WebDriverWait wait;

	public static final int CHANNEL_TYPE_ORG = 0, CHANNEL_TYPE_External = 1;

	public ChannelElements(WebDriver driver)
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void clickChannelPlusicon()
	{

		WebElement channelCreatePlusButton = driver.findElement(channelCreatePlus);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", channelCreatePlusButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(createChannel));

	}

	public void clickCreateChannel() throws InterruptedException
	{
		WebElement createChannelButton = driver.findElement(createChannel);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", createChannelButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(organizationhannel));

	}

	public void clickOrganizationChannel()
	{

		WebElement organizastionChannel = driver.findElement(organizationhannel);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", organizastionChannel);
		wait.until(ExpectedConditions.visibilityOfElementLocated(channelName));
	}

	public void enterChannelUserSuggestionsAndClickEnter(String newUsersuggest)
	{
		driver.findElement(channelUserSuggestion).sendKeys(newUsersuggest);
		driver.findElement(channelUserSuggestion).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(channelDescription));

	}

	public void enterChannelDescription(String description)
	{
		driver.findElement(channelDescription).sendKeys(description);
		wait.until(ExpectedConditions.visibilityOfElementLocated(createChannelBtn));

	}

	public void enterCreateChannelButton() throws InterruptedException
	{

		WebElement button = driver.findElement(createChannelBtn);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", button);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CliqHomePage.profileImage));
	}

	public void clickExternalChannel()
	{

		WebElement button = driver.findElement(superadmincreatechannel);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", button);
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(enterChannelName));
	}

	public void enterchannelName(String channelname)
	{
		driver.findElement(enterChannelName).sendKeys(channelname);
		wait.until(ExpectedConditions.visibilityOfElementLocated(channelUserSuggestion));
	}

}
