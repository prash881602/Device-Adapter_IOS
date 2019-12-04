package org.cts.oneframework.deviceadapter.screens;

import java.net.MalformedURLException;

import org.cts.oneframework.utilities.IOSDeviceActions;
import org.cts.oneframework.utilities.AssertionLibrary;
import org.cts.oneframework.utilities.WaitUtils;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

public class ProductDescriptionScreen extends IOSDeviceActions {

	public ProductDescriptionScreen(IOSDriver<MobileElement> androidDriver) throws MalformedURLException {
		super(androidDriver);
	}

	private final String PRODUCT_TITLE = "title";
	private final String ADD_TO_CART = "add-to-cart-button";

	public void clickAddToCart() {
		scrollAndClick(ADD_TO_CART);
		WaitUtils.sleep(5);
	}

	public void assertProductIsdisplayed(String expectedText) {
		AssertionLibrary.assertEquals(getText(MobileBy.id(PRODUCT_TITLE)), expectedText,
				"Verify the description is as expected: " + expectedText);
	}
}
