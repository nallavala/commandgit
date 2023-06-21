package utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class listeners extends base implements ITestListener {
	

	protected listeners(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	static WebDriver driver;
	static base base=new base(driver);
    
	static ExtentReports ext =getReport();
	static ExtentTest test;
	static ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();
    
	public static ExtentReports getReport() {
		try {
			try {
				ext = base.getreports();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ext;
	}

	public void onTestStart(ITestResult result) {
		test = ext.createTest(result.getMethod().getMethodName());
		extenttest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		extenttest.get().log(Status.PASS, "Test passed");
		screencapture(result);
	}

	public void onTestFailure(ITestResult result) {
		// extenttest.get().log(Status.FAIL, "Test Failed");
		extenttest.get().fail(result.getThrowable());
		screencapture(result);
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stubITestListener.super.onTestSkipped(result);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		ext.flush();
	}

	public static void screencapture(ITestResult result) {
		String TestcaseName = result.getMethod().getMethodName();
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			extenttest.get().addScreenCaptureFromPath(getScreenShot(driver, TestcaseName), TestcaseName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
