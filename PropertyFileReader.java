package com.cliq.task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader
{
	public static String getUserDetails(String name) throws IOException
	{
		try
		{

			Properties prop = new Properties();
			FileInputStream ip = new FileInputStream("/Users/roshan-pt6347/eclipse-workspace/CliqTask/src/com/cliq/task/cliqData.properties");
			prop.load(ip);
			name = prop.getProperty(name);

		}
		catch(Exception e)
		{
			System.err.println("Exception in file path");
			e.printStackTrace();
		}
		return name;

	}

	public static String getTestAccountPoliciesFileValue(String policies) throws IOException
	{
		try
		{
			Properties prop = new Properties();
			FileInputStream ip = new FileInputStream("/Users/roshan-pt6347/eclipse-workspace/CliqTask/src/com/cliq/task/policies.properties");
			prop.load(ip);
			policies = prop.getProperty(policies);
		}
		catch(Exception e)
		{
			System.err.println("Exception in file path");
			e.printStackTrace();
		}
		return policies;

	}
}
