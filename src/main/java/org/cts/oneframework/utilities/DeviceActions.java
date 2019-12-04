package org.cts.oneframework.utilities;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.cts.oneframework.configprovider.ConfigProvider;
import org.cts.oneframework.utilities.Screenshots;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

public class DeviceActions {

	private final JavascriptExecutor javascriptExecutor;
	private final int DEFAULT_IMPLICIT_WAIT = 0;
	private FluentWait<IOSDriver<MobileElement>> fluentWait;
	private IOSDriver<MobileElement> driver;
	private Duration pollingInterval = Duration.ofMillis(ConfigProvider.getAsInt("POLLING_INTERVAL"));
	private Duration fluentWaitDuration = Duration.ofSeconds(ConfigProvider.getAsInt("FLUENT_WAIT"));

	public DeviceActions(IOSDriver<MobileElement> driver) {
		this.driver = driver;
		fluentWait = new FluentWait<>(driver).withTimeout(fluentWaitDuration).pollingEvery(pollingInterval)
				.ignoring(StaleElementReferenceException.class).ignoring(ElementNotVisibleException.class)
				.ignoring(NoSuchElementException.class);
		javascriptExecutor = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}

	protected void setImplicitWait(int duration) {
		driver.manage().timeouts().implicitlyWait(duration, TimeUnit.SECONDS);
	}

	/**
	 * returns list of webElements.
	 * 
	 * @param locator
	 * @return List<WebElement>
	 */
	protected List<WebElement> getElements(final String locator) {
		return fluentWait.until(new ExpectedCondition<List<WebElement>>() {
			@Override
			public List<WebElement> apply(WebDriver driver) {
				return driver.findElements(By.xpath(locator));
			}
		});
	}

	/**
	 * returns the first instance of webElement
	 * 
	 * @param locator
	 * @return WebElement
	 */
	protected WebElement getElement(String locator) {
		return fluentWait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id(locator));
			}
		});
	}

	/**
	 * this method checks if the element is present on page.
	 * 
	 * @param locator
	 * @return boolean
	 */
	protected boolean isElementOnPage(String locator) {
		setImplicitWait(DEFAULT_IMPLICIT_WAIT);
		boolean flag = !getElements(locator).isEmpty();
		setImplicitWait(ConfigProvider.getAsInt("IMPILCIT_WAIT"));
		return flag;
	}

	/**
	 * returns true, if element is enabled.
	 * 
	 * @param locator
	 * @return boolean
	 */
	protected boolean isEnabled(final String locator) {
		List<WebElement> elementList = getElements(locator);
		if (!elementList.isEmpty()) {
			return elementList.get(0).isEnabled();
		} else {
			return false;
		}
	}

	/**
	 * returns true, if element is displayed.
	 * 
	 * @param locator
	 * @return boolean
	 */
	protected boolean isDisplayed(final String locator) {
		List<WebElement> elementList = getElements(locator);
		if (!elementList.isEmpty()) {
			return elementList.get(0).isDisplayed();
		} else {
			return false;
		}
	}

	/**
	 * returns true, if element is selected.
	 * 
	 * @param locator
	 * @return boolean
	 */
	protected boolean isSelected(final String locator) {
		List<WebElement> elementList = getElements(locator);
		if (!elementList.isEmpty()) {
			return elementList.get(0).isSelected();
		} else {
			return false;
		}
	}

	/**
	 * returns the number of instances of the element.
	 * 
	 * @param locator
	 * @return size
	 */
	protected int getElementSize(String locator) {
		if (isElementOnPage(locator)) {
			return getElements(locator).size();
		} else {
			return 0;
		}
	}

	/**
	 * This method sets input value using sendkeys function of selenium. Also
	 * provides the feature of clean before setting the value.
	 * 
	 * @param locator
	 * @param clearInput
	 */
	protected void setInputValue(final String locator, final String value, final boolean clearInput) {
		WebElement element = getElement(locator);
		if (clearInput) {
			element.clear();
		}
		element.sendKeys(value);
		Screenshots.addStepWithScreenshotInReport(driver, "Set value: " + value);
	}

	/**
	 * This method first clears and then sets input value using sendkeys function of
	 * selenium.
	 * 
	 * @param locator
	 * @param clearInput
	 */
	protected void setInputValue(final String locator, final String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
		Screenshots.addStepWithScreenshotInReport(driver, "Set value: " + value);
	}

	/**
	 * This method sets input value using javascript Executor. Also provides the
	 * feature of clean before setting the value.
	 * 
	 * @param locator
	 * @param value
	 * @param clearInput
	 */
	protected void setInputValueJS(final String locator, final String value, final boolean clearInput) {
		WebElement element = getElement(locator);
		if (clearInput) {
			element.clear();
		}
		javascriptExecutor.executeScript("arguments[0].value='" + value + "';", element);
		Screenshots.addStepWithScreenshotInReport(driver, "Set value: " + value);
	}

	/**
	 * This method sets input value using javascript Executor.
	 * 
	 * @param locator
	 * @param value
	 * @param clearInput
	 */
	protected void setInputValueJS(final String locator, final String value) {
		WebElement element = getElement(locator);
		element.clear();
		javascriptExecutor.executeScript("arguments[0].value='" + value + "';", element);
		Screenshots.addStepWithScreenshotInReport(driver, "Set value: " + value);
	}

	/**
	 * This method clears the input field value.
	 * 
	 * @param locator
	 */
	protected void clear(final String locator) {
		getElement(locator).clear();
	}

	/**
	 * This method returns the text.
	 * 
	 * @param locator
	 * @return String
	 */
	protected String getText(final String locator) {
		return getElement(locator).getText();
	}

	/**
	 * This method returns the value of mentioned attribute.
	 * 
	 * @param locator
	 * @param attribute
	 * @return String
	 */
	protected String getAttribute(final String locator, final String attribute) {
		return getElement(locator).getAttribute(attribute);
	}

	/**
	 * This method returns the css value of mentioned field.
	 * 
	 * @param locator
	 * @param attribute
	 * @return String
	 */
	protected String getCssValue(final String locator, final String attribute) {
		return getElement(locator).getCssValue(attribute);
	}

	/**
	 * This method clicks using javascript executor.
	 * 
	 * @param locator
	 */
	protected void clickElementJS(final String locator) {
		javascriptExecutor.executeScript("arguments[0].click();", getElement(locator));
		Screenshots.addStepWithScreenshotInReport(driver, "Click: " + getClickStepDescription(locator));
	}

	/**
	 * This method clicks using javascript executor. Add description to display in
	 * the report.
	 * 
	 * @param locator
	 */
	protected void clickElementJS(final String locator, final String description) {
		javascriptExecutor.executeScript("arguments[0].click();", getElement(locator));
		Screenshots.addStepWithScreenshotInReport(driver, "Click: " + description);
	}

	/**
	 * This method first makes the element visible and then perform click action
	 * using javascript.
	 * 
	 * @param locator
	 */
	protected void makeElementVisibleAndClick(final String locator) {
		WebElement element = getElement(locator);
		javascriptExecutor.executeScript("arguments[0].style.display='block';", element);
		javascriptExecutor.executeScript("arguments[0].click();", element);
		Screenshots.addStepWithScreenshotInReport(driver, "Click: " + getClickStepDescription(locator));
	}

	/**
	 * This method first makes the element visible and then perform click action
	 * using javascript. Add description to display in the report.
	 * 
	 * @param locator
	 */
	protected void makeElementVisibleAndClick(final String locator, final String description) {
		WebElement element = getElement(locator);
		javascriptExecutor.executeScript("arguments[0].style.display='block';", element);
		javascriptExecutor.executeScript("arguments[0].click();", element);
		Screenshots.addStepWithScreenshotInReport(driver, "Click: " + description);
	}

	/**
	 * This method performs the normal click operation of Selenium.
	 * 
	 * @param locator
	 */
	protected void clickElement(final String locator) {
		getElement(locator).click();
		Screenshots.addStepWithScreenshotInReport(driver, "Click Action performed.");
	}

	/**
	 * This method performs the normal click operation of Selenium. Add description
	 * to display in the report.
	 * 
	 * @param locator
	 */
	protected void clickElement(final String locator, String description) {
		getElement(locator).click();
		Screenshots.addStepWithScreenshotInReport(driver, "Click: " + description);
	}

	/**
	 * This method shifts the focus away from the current element.
	 * 
	 * @param locator
	 */
	protected void shiftFocusAway(final String locator) {
		getElement(locator).sendKeys(Keys.TAB);
	}

	private String getClickStepDescription(String locator) {
		String[] str = locator.split("='");
		if (str[1].length() != 0) {
			return str[1].split("\'")[0];
		}
		return locator;
	}

}
