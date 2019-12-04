package org.cts.oneframework.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.cts.oneframework.capabilities.AndroidCapabilities;
import org.cts.oneframework.configprovider.ConfigProvider;
import org.cts.oneframework.excelreader.ExcelDataProvider;
import org.cts.oneframework.utilities.Screenshots;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

@Listeners(org.cts.oneframework.listeners.TestListener.class)
public class BaseTest {

	protected IOSDriver<MobileElement> androidDriver;
	private static Logger logger = Logger.getLogger(BaseTest.class.getName());
	private final String SCREENSHOTS_PATH = System.getProperty("java.io.tmpdir") + "\\screenshots";

	@BeforeSuite
	public void folderCleanup() throws IOException {
		File file = new File(SCREENSHOTS_PATH);
		if (file.exists())
			FileUtils.cleanDirectory(file);
	}

	public IOSDriver<MobileElement> launchApp() throws MalformedURLException {
		DesiredCapabilities capabilities = AndroidCapabilities.set();
		URL url=new URL("http://0.0.0.0:4723/wd/hub");
		 IOSDriver<MobileElement> driver = null;
	        //IOSDriver driver = AppiumUtils.getIOSDriver();
	        try{
	        	driver = new IOSDriver<MobileElement>(url,capabilities);
	        	driver.get(ConfigProvider.getAsString("url"));
	        }
	        catch(Exception e) {
	        	e.printStackTrace();
	        }
		
		//androidDriver = new IOSDriver<MobileElement>(toUrl(ConfigProvider.getAsString("server-url")), capabilities);
		Screenshots.addStepWithScreenshotInReport(driver, "App launched.");
		return driver;
	}

	@DataProvider(name = "data")
	public Object[][] readExcelData(Method method) {
		return new ExcelDataProvider(getClass()).data(method);
	}

	private URL toUrl(String urlString) {
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			logger.warning("Url is not correct: " + url);

		}
		return url;
	}

	@AfterMethod
	public void closeApp() {
		//driver.closeApp();
		//driver.quit();
	}

}
