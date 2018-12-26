package com.expedia.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.expedia.base.BaseClass;
import com.expedia.pages.HomePage;
import com.expedia.pages.SignInPage;

public class HomePageTest extends BaseClass {

	HomePage hpage;
	SignInPage signInPage;

	public HomePageTest() {
		super();
	}

	@BeforeClass
	public void setup() {

		initialization();
		hpage = new HomePage();
		signInPage = new SignInPage();
	}

	@Test
	public void validateHomePageTitle() {

		String pageTitle = hpage.getPageTitle();
		Assert.assertEquals(pageTitle, "Sign In");
	}

	@Test
	public void validateExpediaLogo() {

		Assert.assertTrue(hpage.checkExpediaLogo());
	}

	

	@AfterClass
	public void tearDown() {

		driver.close();
	}

}
