package com.expedia.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.expedia.base.BaseClass;
import com.expedia.pages.SignInPage;
import com.expedia.utils.TestUtils;

public class SignInTest extends BaseClass {

	SignInPage spage;

	@DataProvider
	public Object[][] getDataFromExcel() {
		Object[][] testData = TestUtils.getData();

		return testData;
	}

	public SignInTest() {

		super();
	}

	@BeforeMethod
	public void setup() {

		initialization();
		spage = new SignInPage();

	}

	@Test
	public void keepSignInCheckBoxTest() {
		Assert.assertFalse(spage.checkKeepSignInCheckBox());
	}

	@Test(dataProvider = "getDataFromExcel")
	public void signInTest(String Username, String Password) throws Exception {

		spage.Sigin(Username, Password);
		Thread.sleep(2000);
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
