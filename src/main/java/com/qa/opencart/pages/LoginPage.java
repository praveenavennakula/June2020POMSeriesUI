package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;//use encapsulation to access this private driver
    private ElementUtil elementUtil;//we can reuse the methods in the utility
    //1.BY locators

    By emailID = By.id("input-email");
    By password = By.id("input-password");
    By loginBtn = By.xpath("//input[@value='Login']");
    By logoImage = By.cssSelector("img[title='naveenopencart']");
    By forgotPwdlink = By.linkText("Forgotten Password");
    By register = By.linkText("Register");


    //2.Page Constructor---we need driver to perform actions on By locators
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);

    }

    //3.Page Actions

    /**
     * get the login page title and
     *
     * @return title
     */
    public String getLoginPageTitle() {
        String title = driver.getTitle();
        System.out.println(AppConstants.LOGIN_PAGE_TITLE + title);
        return title;
    }

    /**
     * Check the loginpage url contains specific string
     *
     * @return true if contains ,if not return false
     */

    public boolean loginPageURL() {
        String url = driver.getCurrentUrl();
        System.out.println("login page URL is: " + url);
        if (url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * check forgot password link exists or not
     *
     * @return
     */
    public boolean isForgotPwdLinkExist() {
        return driver.findElement(forgotPwdlink).isDisplayed();
    }

    /**
     * this method is used to login the App with the given credentials
     *
     * @param userName
     * @param pwd
     */
    public AccountsPage doLogin(String userName, String pwd) {
        driver.findElement(emailID).sendKeys(userName);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(loginBtn).click();
/*        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
        String text=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='My Account']"))).getText();
        return text;*/
        return new AccountsPage(driver);


    }

    public RegistrationPage navigateToRegisterPage() {
        System.out.println("navigating to Registration Page");
        elementUtil.doClick(register);

        return new RegistrationPage(driver);
    }


}
