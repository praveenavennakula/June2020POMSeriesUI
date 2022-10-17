package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private By productImages=By.cssSelector("ul.thumbnails img");
    By prdtMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    By prdtPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
    private Map<String,String> prdtInfoMap;

    public ProductInfoPage(WebDriver driver) {
        this.driver=driver;
        eleUtil=new ElementUtil(driver);

    }

    public String getProductHeader(String mainProductName){
        String xpath= "//h1[text()='"+mainProductName+"']";
       String productHeader=eleUtil.doGetText(By.xpath(xpath));
        System.out.println("Product Header is:"+productHeader);
        return productHeader;
    }

    public int getProductImageCount(){
       return eleUtil.waitForElementsToBeVisible(productImages, AppConstants.DEFAULT_LARGE_TIME_OUT).size();
    }

    public String getProductPageTitle(String productTitle){
        return eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT,productTitle);
    }

    public String getProductPageUrl(String searchKey){
        return eleUtil.waitForURLContains(AppConstants.DEFAULT_TIME_OUT,searchKey);

    }

    //	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock

    //meta data is stored in the HashMap
public Map<String,String> getProductMetadataInfo(){
    List<WebElement> metaList=eleUtil.getElements(prdtMetaData);
    prdtInfoMap=new HashMap<String,String>();
    for (WebElement e:metaList){
        String metaText=e.getText();
        String meta[]=metaText.split(":");
        String metaKey=meta[0].trim();
        String metaValue=meta[1].trim();
        prdtInfoMap.put(metaKey,metaValue);
    }
    prdtInfoMap.forEach((k,v)-> System.out.println(k+":"+v));
   return prdtInfoMap;

}
    //$2,000.00
    // Ex Tax: $2,000.00
    public Map<String,String> getProductMetadataPriceInfo(){
        List<WebElement> metaList=eleUtil.getElements(prdtPriceData);
        prdtInfoMap=new HashMap<String,String>();
        for (WebElement e:metaList){
            String metaText=e.getText();
            String meta[];
            String metaKey;
            String metaValue;
            if(metaText.contains(":")) {
                 meta = metaText.split(":");
                metaKey = meta[0].trim();
                 metaValue = meta[1].trim();
                prdtInfoMap.put(metaKey,metaValue);
            }else {
                prdtInfoMap.put("price",metaText);
            }

        }
        prdtInfoMap.forEach((k,v)-> System.out.println(k+":"+v));
        return prdtInfoMap;

    }

}
