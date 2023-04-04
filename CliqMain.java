package com.cliq.task;

import java.io.IOException;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CliqMain
{

	public static void main(String[] args) throws InterruptedException, IOException
	{

		ExtentReports extent = ExtentReportsUtil.init();

		try
		{
			ChromeDriver admin_Driver = DriverManager.launchAndGetChromeDriver(1111);
			ChromeDriver member_Driver = DriverManager.launchAndGetChromeDriver(9999);
			ChromeDriver otherOrganaization_Driver = DriverManager.launchAndGetChromeDriver(1234);

			ExtentTest etest = extent.createTest("Enable and Disable Allow users to create Organization level Channel without approval");
			enableAndDisableAllowUsersToCreateOrganizationLevelChannelWithoutApproval(admin_Driver, member_Driver, etest);

			etest = extent.createTest("Enable and Disable Allow chat with users who are not a part of this organization");
			enableAndDisableAllowChatWithUsersWhoArenotPartOfThisOrganization(admin_Driver, member_Driver, etest);

			etest = extent.createTest("Enable and Disable users to join External Channels of other Organization");
			enableAndDisableAllowUsersToJoinExternalChannelsOfOtherOrganization(admin_Driver, member_Driver, otherOrganaization_Driver, etest);

			etest = extent.createTest("Enable and Disable Allow admins to export private conversations as part of your company's data export");
			enableAndDisableAllowAdminsToExportPrivateConversationsAsPartOfYourCompanysDataExport(admin_Driver, member_Driver, otherOrganaization_Driver, etest);

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			extent.flush();
		}

	}

	public static void enableAndDisableAllowUsersToCreateOrganizationLevelChannelWithoutApproval(ChromeDriver admin_Driver, ChromeDriver member_Driver, ExtentTest test) throws InterruptedException, IOException
	{
		enableChannelCreationAdminApprovalAndCreatingOrganizationChannel(admin_Driver, member_Driver, test);
		disableChannelCreationAdminApprovalAndCreatingOrganizationChannel(admin_Driver, member_Driver, test);
	}

	public static void enableAndDisableAllowChatWithUsersWhoArenotPartOfThisOrganization(ChromeDriver admin_Driver, ChromeDriver member_Driver, ExtentTest test) throws InterruptedException, IOException
	{
		enableChatWithNonorganizationAndTryToChatInviteWithNonOrgannization(admin_Driver, member_Driver, test);
		disableChatWithNonorganizationAndTryToChatInviteWithNonOrgannization(admin_Driver, member_Driver, test);

	}

	public static void enableAndDisableAllowUsersToJoinExternalChannelsOfOtherOrganization(ChromeDriver admin_Driver, ChromeDriver member_Driver, ChromeDriver otherOrganaization_Driver, ExtentTest test) throws IOException

	{
		enableJoinExternalChannelAndCreateExternalChannelAndJoinToChannel(admin_Driver, member_Driver, otherOrganaization_Driver, test);
		disableJoinExternalChannelAndCreateExternalChannelAndTryJoinToChannel(admin_Driver, member_Driver, otherOrganaization_Driver, test);

	}

	public static void enableAndDisableAllowAdminsToExportPrivateConversationsAsPartOfYourCompanysDataExport(ChromeDriver admin_Driver, ChromeDriver member_Driver, ChromeDriver otherOrganaization_Driver, ExtentTest test)
			throws IOException, InterruptedException
	{
		enableExportPrivateConversationAndTrytoExportPrivateConversation(admin_Driver, member_Driver, test);
		disableExportPrivateConversationAndTrytoExportPrivateConversation(admin_Driver, member_Driver, test);

	}

	public static void enableChannelCreationAdminApprovalAndCreatingOrganizationChannel(ChromeDriver admin_Driver, ChromeDriver member_Driver, ExtentTest test) throws InterruptedException, IOException
	{

		try
		{
			String org_channel_name = "AutomateChannel"+System.currentTimeMillis();
			String channel_description = "demo channel creation " + System.currentTimeMillis();

			CliqCommonActions.login(admin_Driver, test, PropertyFileReader.getUserDetails("adminUserEmail"), PropertyFileReader.getUserDetails("password"));

			CliqCommonActions.openAdminPanelPolicies(admin_Driver, test);
			AdminPanel admin = new AdminPanel(admin_Driver);
			admin.toggleEnableAndDisableOperationPerformed(PropertyFileReader.getTestAccountPoliciesFileValue("no_approval_Organization_level_Channel_text"), true, test);
			admin.closeProfileImageTab(test);

			URLNavigator.goToAccountsURL(member_Driver, test);
			CliqCommonActions.login(member_Driver, test, PropertyFileReader.getUserDetails("roshan28Email"), PropertyFileReader.getUserDetails("password"));
			CliqCommonActions.createChannel(member_Driver, test, org_channel_name, channel_description, ChannelElements.CHANNEL_TYPE_ORG, PropertyFileReader.getUserDetails("adminUserEmail"));

			if(!CliqCommonActions.channelNotFound(member_Driver, org_channel_name))
			{
				test.log(Status.FAIL, "We are not able to create org channel " + org_channel_name + " without approval after enabling" + PropertyFileReader.getTestAccountPoliciesFileValue("no_approval_Organization_level_Channel_text"));
				CliqCommonActions.takeScreenShot(admin_Driver, test, "channelFail");
			}
			else
			{
				test.log(Status.PASS, "We are  able to create org channel " + org_channel_name + " without approval after enabling" + PropertyFileReader.getTestAccountPoliciesFileValue("no_approval_Organization_level_Channel_text"));

			}

		}

		catch(Exception e)
		{

			test.log(Status.FAIL, e);
			CliqCommonActions.takeScreenShot(admin_Driver, test, "channelcreationEnable");
		}

	}

	public static void disableChannelCreationAdminApprovalAndCreatingOrganizationChannel(ChromeDriver admin_Driver, ChromeDriver member_Driver, ExtentTest test) throws InterruptedException, IOException
	{

		try
		{
		
			String org_channel_name = "AutomateCliqDemo " + System.currentTimeMillis();
			String channel_description = "demo channel creation " + System.currentTimeMillis();
			String user_suggestion = PropertyFileReader.getUserDetails("roshan28Email2");

			CliqCommonActions.openAdminPanelPolicies(admin_Driver, test);
			AdminPanel admin = new AdminPanel(admin_Driver);
			admin.toggleEnableAndDisableOperationPerformed(PropertyFileReader.getTestAccountPoliciesFileValue("no_approval_Organization_level_Channel_text"), false, test);
			admin.closeProfileImageTab(test);
			CliqCommonActions.createChannel(member_Driver, test, org_channel_name, channel_description, ChannelElements.CHANNEL_TYPE_ORG, user_suggestion);

			if(CliqCommonActions.channelNotFound(admin_Driver, org_channel_name))
			{
				test.log(Status.PASS, "We are not able to create org channel " + org_channel_name + " without approval after disabling" + PropertyFileReader.getTestAccountPoliciesFileValue("no_approval_Organization_level_Channel_text"));

			}
			else
			{
				test.log(Status.FAIL, "We are  able to create org channel " + org_channel_name + " without approval after disabling" + PropertyFileReader.getTestAccountPoliciesFileValue("no_approval_Organization_level_Channel_text"));
				CliqCommonActions.takeScreenShot(admin_Driver, test, "channelFail");
			}

		}

		catch(Exception e)
		{

			test.log(Status.FAIL, e);
			CliqCommonActions.takeScreenShot(admin_Driver, test, "channelcreationDisable");
		}

	}

	public static void enableChatWithNonorganizationAndTryToChatInviteWithNonOrgannization(ChromeDriver admin_Driver, ChromeDriver member_Driver, ExtentTest test) throws InterruptedException, IOException
	{
		try
		{
			CliqCommonActions.openAdminPanelPolicies(admin_Driver, test);

			AdminPanel admin = new AdminPanel(admin_Driver);
			admin.toggleEnableAndDisableOperationPerformed(PropertyFileReader.getTestAccountPoliciesFileValue("aproval_to_user_chat_with_otherOrganization_User"), true, test);
			admin.closeProfileImageTab(test);

			CliqHomePage home = new CliqHomePage(member_Driver);
			home.refreshThePageToUpdate(member_Driver);
			String invite_user_Email = PropertyFileReader.getUserDetails("inviteEmail");
			CliqCommonActions.inviteNonOrganizationUser(member_Driver, test, true, invite_user_Email, PropertyFileReader.getTestAccountPoliciesFileValue("aproval_to_user_chat_with_otherOrganization_User"));

		}
		catch(Exception e)
		{
			test.log(Status.FAIL, e);
			CliqCommonActions.takeScreenShot(admin_Driver, test, "inviteUserEnable");
		}

	}

	public static void disableChatWithNonorganizationAndTryToChatInviteWithNonOrgannization(ChromeDriver admin_Driver, ChromeDriver member_Driver, ExtentTest test) throws InterruptedException, IOException
	{
		try
		{
			CliqCommonActions.openAdminPanelPolicies(admin_Driver, test);

			AdminPanel admin = new AdminPanel(admin_Driver);
			admin.toggleEnableAndDisableOperationPerformed(PropertyFileReader.getTestAccountPoliciesFileValue("aproval_to_user_chat_with_otherOrganization_User"), false, test);
			admin.closeProfileImageTab(test);

			CliqHomePage home = new CliqHomePage(member_Driver);
			home.refreshThePageToUpdate(member_Driver);
			String invite_user_Email = PropertyFileReader.getUserDetails("inviteEmail");
			CliqCommonActions.inviteNonOrganizationUser(member_Driver, test, false, invite_user_Email, PropertyFileReader.getTestAccountPoliciesFileValue("aproval_to_user_chat_with_otherOrganization_User"));

		}
		catch(Exception e)
		{
			test.log(Status.FAIL, e);
			CliqCommonActions.takeScreenShot(member_Driver, test, "inviteUserDisable");
		}

	}

	public static void enableJoinExternalChannelAndCreateExternalChannelAndJoinToChannel(ChromeDriver admin_Driver, ChromeDriver member_Driver, ChromeDriver otherOrganaization_Driver, ExtentTest test) throws IOException
	{
		try
		{
			CliqCommonActions.openAdminPanelPolicies(admin_Driver, test);

			AdminPanel admin = new AdminPanel(admin_Driver);
			admin.toggleEnableAndDisableOperationPerformed(PropertyFileReader.getTestAccountPoliciesFileValue("approval_to_user_join_external_channel"), true, test);
			admin.closeProfileImageTab(test);

			String external_channel_name = "newExternalChannel ";
			String channel_description = "demo external channel creation " + System.currentTimeMillis();
			String user_suggestion = PropertyFileReader.getUserDetails("roshan28Email");

			CliqCommonActions.login(otherOrganaization_Driver, test, PropertyFileReader.getUserDetails("externalChannelEmail"), PropertyFileReader.getUserDetails("password"));
			CliqCommonActions.createChannel(otherOrganaization_Driver, test, external_channel_name, channel_description, ChannelElements.CHANNEL_TYPE_External, user_suggestion);

			CliqHomePage home = new CliqHomePage(member_Driver);
			home.refreshThePageToUpdate(member_Driver);
			CliqCommonActions.joinToExternalChannel(member_Driver, test, true, PropertyFileReader.getTestAccountPoliciesFileValue("approval_to_user_join_external_channel"));

		}
		catch(Exception e)
		{
			test.log(Status.FAIL, e);
			CliqCommonActions.takeScreenShot(member_Driver, test, "joinChannelEnable");
		}
	}

	public static void disableJoinExternalChannelAndCreateExternalChannelAndTryJoinToChannel(ChromeDriver admin_Driver, ChromeDriver member_Driver, ChromeDriver otherOrganaization_Driver, ExtentTest test) throws IOException
	{
		try
		{
			CliqCommonActions.openAdminPanelPolicies(admin_Driver, test);

			AdminPanel admin = new AdminPanel(admin_Driver);
			admin.toggleEnableAndDisableOperationPerformed(PropertyFileReader.getTestAccountPoliciesFileValue("approval_to_user_join_external_channel"), false, test);
			admin.closeProfileImageTab(test);

			String external_channel_name = "ExternalAutomateCliqTwo";
			String channel_description = "demo external channel creation " + System.currentTimeMillis();
			String user_suggestion = PropertyFileReader.getUserDetails("roshan28Email2");

			CliqCommonActions.createChannel(otherOrganaization_Driver, test, external_channel_name, channel_description, ChannelElements.CHANNEL_TYPE_External, user_suggestion);

			CliqHomePage home = new CliqHomePage(member_Driver);
			home.refreshThePageToUpdate(member_Driver);
			CliqCommonActions.joinToExternalChannel(member_Driver, test, false, PropertyFileReader.getTestAccountPoliciesFileValue("approval_to_user_join_external_channel"));

		}
		catch(Exception e)
		{
			test.log(Status.FAIL, e);
			CliqCommonActions.takeScreenShot(member_Driver, test, "joinChannelDisable");
		}
	}

	public static void enableExportPrivateConversationAndTrytoExportPrivateConversation(ChromeDriver super_Admin, ChromeDriver org_admin, ExtentTest test) throws InterruptedException, IOException
	{
		String export_password = "Roshan12345";
		try
		{
			CliqCommonActions.openAdminPanelPolicies(super_Admin, test);

			AdminPanel admin = new AdminPanel(super_Admin);
			admin.toggleEnableAndDisableOperationPerformed(PropertyFileReader.getTestAccountPoliciesFileValue("aproval_of_admin_export_data"), true, test);
			admin.closeProfileImageTab(test);

			CliqHomePage home = new CliqHomePage(org_admin);
			home.refreshThePageToUpdate(org_admin);
			CliqCommonActions.exportConversation(org_admin, test, true, PropertyFileReader.getTestAccountPoliciesFileValue("aproval_of_admin_export_data"),export_password);
			 DriverManager.driverTabSwitched(org_admin);
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, e);
			CliqCommonActions.takeScreenShot(org_admin, test, "exportEnable");
		}

	}

	public static void disableExportPrivateConversationAndTrytoExportPrivateConversation(ChromeDriver super_Admin, ChromeDriver org_admin, ExtentTest test) throws InterruptedException, IOException
	{
		String export_password = "Roshan12345";
		try
		{

			CliqCommonActions.openAdminPanelPolicies(super_Admin, test);

			AdminPanel admin = new AdminPanel(super_Admin);
			admin.toggleEnableAndDisableOperationPerformed(PropertyFileReader.getTestAccountPoliciesFileValue("aproval_of_admin_export_data"), false, test);
			admin.closeProfileImageTab(test);

			CliqHomePage home = new CliqHomePage(org_admin);
			home.refreshThePageToUpdate(org_admin);
			CliqCommonActions.exportConversation(org_admin, test, false, PropertyFileReader.getTestAccountPoliciesFileValue("aproval_of_admin_export_data"),export_password);
            DriverManager.driverTabSwitched(org_admin);
		}

		catch(Exception e)
		{
			test.log(Status.FAIL, e);
			CliqCommonActions.takeScreenShot(org_admin, test, "exportDisable");
		}
	}

}
