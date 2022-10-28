package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

@Epic("Epic - 200: Open cart application Accounts page design")
@Story("US - 201: Design Accounts page features")
public class AccountPageTest extends BaseTest {
    @BeforeClass
    public void accSetup() {
        accPage = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Description("accPageTitleTest -- Dev Name: @Naveen Khunteta")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 1)
    public void accPageTitleTest() {
        String actAccPageTitle = accPage.getAccPageTitle();
        Assert.assertEquals(actAccPageTitle, "My Account");
    }

    @Description("accPageUrlTest -- Dev Name: @praveena")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void accPageURLTest() {
        Assert.assertTrue(accPage.getAcctPageURL());
    }

    @Description("Acc page search test -- Dev Name:  @praveena")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void searchExistTest() {
        Assert.assertTrue(accPage.isSearchExist());
    }

    @Description("Acc page logout link exist test -- Dev Name:  @praveena")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4)
    public void isLogoutLinkExistTest() {
        Assert.assertTrue(accPage.isLogoutLinkExist());
    }

    @Description("Acc page header test -- Dev Name:  @praveena")
    @Severity(SeverityLevel.TRIVIAL)
    @Test(priority = 5)
    public void accPageHeadersTest() {
        ArrayList<String> actHeadersList = accPage.getAcctSecHeaders();
        System.out.println("Actual Page Headers " + actHeadersList);
        Assert.assertEquals(actHeadersList, AppConstants.ACC_PAGE_SECTIONS_HEADERS);
    }

    @DataProvider
    public Object[][] getProductKeyData() {
        return new Object[][]{
                {"Macbook"},
                {"iMac"},
                {"Samsung"}
        };

    }

    @Description("Acc page search check test -- Dev Name:  @praveena")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "getProductKeyData", priority = 6)
    public void searchCheckTest(String productKeyName) {
        searchResultsPage = accPage.performSearch(productKeyName);
        Assert.assertTrue(searchResultsPage.isSearchSuccessful());
    }

    @DataProvider
    public Object[][] getProductData() {
        return new Object[][]{
                {"Macbook", "MacBook Pro"},
                {"Macbook", "MacBook Air"},
                {"iMac", "iMac"},
                {"Samsung", "Samsung SyncMaster 941BW"},
                {"Samsung", "Samsung Galaxy Tab 10.1"}
        };

    }

    @Description("Acc page product search test -- Dev Name:  @praveena")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "getProductData", priority = 7)
    public void searchTest(String productKeyName, String mainProductName) {
        searchResultsPage = accPage.performSearch(productKeyName);
        if (searchResultsPage.isSearchSuccessful()) {
            productInfoPage = searchResultsPage.selectProduct(mainProductName);
            String actualProductHeader = productInfoPage.getProductHeader(mainProductName);
            Assert.assertEquals(actualProductHeader, mainProductName);
        }
    }
}
