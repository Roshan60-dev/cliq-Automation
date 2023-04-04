package com.cliq.task;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExportElements
{
	public static final By adminpanel = By.className("msi-adminpanel"), exportBtn = By.cssSelector("[data-module=\"Export\"]"), newexport = By.cssSelector("[data-action=\"create_new_export_ui\"]"),
			anothernewexport = By.cssSelector("[data-action=\"create_new_export_ui\"]"), messagecheckbox = By.id("input_messages"), includedirectmsg = By.id("input_direct_message_private_channels"), password = By.id("encrypt_password"),
			comfirmpassword = By.id("encrypt_password_confirmation"), startexportbtn = By.cssSelector("[data-action=\"create_export\"]"), finalexportbtn = By.id("okbtn"),
			secondExportButton = By.cssSelector(".btn-pos.text-transU.fshrink.fontB.font13"), exportSection = By.id("rhs_section"), exportContainer = By.id("export_win");

	WebDriver driver;
	WebDriverWait wait;

	public ExportElements(WebDriver driver)
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void clickExportBtn() throws InterruptedException
	{
		driver.findElement(exportBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(exportContainer));
		

	}

	public void clickExportBtnTwo()
	{
		driver.findElement(exportBtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(secondExportButton));

	}

	public void secondNewExortButton()
	{

		driver.findElement(secondExportButton).click();

	}

	public void clickNewExportBtn() throws InterruptedException
	{
		Thread.sleep(3000);		
		WebElement exportButton = driver.findElement(newexport);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", exportButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(messagecheckbox));
	}

	public void clickMessageCheckBox()
	{
		driver.findElement(messagecheckbox).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(includedirectmsg));
	}

	public void clickIncludeDirectMessageBtn()
	{
		driver.findElement(includedirectmsg).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(password));
	}

	public void enterExportPassword(String export_password)
	{
		driver.findElement(password).sendKeys(export_password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(comfirmpassword));
	}

	public void enterExportConfirmPassword(String export_password)
	{
		driver.findElement(comfirmpassword).sendKeys(export_password);
		wait.until(ExpectedConditions.visibilityOfElementLocated(startexportbtn));
	}

	public void clickstartexportbtn()
	{
		driver.findElement(startexportbtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(finalexportbtn));

	}

	public void clickfinalexportbtn()
	{
		driver.findElement(finalexportbtn).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(exportContainer));
		
	}

	public void clickanothernewexport() throws InterruptedException
	{
		
		WebElement exportButton = driver.findElement(newexport);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", exportButton);
	

	}

}
