package com.qa.opencart.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class DriverFactory {
    public WebDriver driver;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    public static String highlight;
    public OptionsManager optionsManager;

    /**
     * This method is used to initialize the driver as per the browser name
     *
     * @param prop
     * @return this will return the driver instance
     */
    public WebDriver initDriver(Properties prop) {
        String browserName = prop.getProperty("browser").toLowerCase();
        optionsManager = new OptionsManager(prop);
        highlight = prop.getProperty("highlight");
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
            //driver=new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            // driver=new FirefoxDriver();
            tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
        } else if (browserName.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            // driver=new EdgeDriver();
            tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().get(prop.getProperty("url"));
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public Properties initProp() {
        Properties prop = new Properties();
        FileInputStream fp = null;
        String envname = System.getProperty("env");
                //System.getenv("env");
        System.out.println("Running Test Cases on the environment: " + envname);
        //mvn clean install -Denv="qa"   //command line argument with env value as qa
        //mvn clean install           //command line argument without any input for env, so qa is default env

        if (envname == null) {
            System.out.println("No env is given so running it on QA");
            try {
                fp = new FileInputStream("./src/test/resources/config/qa.config.properties");


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                switch (envname) {
                    case "qa":

                        fp = new FileInputStream("./src/test/resources/config/qa.config.properties");

                        break;

                    case "dev":

                        fp = new FileInputStream("./src/test/resources/config/dev.config.properties");

                        break;

                    case "stage":

                        fp = new FileInputStream("./src/test/resources/config/stage.config.properties");

                        break;

                    case "uat":

                        fp = new FileInputStream("./src/test/resources/config/uat.config.properties");

                        break;

                    default:
                        System.out.println("Please pass the right environment name: " + envname);
                        break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
      /*  try {
             fp = new FileInputStream("./src/test/resources/config/config.properties");
            prop.load(fp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            prop.load(fp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    public static String getScreenshot(){
       String srcPath = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
/*        String destpath = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File source=new File(srcPath);
        File destination = new File(destpath);

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return "data:image/jpg;base64, " +srcPath;

      /* // works fine
       String scnShot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        return "data:image/jpg;base64, " + scnShot;*/


    }
}
