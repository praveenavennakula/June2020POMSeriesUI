package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountsPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    By logOutLink = By.linkText("Logout");
    By search = By.name("search");
    By searchIcon = By.cssSelector("span.input-group-btn button");
    By accSecHeaders = By.cssSelector("div#content h2");

    public AccountsPage(WebDriver driver) {
        this.driver = driver;
      eleUtil=new ElementUtil(driver);
    }

    public String getAccPageTitle() {
        String title = driver.getTitle();
        System.out.println(AppConstants.ACC_PAGE_TITLE + title);
        return title;
    }

    public boolean getAcctPageURL() {
        String url = driver.getCurrentUrl();
        System.out.println("Account Page URL is " + url);
        if (url.contains(AppConstants.ACC_PAGE_URL_PARAM)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLogoutLinkExist() {
        return driver.findElement(logOutLink).isDisplayed();
    }

    public boolean isSearchExist() {
        return driver.findElement(search).isDisplayed();
    }

    public ArrayList<String> getAcctSecHeaders() {
        List<WebElement> secList = driver.findElements(accSecHeaders);
        System.out.println("Total section of headers:" + secList.size());
        ArrayList<String> acctSecTextList = new ArrayList<String>();
        for (WebElement e : secList) {
            String text = e.getText();
            acctSecTextList.add(text);
        }
        return acctSecTextList;

    }

    public SearchResultsPage performSearch(String productKey) {
        System.out.println("Product Name is " + productKey);
        if (isSearchExist()) {

            eleUtil.doSendKeys(search, productKey);
            eleUtil.doClick(searchIcon);
            return new SearchResultsPage(driver);
        } else {
            System.out.println("search field is not present on the page");
            return null;
        }
    }




}
