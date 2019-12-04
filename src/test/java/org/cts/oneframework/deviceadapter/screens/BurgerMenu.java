package org.cts.oneframework.deviceadapter.screens;

import java.net.MalformedURLException;

import org.cts.oneframework.utilities.IOSDeviceActions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

public class BurgerMenu extends IOSDeviceActions {

	protected BurgerMenu(IOSDriver<MobileElement> androidDriver) throws MalformedURLException {
		super(androidDriver);
	}

	private final String BURGER_MENU = "in.amazon.mShop.android.shopping:id/action_bar_burger_icon";
	private final String HOME = "Home";
	private final String SHOP_BY_CATEGORY = "Shop by Category";

	protected void openBurgerMenu() {
		clickElement(MobileBy.id(BURGER_MENU), "Open Burger Menu");
	}

	protected void home() {
		openBurgerMenu();
		clickOnTextView(HOME);
	}

	protected void shopByCategory() {
		openBurgerMenu();
		clickOnTextView(SHOP_BY_CATEGORY);
	}
}
