package pages;

import java.util.Hashtable;

import Utility.CommonMethods;
import testBase.BaseClass;

public class AuthenticationPage extends BaseClass {

	public static void createAnAccount(Hashtable<String, String> htData) throws Exception {

		CommonMethods.verifyPageTitle(htData.get("PageTitle"));
		CommonMethods.clickOnButton("Sign_in", "Sign_in");
		CommonMethods.enterTextIntoTextBoxTestDataSheet("Email_address", htData, "Email_ID", "Email Address");
		CommonMethods.clickOnButton("CreateAnAccount", "CreateAnAccount");

	}

	public static void loginWithRegisteredUser(Hashtable<String, String> htData) throws Exception {

//		CommonMethods.verifyPageTitle(htData.get("PageTitle"));
//		CommonMethods.clickOnButton("Sign_in", "Sign_in_btn");
		CommonMethods.enterTextIntoTextBoxConfig("RegUserEmail", "Email_id", "RegUser Email_ID");
		CommonMethods.enterTextIntoTextBoxConfig("RegUserPassword", "Password", "RegUser Password");
		CommonMethods.clickOnButton("Sign_in_btn_RegUser", "RegUser Sign_in_btn");

	}

}
