package org.cts.oneframework.deviceadapter.screens;

import java.net.MalformedURLException;

import org.cts.oneframework.utilities.IOSDeviceActions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

public class SearchRsultsScreen extends IOSDeviceActions {

	public SearchRsultsScreen(IOSDriver<MobileElement> androidDriver) throws MalformedURLException {
		super(androidDriver);
	}

	private final String PRIME_FILTER = "in.amazon.mShop.android.shopping:id/rs_promoted_filter_toggle";

	public void setPrimeFilterOn() {
		clickElement(MobileBy.id(PRIME_FILTER), "Prime filter");
	}

	public void selectProduct(String product) {
		clickOnTextView(product);
	}

}
