package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    By productSearchLayout= By.cssSelector("div.product-layout");
    public SearchResultsPage(WebDriver driver){
        this.driver=driver;
        eleUtil=new ElementUtil(driver);
    }

    public boolean isSearchSuccessful(){
        List<WebElement> searchList=eleUtil.waitForElementsToBeVisible(productSearchLayout, AppConstants.DEFAULT_LARGE_TIME_OUT);
        if(searchList.size()>0){
            System.out.println("Search is successfully done");
            return true;
        }
        return false;
    }
public ProductInfoPage selectProduct(String mainproductName){
        By mainPrName=By.linkText(mainproductName);
        eleUtil.doClick(mainPrName);
    System.out.println("Main Product Name is:"+mainproductName);
        return new ProductInfoPage(driver);
}

}
