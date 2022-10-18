package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Epic("Epic - 100: Open cart application login page design")
@Story("US - 101: Design login page features")
//@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {

    @Description("login page title test.....")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 1)
    public void getPageTitleTest() {
        //LoginPage lp=new LoginPage(driver);we are not suppose dto call Driver API in Test class
        String title = lp.getLoginPageTitle();
        Assert.assertEquals(title, "Account Login11");

    }

    @Description("login page url test.....")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void loginPageURLTest() {
        boolean b = lp.loginPageURL();
        Assert.assertTrue(b);
    }

    @Description("login page is displaying forgot pwd link test.....")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void isForgotPwdLinkExistTest() {
        Assert.assertTrue(lp.isForgotPwdLinkExist());
    }

    @Description("login page Login Test with correct username and password.....")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 4)
    public void loginTest() {
        accPage = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertTrue(accPage.isLogoutLinkExist());

    }
}
