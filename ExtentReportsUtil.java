package com.cliq.task;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsUtil
{
	public static ExtentReports init()
	{
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("CliqAutomation.html");
		extent.attachReporter(spark);
		return extent;
	}

}
