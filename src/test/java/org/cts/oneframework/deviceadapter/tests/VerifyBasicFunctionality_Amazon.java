package org.cts.oneframework.deviceadapter.tests;

import java.net.MalformedURLException;
import java.util.Map;

import org.cts.oneframework.annotation.ExcelDetails;
import org.cts.oneframework.deviceadapter.screens.HomeScreen;
import org.cts.oneframework.deviceadapter.screens.ProductDescriptionScreen;
import org.cts.oneframework.deviceadapter.screens.SearchRsultsScreen;
import org.cts.oneframework.utilities.BaseTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

@ExcelDetails
public class VerifyBasicFunctionality_Amazon extends BaseTest {

	private IOSDriver<MobileElement> driver;
	private HomeScreen homeScreen;
	private SearchRsultsScreen resultsSscreen;
	private ProductDescriptionScreen productDescriptionScreen;

	@Test(dataProvider = "data", description = "Basic Amazon app flow")
	public void verifyBasicSearchFunctionality_Amazon_Test(Map<String, String> input) throws MalformedURLException {
		try {
			driver = launchApp();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		homeScreen = new HomeScreen(driver);
		resultsSscreen = new SearchRsultsScreen(driver);
		productDescriptionScreen = new ProductDescriptionScreen(driver);
		String SEARCH_PRODUCT = input.get("searchProduct").trim();
		String PRODUCT_TITLE = input.get("productTitle").trim();
		
		homeScreen.searchProduct(SEARCH_PRODUCT);
		
		resultsSscreen.setPrimeFilterOn();
		
		resultsSscreen.selectProduct(PRODUCT_TITLE);
		
		productDescriptionScreen.assertProductIsdisplayed(PRODUCT_TITLE);
	}

}
