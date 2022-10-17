package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    //1.By locators
    By firstName = By.name("firstname");
    By lastName = By.name("lastname");
    By email = By.name("email");
    By telePhone = By.id("input-telephone");
    By password = By.id("input-password");
    By passwordConfirm = By.id("input-confirm");
    By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value=1 and @type='radio']");
    By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value=0 and @type='radio']");
    By agreeCheckBox = By.name("agree");
    By continueBtn = By.cssSelector("input[value='Continue']");
    By regSuccessMsg = By.cssSelector("div#content h1");
    By logoutLink = By.linkText("Logout");
    By registerLink = By.linkText("Register");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);

    }

    public String userRegister(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
        elementUtil.doSendKeysWithVisibleElement(this.firstName, AppConstants.DEFAULT_LARGE_TIME_OUT, firstName);
        elementUtil.doSendKeys(this.lastName, lastName);
        elementUtil.doSendKeys(this.email, email);
        elementUtil.doSendKeys(this.telePhone, telephone);
        elementUtil.doSendKeys(this.password, password);
        elementUtil.doSendKeys(this.passwordConfirm, password);
        if ((subscribe.equalsIgnoreCase("Yes"))) {
            elementUtil.doClick(subscribeYes);
        } else {
            elementUtil.doClick(subscribeNo);
        }
        elementUtil.doClick(agreeCheckBox);
        elementUtil.doClick(continueBtn);

        String succMessage = elementUtil.waitForElementVisible(regSuccessMsg, 30).getText();
        System.out.println(succMessage);
        System.out.println("email is :" + email);
        System.out.println("password is:" + password);
        elementUtil.doClick(logoutLink);
        elementUtil.doClick(registerLink);
        return succMessage;
    }

}
