package com.expedia.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.expedia.utils.WebEventListener;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;
	public EventFiringWebDriver ef;

	public BaseClass() {

		try {

			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\hp\\eclipse-workspace\\automation\\src\\main\\java\\com\\expedia\\config\\config.properties");
			prop.load(fis);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void initialization() {

		String browser = prop.getProperty("Browser");

		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriver"));
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", prop.getProperty("FirefoxDriver"));
			driver = new FirefoxDriver();
		}

		ef = new EventFiringWebDriver(driver);
		WebEventListener listener = new WebEventListener();
		ef.register(listener);
		driver = ef;

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		driver.get(prop.getProperty("URL"));
	}

}
