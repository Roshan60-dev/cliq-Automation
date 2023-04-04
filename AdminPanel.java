package com.cliq.task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class AdminPanel
{

	public static final By policies = By.cssSelector(".fclq-policies.icon25.mR15"), closeimage = By.cssSelector(".msi-chtclose.cur"), checkbox = By.cssSelector("[data-action=\"enable_policy\"]"), okbutton = By.id("okbtn"),
			slider = By.className("switch-btn"), policySection = By.id("rhs_section"), allPolicy = By.className("policies-listitem"), policiess = By.className("policies-listitem");
	WebDriver driver;
	WebDriverWait wait;

	public AdminPanel(WebDriver driver)
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	public void policiesClick()
	{
		driver.findElement(policies).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(policiess));

	}

	public void closeProfileImageTab(ExtentTest test)
	{
		driver.findElement(closeimage).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(CliqHomePage.profileImage));
		test.log(Status.INFO, "close image icon clicked Sucessfully and operation shift to next driver");
	}

	public void toggleEnableAndDisableOperationPerformed(String policies_text, boolean is_enable, ExtentTest test) throws IOException, InterruptedException
	{

		ArrayList<String> allpolicies = new ArrayList<String>();
		List<WebElement> allOptions = driver.findElements(slider);
		System.out.println(allOptions.size());
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		List<WebElement> allpolicyLocator = driver.findElements(allPolicy);
		for(WebElement policyTitles : allpolicyLocator)
		{
			allpolicies.add(policyTitles.getText());
		}
		System.out.print(allpolicies);
		;
		int i = 0;

		for(String comparePolicy : allpolicies)

		{
			i = i + 1;
			if(comparePolicy.equals(policies_text))
			{
				System.out.println(policies_text);
				System.out.println(i);
				List<WebElement> allbox = driver.findElements(checkbox);
				boolean enable = allbox.get(i - 1).isSelected();
				System.out.println(enable);
				System.out.println(is_enable);
				if(enable != is_enable)
				{
					if(policies_text.equals(PropertyFileReader.getTestAccountPoliciesFileValue("aproval_of_admin_export_data")))
					{
						allpolicyLocator.get(i - 1).findElement(slider).click();

						allpolicies.clear();
						Thread.sleep(3000);
						driver.findElement(okbutton).click();
						break;
					}
					allpolicyLocator.get(i - 1).findElement(slider).click();

				}

			}
		}
		test.log(Status.PASS, "toggle operation for " + policies_text + " worked sucessfully");
		allpolicies.clear();
		driver.close();
		driver.switchTo().window(tabs.get(0));

	}

}
