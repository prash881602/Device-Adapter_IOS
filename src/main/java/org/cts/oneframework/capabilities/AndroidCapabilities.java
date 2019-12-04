package org.cts.oneframework.capabilities;

import java.util.logging.Logger;

import org.cts.oneframework.configprovider.ConfigProvider;
import org.cts.oneframework.listeners.TestListener;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidCapabilities {

	private static Logger logger = Logger.getLogger(AndroidCapabilities.class.getName());
	//private static final String APK_FOLDER = System.getProperty("user.dir") + "//src//main//resources//apk//";

	public static DesiredCapabilities set() {
		 DesiredCapabilities capabilities;
		capabilities =  new DesiredCapabilities().iphone();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,ConfigProvider.getAsString("PLATFORM_NAME"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,ConfigProvider.getAsString("DEVICE_NAME"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,ConfigProvider.getAsString("PLATFORM_VERSION"));
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"safari");
		logger.info(capabilities.toString());
		return capabilities;
	}

	
}
