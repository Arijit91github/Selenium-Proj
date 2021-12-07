package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import testBase.BaseClass;

public class Listeners extends BaseClass implements ITestListener {

	public void onTestStart(ITestResult result) {
		
		try {
			if(htRunMode.get(testcaseName).equalsIgnoreCase("Y"))
			launchBrowser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		try {
			takeScreenshot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeBrowser();
		test.log(LogStatus.PASS, "Testcase " +testcaseName+" execution is passed ");
		
	}

	public void onTestFailure(ITestResult result) {

		test.log(LogStatus.FAIL, result.getThrowable().getMessage());
		try {
			takeScreenshot_failed();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeBrowser();
		test.log(LogStatus.FAIL,  "Testcase " +testcaseName+" execution is failed");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
