package com.cliq.task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.cliq.task.*;

public class URLNavigator
{
	public static void goToAccountsURL(ChromeDriver driver, ExtentTest test)
	{

		driver.get("https://accounts.zoho.in/signin?servicename=ZohoChat&signupurl=https://www.zoho.in/cliq/signup.html");
		test.log(Status.INFO, "open Cliq SignIn page");
	}

}
