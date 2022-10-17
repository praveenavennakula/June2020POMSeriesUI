package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AccountPageTest extends BaseTest {
    @BeforeClass
    public void accSetup(){
        accPage=lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test(priority = 0)
    public void accPageTitleTest(){
        String actAccPageTitle= accPage.getAccPageTitle();
        Assert.assertEquals(actAccPageTitle,"My Account");
    }

    @Test(priority = 1)
    public void accPageURLTest(){
        Assert.assertTrue(accPage.getAcctPageURL());
    }

    @Test(priority = 2)
    public void searchExistTest(){
        Assert.assertTrue(accPage.isSearchExist());
    }
    @Test(priority = 3)
    public void isLogoutLinkExistTest(){
        Assert.assertTrue(accPage.isLogoutLinkExist());
    }

    @Test(priority = 4)
    public void accPageHeadersTest(){
        ArrayList<String> actHeadersList= accPage.getAcctSecHeaders();
        System.out.println("Actual Page Headers "+actHeadersList);
        Assert.assertEquals(actHeadersList, AppConstants.ACC_PAGE_SECTIONS_HEADERS);
    }

    @DataProvider
    public Object[][] getProductKeyData(){
        return new Object[][]{
                				{ "Macbook"},
                        { "iMac"},
                        {"Samsung"}
        };

    }
    @Test(priority = 5,dataProvider = "getProductKeyData")
    public void searchCheckTest(String productKeyName){
        searchResultsPage=accPage.performSearch(productKeyName);
        Assert.assertTrue(searchResultsPage.isSearchSuccessful());
    }
    @DataProvider
    public Object[][] getProductData(){
        return new Object[][]{
                { "Macbook", "MacBook Pro" },
                { "Macbook", "MacBook Air" },
                { "iMac", "iMac" },
                {"Samsung", "Samsung SyncMaster 941BW"},
                {"Samsung", "Samsung Galaxy Tab 10.1"}
        };

    }
    @Test(priority = 6,dataProvider = "getProductData")
    public void searchTest(String productKeyName,String mainProductName){
        searchResultsPage=accPage.performSearch(productKeyName);
        if(searchResultsPage.isSearchSuccessful()){
            productInfoPage=searchResultsPage.selectProduct(mainProductName);
            String actualProductHeader=productInfoPage.getProductHeader(mainProductName);
            Assert.assertEquals(actualProductHeader,mainProductName);
        }
    }
}
