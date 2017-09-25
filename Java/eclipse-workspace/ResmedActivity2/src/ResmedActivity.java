/********************************
 * Author: Jan Michael Navarro
 * Date: September 25,2017 
 * Purpose: Resmed Activity 2: Open a website and perform operations
 *********************************/
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;

public class ResmedActivity {
  private WebDriver webdriver;
  private String baseUrl="https://www.resmed.com/au/en/consumer.html";
  
  
  private static ChromeDriverService service;

  @BeforeClass
  public static void createAndStartService() {
  //Service builder for chrome
    service = new ChromeDriverService.Builder()
        .usingDriverExecutable(new File("/Users/JMike/Documents/ChromeDriver/chromedriver"))
        .usingAnyFreePort()
        .build();
    try {
		service.start();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  //Stop Service 
  @AfterClass
  public static void createAndStopService() {
    service.stop();
  }

  //instantiate remotewebdriver
  @Before
  public void createDriver() {
	  webdriver = new RemoteWebDriver(service.getUrl(),
        DesiredCapabilities.chrome());
  }

  //quite driver
  @After
  public void quitDriver() {
	  webdriver.quit();
  }

  @Test
  public void testResmed() throws Exception {
	//Open Resmed website
	webdriver.get(baseUrl);
    //open tab for Diagnosis and Treatment
	webdriver.findElement(By.linkText("Diagnosis and treatment")).click();
    // open tab for Products
	webdriver.findElement(By.linkText("Products")).click();
    //Open tab for Air Solutions
	webdriver.findElement(By.linkText("Air Solutions")).click();
    // Open tab for Support
	webdriver.findElement(By.linkText("Support")).click();
    // Open tab for Diagnosis and treatment
	webdriver.findElement(By.linkText("Diagnosis and treatment")).click();
    // Open search
	webdriver.findElement(By.xpath("//span[@id='global-nav-search']")).click();
    // Clear text on search
	webdriver.findElement(By.id("primary-search")).clear();
    // Key In Search key word
    webdriver.findElement(By.id("primary-search")).sendKeys("Nasal pillows");
    //Submit search 
	webdriver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
  }
}
