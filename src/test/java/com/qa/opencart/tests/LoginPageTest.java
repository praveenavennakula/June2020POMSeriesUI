package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

@Test
    public void getPageTitleTest(){
    //LoginPage lp=new LoginPage(driver);we are not suppose dto call Driver API in Test class
    String title=lp.getLoginPageTitle();
    Assert.assertEquals(title,"Account Login11");

}

@Test(enabled = false)
    public void loginPageURLTest(){
    boolean b=lp.loginPageURL();
    Assert.assertTrue(b);
}

@Test
    public void isForgotPwdLinkExistTest(){
    Assert.assertTrue(lp.isForgotPwdLinkExist());
}

@Test
    public void loginTest(){
    accPage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    Assert.assertTrue(accPage.isLogoutLinkExist());

}
}
