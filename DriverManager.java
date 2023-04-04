package com.cliq.task;

import java.util.ArrayList;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager
{
	public static ChromeDriver launchAndGetChromeDriver(int port)
	{
		System.setProperty("webdriver.chrome.driver", "/Users/roshan-pt6347/Downloads/chromedriver_mac64 (4)/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "localhost:" + port);
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		return driver;
	}
	public static void driverTabSwitched(ChromeDriver driver)
	{
		driver.close();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}

}
