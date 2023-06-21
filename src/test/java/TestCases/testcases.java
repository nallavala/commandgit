package TestCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.base;  

public class testcases extends base {
	
	public testcases(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	base base;
	Logger logger = LogManager.getLogger(testcases.class);
	

	@Test(priority = 1)
	public void beforetest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		base=new base(driver);
		  logger.info("launch successful"); logger.error(" Launched");
		  logger.debug("browser launched"); logger.trace("launch");
		 
		driver.get("https://www.amazon.in/");
		
		  logger.info("URL launch successful"); logger.error(" new Launched");
		  logger.debug("URL launched"); logger.trace("newlaunch");
	}

	@Test(priority = 2)
	public void validate() {
		String actualurl = driver.getCurrentUrl();
		String expectedurl = "https://www.amazon.in/";
		Assert.assertEquals(actualurl, expectedurl);
		System.out.println("same");
	}

	@Test(priority = 3)
	public void close() {
		driver.close();
	}

	@Test(groups = "smoke")
	public void smoke() {
		System.out.println("This is smoke testing");
	}

	@Test(groups = "regression")
	public void regression() {
		System.out.println("This is for regression testing");
	}
}
