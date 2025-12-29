package createVendor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericutility.ExcelFileUtil;
import genericutility.JavaUtil;
import genericutility.PropFileUtil;
import genericutility.WebDriverUtil;

public class CreateVendorNameTest {
	@Test
	public void createVendorNameTest() throws Exception {

		//create Obj for Utility Classes
		 PropFileUtil fu = new PropFileUtil();
		JavaUtil jLib = new JavaUtil();
		ExcelFileUtil eLib = new ExcelFileUtil();
		WebDriverUtil wLib = new WebDriverUtil();

		String BROWSER = fu.readPropFile("browser");
		String URL = fu.readPropFile("url");
		String USERNAME = fu.readPropFile("username");
		String PASSWORD = fu.readPropFile("password");

		//read data from excel file
		String vName = eLib.readExcelFile("Trouble", 1, 1)+jLib.randomNum();

		//Launch the browser
		//	WebDriver driver = new ChromeDriver();
		WebDriver driver=null;

		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
		}		

		//maximize the window
	//	driver.manage().window().maximize();
		wLib.maximizeWindow(driver);
		//enter URl
		driver.get(URL);
		//wait for page load
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wLib.waitImplicitly(driver, 10);
		//step1.Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		//step1.Navigate to Home Page click on 'More' link
		driver.findElement(By.xpath("//a[.='More']")).click();
		//step2.Click on 'Vendors' link
		driver.findElement(By.xpath("//a[.='Vendors']")).click();
		//step3.Click on Create Organization Lookup Image
		driver.findElement(By.xpath("//img[@title=\"Create Vendor...\"]")).click();
		//step4.Enter all mandatory fields with valid data
		driver.findElement(By.name("vendorname")).sendKeys(vName);
		//step5.Click on 'GL Account ' drop down and select '301-Sales-Hardware' from drop down
		WebElement dropd = driver.findElement(By.name("glacct"));
//		Select sel = new Select(dropd);
//		sel.selectByContainsVisibleText("301-Sales-Hardware");
		wLib.selectByVisibleText(dropd, "301-Sales-Hardware");
		//step6.click on 'Save' button
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		String text = driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();

		System.out.println(text);

		if(text.contains(vName))
		{
			System.out.println("Vendor created sucessfully");
		}
		else
		{
			System.out.println("Vendor not created ");
		}
		//click on administrator icon
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
		//Click on 'sign out' 
		WebElement signoutEle = driver.findElement(By.linkText("Sign Out"));
		//close the browser
		wLib.mouseHover(driver, signoutEle);
		Thread.sleep(5000);
		driver.quit();
		Assert.fail();
	}

}
