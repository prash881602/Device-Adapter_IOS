package org.cts.oneframework.deviceadapter.tests;

import java.net.MalformedURLException;

import org.cts.oneframework.utilities.BaseTest;
import org.testng.annotations.Test;

public class LaunchApplication_Test extends BaseTest {

	@Test
	public void launchApplication_Test() {
		try {
			launchApp();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}