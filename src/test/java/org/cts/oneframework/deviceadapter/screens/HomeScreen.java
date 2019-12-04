package org.cts.oneframework.deviceadapter.screens;

import java.net.MalformedURLException;

import org.cts.oneframework.utilities.IOSDeviceActions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.ios.IOSDriver;

public class HomeScreen extends IOSDeviceActions {

	public HomeScreen(IOSDriver<MobileElement> driver) throws MalformedURLException {
		super(driver);
	}

	private final String SEARCH_BOX = "in.amazon.mShop.android.shopping:id/rs_search_src_text";

	public void searchProduct(String product) {
		setInputValue(MobileBy.id(SEARCH_BOX), product, false);
		//((PressesKey) keyBoard()).pressKey(AndroidKey.ENTER);
	}
}
