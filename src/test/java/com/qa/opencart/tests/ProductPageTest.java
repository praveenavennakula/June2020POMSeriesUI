package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductPageTest extends BaseTest {
    @BeforeTest
    public void prodInfoSetUp() {
        accPage = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }


    @Test
    public void productheaderTest() {
        searchResultsPage = accPage.performSearch("macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
        String actPrdHeader = productInfoPage.getProductHeader("MacBook Pro");
        Assert.assertEquals(actPrdHeader, "MacBook Pro");

    }

    @DataProvider
    public Object[][] getProductInfoData() {
        return new Object[][]{
                {"macbook", "MacBook Pro", AppConstants.MACBOOK_PRO_IMAGES_COUNT},
                {"Macbook", "MacBook Air", AppConstants.MACBOOK_AIR_IMAGES_COUNT},
                {"iMac", "iMac", AppConstants.IMAC_IMAGES_COUNT},

        };

    }

    @Test(dataProvider = "getProductInfoData")
    public void productImageCountTest(String searchKey, String mainPrdtName, int imagesCount) {
        searchResultsPage = accPage.performSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(mainPrdtName);
        int actProdImageCount = productInfoPage.getProductImageCount();
        System.out.println("Actual Product Image count: " + actProdImageCount);
        Assert.assertEquals(actProdImageCount, imagesCount);

    }

    //	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
    @DataProvider
    public Object[][] getProductInfoMetaData() {
        return new Object[][]{
                {"Brand", "Apple"},
                {"Product Code", "Product 18"},
                {"Reward Points", "800"},
                {"Availability", "In Stock"},

        };

    }

    @Test(dataProvider = "getProductInfoMetaData")
    public void productMetaDataTest(String metaDataKey, String metaDataVal) {
        searchResultsPage = accPage.performSearch("macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
        Map<String, String> actMetadataMap = productInfoPage.getProductMetadataInfo();
        Assert.assertEquals(actMetadataMap.get(metaDataKey), metaDataVal);
/*        Assert.assertEquals(actMetadataMap.get("Product Code"),"Product 18");
        Assert.assertEquals(actMetadataMap.get("Reward Points"),"800");
        Assert.assertEquals(actMetadataMap.get("Availability"),"In Stock");*/
    }

    //$2,000.00
    // Ex Tax: $2,000.00
    @Test
    public void productMetaDataPriceTest() {
        searchResultsPage = accPage.performSearch("macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
        Map<String, String> actMetadataMap = productInfoPage.getProductMetadataPriceInfo();
        Assert.assertEquals(actMetadataMap.get("price"), "$2,000.00");
        Assert.assertEquals(actMetadataMap.get("Ex Tax"), "$2,000.00");

    }


}
