package com.expedia.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.expedia.base.BaseClass;

public class HomePage extends BaseClass {

	@FindBy(xpath = "//a[@id='header-logo']//img")
	WebElement expediaLogo;

	@FindBy(id = "account-menu")
	WebElement accountLoginOptions;

	@FindBy(id = "account-signin")
	WebElement btnSignin;

	@FindBy(id = "account-register")
	WebElement btnSignUp;

	public HomePage() {

		PageFactory.initElements(driver, this);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public boolean checkExpediaLogo() {

		return expediaLogo.isDisplayed();
	}

	
}
