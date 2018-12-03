package com.expedia.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.expedia.base.BaseClass;

public class SignInPage extends BaseClass {
	
	@FindBy(id="signin-loginid")
	WebElement userName;

	@FindBy(id="signin-password")
	WebElement pwd;
	
	@FindBy(id="submitButton")
	WebElement btnSubmit;
	
	@FindBy(id="signin-loggedin")
	WebElement keepSignInCheckBox;
	
	
	
	public SignInPage() {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage Sigin(String Username, String Password) {
		userName.sendKeys(Username);
		pwd.sendKeys(Password);
		btnSubmit.click();
		
		return new HomePage();
	}
	public boolean checkKeepSignInCheckBox() {
		return keepSignInCheckBox.isSelected();
	}
}
