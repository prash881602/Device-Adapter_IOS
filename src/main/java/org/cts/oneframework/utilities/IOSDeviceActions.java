package org.cts.oneframework.utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.cts.oneframework.capabilities.AndroidCapabilities;
import org.cts.oneframework.utilities.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class IOSDeviceActions extends BasePageObject {

	//private IOSDriver<MobileElement> tAction;
	protected IOSDriver<MobileElement> driver;

	protected IOSDeviceActions(IOSDriver<MobileElement> driver2) throws MalformedURLException {
		super(driver2);
		//DesiredCapabilities capabilities = AndroidCapabilities.set();
		//URL url=new URL("http://0.0.0.0:4723/wd/hub");
		//driver=new IOSDriver<MobileElement>(url,capabilities);
		//driver = new IOSDriver<MobileElement>((Capabilities) driver2);
		this.driver = driver2;
	}
	protected void tapByElement(By by) {
		
	}

	protected void tapByCoordinates(int x, int y) {
		
	}

	protected void pressByCoordinates(int x, int y, long seconds) {
		
	}

	protected void longPressByElement(By by) {
		
	}

	protected void longPressByCoordinates(AndroidElement androidElement) {
		
	}

	protected void clickOnTextView(String text) {

		WebElement element = getElement(MobileBy.AndroidUIAutomator(String.format("new UiSelector().text(\"%s\")", text)));
		String originalContext = driver.getContext();
		Point coordinate = element.getLocation();
		Dimension dms = element.getSize();
		int centerX = dms.getWidth() / 2 + coordinate.getX();
		int centerY = dms.getHeight() / 2 + coordinate.getY();
		driver.context("NATIVE_APP");
		tapByCoordinates(centerX, centerY);
		driver.context(originalContext);
	}
	
	public void scrollAndClick(String id) {
		clickElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\""
						+ id + "\").instance(1))"));		
	}

	protected KeyBoard keyBoard() {
		return new KeyBoard();
	}

	public class KeyBoard {

		

	}

}
