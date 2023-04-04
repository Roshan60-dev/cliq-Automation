/**
 * 
 */
package com.cliq.task;

import java.time.Duration;
import java.util.concurrent.Executor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author roshan-pt6347
 *
 */
public class ContactElements
{
	public static final By contactbar = By.id("lhs_contacts"), plusContact = By.id("addcontact"), inviteContact = By.id("invitecontact-search-field"), inviteUserImage = By.cssSelector(".floatl.adchtlft.posl"),
			inviteNext = By.id("sendinvitenext"), sendInviteButton = By.id("contaddbtn"), displayofpage = By.className("zcempty-title"), topcontact = By.id("topbotcontactslist"), categoryContact = By.cssSelector("[catagory=\"contact\"]"),
			joinbutton = By.id("join"), botimage = By.id("contactuserimgb-247018000000002049"), tazBotChat = By.cssSelector(".hdrwrap.flexC"), actionStatues = By.id("ajaxstatus"), linktext1 = By.linkText("#finalDemoTestingChannel"),
			chatHeader = By.id("header"), closeInviteTab = By.cssSelector(".zcf-closeB.zcl-icon-lg.zcl-icon--plain.zcl-round");
	WebDriver driver;
	WebDriverWait wait;

	public ContactElements(WebDriver driver)
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void clickContactBar() throws InterruptedException
	{

		WebElement contactBar = driver.findElement(contactbar);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", contactBar);
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(plusContact));

	}

	public void clickPlusContact()
	{
		driver.findElement(plusContact).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(inviteContact));

	}

	public void enterInviteContact(String mailid) throws InterruptedException
	{
		driver.findElement(inviteContact).sendKeys(mailid);
	   
		wait.until(ExpectedConditions.visibilityOfElementLocated(closeInviteTab));

	}

	public void clickInviteUserImage()
	{
		driver.findElement(inviteUserImage).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(inviteNext));

	}

	public void clickInviteNextButton()
	{
		driver.findElement(inviteNext).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(sendInviteButton));
	}

	public void clicksendInviteButton() throws InterruptedException
	{
		driver.findElement(sendInviteButton).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(contactbar));

	}

	public String getDisplayMesssge()
	{
		return driver.findElement(displayofpage).getText();
	}

	public void clickLinkText() throws InterruptedException
	{
		driver.findElement(linktext1).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(joinbutton));

	}

	public void clickJoinButton() throws InterruptedException
	{
		driver.findElement(joinbutton).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CliqHomePage.profileImage));
	}

	public void clickBot() throws InterruptedException
	{
		driver.findElement(botimage).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(linktext1));

	}
}
