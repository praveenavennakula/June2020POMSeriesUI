package com.qa.opencart.base;

import com.beust.jcommander.Parameter;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.ExcelUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

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

    @Parameters({"browser","browserversion","testname"})
    @BeforeTest
    public void setup(String browser, String browserVersion, String testName){

        df=new DriverFactory();
        prop=df.initProp();
        if(browser!=null){
            prop.setProperty("browser",browser);
            prop.setProperty("browserversion", browserVersion);
            prop.setProperty("testname", testName);
            System.out.println(prop.getProperty("browser"));
            System.out.println(prop.getProperty("browserversion"));
            System.out.println(prop.getProperty("testname"));
        }

        driver=df.initDriver(prop);
         lp=new LoginPage(driver);

    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
