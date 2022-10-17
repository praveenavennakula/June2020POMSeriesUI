package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.ExcelUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;
import java.util.Random;

public class BaseTest {
    DriverFactory df;
   public WebDriver driver;
    public LoginPage lp;
    public Properties prop;
    public AccountsPage accPage;
    public SearchResultsPage searchResultsPage;
    public ProductInfoPage productInfoPage;
    public RegistrationPage regPage;
   // public Random rd;


    @BeforeTest
    public void setup(){
        df=new DriverFactory();
       prop=df.initProp();
        driver=df.initDriver(prop);
         lp=new LoginPage(driver);

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
