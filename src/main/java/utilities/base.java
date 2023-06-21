package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class base {
	public static WebDriver driver;

	public base(WebDriver driver){
		base.driver=driver;	
	}
	public static String getScreenShot(WebDriver driver, String TestcaseName) throws IOException {
		TakesScreenshot scr = (TakesScreenshot) driver;
		File source = scr.getScreenshotAs(OutputType.FILE);
		String des = System.getProperty("user.dir" + "Screenshots"+"\\ScreenShots\\"+TestcaseName+".png");
		FileUtils.copyFile(source, new File(des));
		return des;
	}

	public static ExtentReports getreports() throws IOException {
		Properties prop = new Properties();
		String path = System.getProperty("user.dir" + "\\data.properties");
		FileInputStream fis= new FileInputStream(path);
		prop.load(fis);
		String repo = System.getProperty("user.dir" + "\\ExtentReports"+"\\ExtentReport.html");
		ExtentSparkReporter ext = new ExtentSparkReporter(repo);
		ext.config().setDocumentTitle(prop.getProperty("doctitle"));
		ext.config().setTheme(Theme.DARK);
		
		ExtentReports repo1 = new ExtentReports();
		repo1.attachReporter(ext);
		repo1.setSystemInfo("browser", "browser");
		return repo1;

	}
}
