package com.xmd.technician;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import java.sql.Time;
import java.util.Set;
import java.io.File;

import javax.sound.midi.MidiDevice.Info;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.chrome.ChromeOptions;
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
	  capabilities.setCapability("platformVersion", "7.1.1");
	  //capabilities.setCapability("appPackage", "com.xmd.technician");
	  //capabilities.setCapability("appActivity", ".window.WelcomeActivity");
	  capabilities.setCapability("appPackage", "com.tencent.mm");
	  capabilities.setCapability("appActivity", ".ui.LauncherUI");
	  //capabilities.setCapability("appWaitActivity", ".plugin.webview.ui.tools.WebViewUI");
	  //微信公众号设置
	  ChromeOptions options = new ChromeOptions();
	  options.setExperimentalOption("androidProcess", "com.tencent.mm:tools");
	  capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	  
	  driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
	  
	  //安装app
/*	  File classpathRoot=new File(System.getProperty("user.dir"));
	  File appDir=new File(classpathRoot, "apps");
	  driver.installApp(appDir.getAbsolutePath());
	  logger.info(appDir.getAbsolutePath());
	  if(driver.isAppInstalled("com.xmd.technician")==true){
	      logger.info("APP端安装成功");
	  }
	  else{
	      logger.info("APP端安装失败");        
	  }*/
	  
	  //是否需要安装apk
/*	  if(notInstall){
    	  File classpathRoot=new File(System.getProperty("user.dir"));
    	  File appDir=new File(classpathRoot, "apps");
    	  File app=new File(appDir, "9358tech_v1.8.9.57_dev_xiaomodo_201709061741.apk");
    	  capabilities.setCapability("app", app.getAbsolutePath());
	  }*/
    	  
  }
  
  @Test
  public void testLogin() throws InterruptedException{
	  
	  logger.info(driver.currentActivity());
	  Thread.sleep(5000);
	  //微信操作
	  //driver.findElementByXPath("//*[@text='通讯录']").click();
	  //driver.findElementByXPath("//*[@text='公众号']").click();
	  
	  driver.findElementByXPath("//*[@text='小摩豆用户测试']").click();
	  Thread.sleep(2000);
	  logger.info(driver.currentActivity());
	  driver.findElementByXPath("//*[@text='会所']").click();
	  logger.info("已打开公众号");
	  Thread.sleep(3000);
	  
	  Set<String> contextNames=driver.getContextHandles();
	  logger.info(contextNames);
	  
	  driver.context("WEBVIEW_com.tencent.mm:tools");
	  logger.info("已切换至webview");
	  driver.findElementByXPath("//*[@text='筛选']").click();
	  Thread.sleep(5000);
	 /* //切换到技师app
	  driver.startActivity("com.xmd.technician", ".window.WelcomeActivity");
	  logger.info(driver.currentActivity());
	  //登录测试
	  driver.findElementById("com.xmd.technician:id/phone_number").clear();
	  driver.findElementById("com.xmd.technician:id/phone_number").sendKeys("13722222222");
	  driver.findElementById("com.xmd.technician:id/password1").sendKeys("123456");
	  driver.hideKeyboard();
	  driver.findElementById("com.xmd.technician:id/login_btn1").click();
	  logger.info("点击登录");
	  Thread.sleep(5000);
	  String currentActivity = driver.currentActivity();
	  logger.info(currentActivity);
	  assertEquals(currentActivity, ".window.MainActivity");*/
  }
  
  @AfterClass(alwaysRun = true)
  public void afterTest() {
	  driver.quit();
  }

}
