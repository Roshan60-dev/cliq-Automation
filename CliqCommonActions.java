package com.cliq.task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CliqCommonActions
{
	public static ArrayList<String> Messages = new ArrayList<String>();
	static int org_channel = 0;
	static int external_channel = 1;

	public static void login(WebDriver member_Driver, ExtentTest test, String userEmailId, String userPassword) throws InterruptedException
	{
		LoginMain login = new LoginMain(member_Driver);
		login.enterMailAndPressEnterButton(userEmailId);
		test.log(Status.INFO, userEmailId + " email enter sucessfully");
		login.enterPasswordAndPressEnterButton(userPassword);
		test.log(Status.INFO, "password enter Sucessfully");
	}

	public static void openAdminPanelPolicies(WebDriver member_Driver, ExtentTest test) throws InterruptedException
	{
		CliqHomePage cliqHome = new CliqHomePage(member_Driver);
		cliqHome.profileImageClick();
		test.log(Status.INFO, "profile image clicked sucessfully");

		CliqProfilePage cliqProfile = new CliqProfilePage(member_Driver);
		cliqProfile.clickAdminPanel();
		test.log(Status.INFO, "admin panel clicked and tab swithched sucessfully");

		AdminPanel admin = new AdminPanel(member_Driver);
		admin.policiesClick();
		test.log(Status.INFO, "policies button clicked Sucessfully");
	}


	public static void createChannel(WebDriver member_Driver, ExtentTest test, String channel_name, String channel_description, int channelTypeOrg, String user_suggestion) throws InterruptedException
	{

		ChannelElements channel = new ChannelElements(member_Driver);
		channel.clickChannelPlusicon();
		test.log(Status.INFO, "channel plus icon clicked");
		channel.clickCreateChannel();
		test.log(Status.INFO, "create channel button clicked");
		if(channelTypeOrg == org_channel)
		{
			channel.clickOrganizationChannel();
			test.log(Status.PASS, "organization channel is Selected");
		}
		else if(channelTypeOrg == external_channel)
		{
			channel.clickExternalChannel();
			test.log(Status.PASS, "external channel is selected");
		}
		channel.enterchannelName(channel_name);
		test.log(Status.INFO, "channel name Entered is" + channel_name);
		channel.enterChannelUserSuggestionsAndClickEnter(user_suggestion);
		test.log(Status.INFO, user_suggestion + " user suggestion are entered");
		channel.enterChannelDescription(channel_description);
		test.log(Status.INFO, "channel description entered are " + channel_description);
		channel.enterCreateChannelButton();
		test.log(Status.INFO, "final create channel button clicked channel created");
	}

	public static boolean channelNotFound(WebDriver member_Driver, String org_channel_name)
	{
		try
		{
			CliqHomePage cliqHome = new CliqHomePage(member_Driver);
			cliqHome.searchchannelName(org_channel_name);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}

	}

	public static void inviteNonOrganizationUser(ChromeDriver member_Driver, ExtentTest test, boolean is_enable, String invite_user_Email, String Invite_nonOrg_policies) throws InterruptedException, IOException
	{

		ContactElements contact = new ContactElements(member_Driver);
		contact.clickContactBar();
		test.log(Status.INFO, "Contact icon clicked");
		contact.clickPlusContact();
		test.log(Status.INFO, "plus icon to add user is clicked");
		contact.enterInviteContact(invite_user_Email);
		test.log(Status.INFO, invite_user_Email + "contact invite user entered");
		if(is_enable == true)
		{
			try
			{
				contact.clickInviteUserImage();
				test.log(Status.PASS, "visible invite user" + invite_user_Email + " is clicked");
				contact.clickInviteNextButton();
				test.log(Status.INFO, "next button clicked");
				contact.clicksendInviteButton();
				test.log(Status.INFO, "Send invite button clicked,invite send Sucessfully");
				test.log(Status.PASS, "We are able to chat with Non OrganiZation with approval after enabling" + Invite_nonOrg_policies);
			}
			catch(Exception e)
			{
				test.log(Status.FAIL, "We are not able to chat with Non OrganiZation wit approval after enabling" + Invite_nonOrg_policies);
				CliqCommonActions.takeScreenShot(member_Driver, test, "inviteUser");
				
			}

		}
		else if(is_enable == false)
		{
			try
			{
				String displayMessage = contact.getDisplayMesssge();
				Messages.add(displayMessage);
				test.log(Status.PASS, "A message show as could Not invite a person after disable show a message as" + displayMessage);
				CliqHomePage home = new CliqHomePage(member_Driver);
				home.closeicon();
				test.log(Status.INFO, "Close icon clicked Sucessfully");
				test.log(Status.PASS, "We are not able to chat with Non OrganiZation wit approval after enabling" + Invite_nonOrg_policies);

			}
			catch(Exception e)
			{
				test.log(Status.FAIL, "We are able to chat with Non OrganiZation wit approval after enabling" + Invite_nonOrg_policies);
				CliqCommonActions.takeScreenShot(member_Driver, test, "inviteUser");
			}

		}

	}

	public static void joinToExternalChannel(ChromeDriver member_Driver, ExtentTest test, boolean enabled, String join_external_policies) throws InterruptedException, IOException
	{

		ContactElements contact = new ContactElements(member_Driver);
		contact.clickContactBar();
		test.log(Status.INFO, "contact bar clicked");
		if(enabled == true)
		{
			try
			{
				contact.clickBot();
				test.log(Status.PASS, "bot icon clicked");
				contact.clickLinkText();
				test.log(Status.INFO, "LinkText for join channel Clicked");
				contact.clickJoinButton();
				test.log(Status.PASS, "We are able to join external channel after enabling" + join_external_policies);

			}
			catch(Exception e)
			{
				test.log(Status.FAIL, "We are not able to join external channel after enabling" + join_external_policies);
				CliqCommonActions.takeScreenShot(member_Driver, test, "joinchannel");

			}
		}
		else
		{
			try
			{
				contact.clickBot();
				test.log(Status.INFO, "bot icon clicked and No link found to join channel");
				test.log(Status.PASS, "We are not able to join external channel after disabling" + join_external_policies);

			}
			catch(Exception e)
			{
				test.log(Status.FAIL, "We are  able to join external channel after disabling" + join_external_policies);
				CliqCommonActions.takeScreenShot(member_Driver, test, "joinchannel");

			}
		}

	}

	public static void logout(ChromeDriver otherOrganaization_Driver, ExtentTest test) throws InterruptedException
	{
		CliqProfilePage cliqProfile = new CliqProfilePage(otherOrganaization_Driver);
		cliqProfile.clickSignOutBtn();
		test.log(Status.INFO, "sign out link clicked");
		cliqProfile.clickReSignButton();
		test.log(Status.INFO, "Resign button clicked ");
	}



	public static void exportConversation(ChromeDriver member_Driver, ExtentTest test, boolean is_enable, String export_Conversation_policies, String export_password) throws InterruptedException, IOException
	{

		CliqHomePage homePage = new CliqHomePage(member_Driver);
		homePage.profileImageClick();
		test.log(Status.INFO, "profileImage click");

		CliqProfilePage myProfile = new CliqProfilePage(member_Driver);
		myProfile.clickAdminPanel();
		test.log(Status.INFO, "adminpanel clicked");

		ExportElements export = new ExportElements(member_Driver);
		export.clickExportBtn();
		if(is_enable == true)
		{
			try
			{
				export.clickNewExportBtn();
				test.log(Status.INFO, "export button click");
				export.clickMessageCheckBox();
				test.log(Status.INFO, "message checkbox click");
				export.clickIncludeDirectMessageBtn();
				test.log(Status.INFO, "direct message click");
				export.enterExportPassword(export_password);
				test.log(Status.INFO, "password for export entered");
				export.enterExportConfirmPassword(export_password);
				test.log(Status.INFO, "confirm password for export entered");
				export.clickstartexportbtn();
				test.log(Status.INFO, "start export button clicked");
				export.clickfinalexportbtn();
				test.log(Status.INFO, "final export button clicked to export conversation");
				test.log(Status.PASS, "We are able to export conversation with approval after enabling" + export_Conversation_policies);

			}
			catch(Exception e)
			{
				test.log(Status.FAIL, "We are not able to export conversation with approval after enabling" + export_Conversation_policies);
				CliqCommonActions.takeScreenShot(member_Driver, test, "export");
			}

		}
		else if(is_enable == false)
		{
			try
			{
				export.clickanothernewexport();
				test.log(Status.PASS, "We are not able to export conversation with approval after disabling" + PropertyFileReader.getTestAccountPoliciesFileValue("aproval_of_admin_export_data"));

			}
			catch(Exception e)
			{
				test.log(Status.FAIL, "We are able to export conversation with approval after disabling" + PropertyFileReader.getTestAccountPoliciesFileValue("aproval_of_admin_export_data"));
				CliqCommonActions.takeScreenShot(member_Driver, test, "export");
			}

		}

		AdminPanel admin = new AdminPanel(member_Driver);
		admin.closeProfileImageTab(test);
		test.log(Status.INFO, "close image icon clicked");

	}

	public static boolean checkInvite(String userInviteMessage)
	{

		for(String compareMessages : Messages)
		{
			if(compareMessages.equals(userInviteMessage))
			{
				Messages.clear();
				return true;

			}
		}
		return false;

	}

	public static void takeScreenShot(ChromeDriver toDriver, ExtentTest test, String imageName) throws IOException
	{
		TakesScreenshot screen = (TakesScreenshot) toDriver;
		File sourceFile = screen.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(imageName + ".png");
		FileHandler.copy(sourceFile, destinationFile);
		test.addScreenCaptureFromPath(imageName + ".png");
	}

}
