package com.xmd.technician;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import java.io.File;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.apache.log4j.Logger;

public class UITest {
	private AndroidDriver<AndroidElement> driver;
	public static Logger logger = Logger.getLogger(UITest.class.getName());
	private boolean notInstall = true;

  @BeforeClass
  public void setUp() throws Exception {
	  logger.info("开始配置");
	  DesiredCapabilities capabilities = new DesiredCapabilities();
	  capabilities.setCapability("deviceName", "CB512BPKD9");
	  capabilities.setCapability("platformName", "Android");
	  capabilities.setCapability("platformVersion", "7.0");
	  capabilities.setCapability("appPackage", "com.xmd.technician");
	  capabilities.setCapability("appActivity", ".window.WelcomeActivity");
	  //capabilities.setCapability("appWaitActivity", ".window.WelcomeActivity");
	  
	  driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
	  
	  //是否需要安装apk
	  if(notInstall){
    	  File classpathRoot=new File(System.getProperty("user.dir"));
    	  File appDir=new File(classpathRoot, "apps");
    	  File app=new File(appDir, "9358tech_v1.8.9.57_dev_xiaomodo_201709061741.apk");
    	  capabilities.setCapability("app", app.getAbsolutePath());
	  }
    	  
  }
  
  @Test
  public void testLogin() throws InterruptedException{
	  driver.findElementById("com.xmd.technician:id/phone_number").clear();
	  driver.findElementById("com.xmd.technician:id/phone_number").sendKeys("13722222222");
	  driver.findElementById("com.xmd.technician:id/password1").sendKeys("123456");
	  driver.hideKeyboard();
	  driver.findElementById("com.xmd.technician:id/login_btn1").click();
	  logger.info("点击登录");
	  Thread.sleep(5000);
	  String currentActivity = driver.currentActivity();
	  logger.info(currentActivity);
	  assertEquals(currentActivity, ".window.MainActivity");
  }
  
  @AfterClass(alwaysRun = true)
  public void afterTest() {
	  driver.quit();
  }

}
