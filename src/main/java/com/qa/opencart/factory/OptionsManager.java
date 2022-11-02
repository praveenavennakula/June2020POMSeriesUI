package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {
    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;
    private EdgeOptions eo;
    public OptionsManager(Properties prop){
        this.prop=prop;
    }

    public ChromeOptions getChromeOptions(){
        co=new ChromeOptions();

     if(Boolean.parseBoolean(prop.getProperty("headless"))){
         co.setHeadless(true);
     }

       if(Boolean.parseBoolean(prop.getProperty("incognito"))){
        co.addArguments("--incognito");
    }
       
       
       if(Boolean.parseBoolean(prop.getProperty("remote"))){
           co.setBrowserVersion(prop.getProperty("browserversion"));
           co.setPlatformName("linux");
           co.setCapability("enableVNC", true);
           co.setCapability("name", "OpenAppTest - " + prop.getProperty("testname"));
       }
     return co;
}

    public FirefoxOptions getFirefoxOptions(){
        fo=new FirefoxOptions();

        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            fo.setHeadless(true);
        }

        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            fo.addArguments("--incognito");
        }
        if(Boolean.parseBoolean(prop.getProperty("remote"))){
            fo.setBrowserVersion(prop.getProperty("browserversion"));
            fo.setPlatformName("linux");
            fo.setCapability("enableVNC", true);
            fo.setCapability("name", "OpenAppTest - " + prop.getProperty("testname"));
        }
        return fo;
    }
    public EdgeOptions getEdgeOptions(){
        eo=new EdgeOptions();

        if(Boolean.parseBoolean(prop.getProperty("headless"))){
            eo.setHeadless(true);
        }

        if(Boolean.parseBoolean(prop.getProperty("incognito"))){
            eo.addArguments("--incognito");
        }
        return eo;
    }
}
