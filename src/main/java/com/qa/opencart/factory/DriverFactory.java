package com.qa.opencart.factory;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exception.FrameworkException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

public class DriverFactory {
    public WebDriver driver;
    private static final Logger LOG = Logger.getLogger(DriverFactory.class);
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    public static String highlight;
    public OptionsManager optionsManager;
    public Properties prop;


    /**
     * This method is used to initialize the driver as per the browser name
     *
     * @param prop
     * @return this will return the driver instance
     */
    public WebDriver initDriver(Properties prop) {
        String browserName = prop.getProperty("browser").toLowerCase();
        System.out.println("browser name is : " + browserName);
        LOG.info("browser name is : " + browserName);
        optionsManager = new OptionsManager(prop);
        highlight = prop.getProperty("highlight");

        if (browserName.equals("chrome")) {
            if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                init_remoteDriver(browserName);
            } else {
                WebDriverManager.chromedriver().setup();
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                //driver=new ChromeDriver();
            }
        } else if (browserName.equals("firefox")) {
            if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                init_remoteDriver(browserName);
            } else {
                WebDriverManager.firefoxdriver().setup();
                // driver=new FirefoxDriver();
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
            }
        } else if (browserName.equals("edge")) {
            if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                init_remoteDriver(browserName);
            } else {
                WebDriverManager.edgedriver().setup();
                // driver=new EdgeDriver();
                tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
            }
        }

        else if (browserName.equals("safari")) {
            //only local execution---docker does not support safari
            tlDriver.set(new SafariDriver());
        }
            else {
            System.out.println("Please pass the right browser name: " + browserName);
            LOG.error("Please pass the right browser name : " + browserName);
            throw new FrameworkException(AppError.BROWSER_NOT_FOUND);
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().get(prop.getProperty("url"));
        return getDriver();
    }
    /*
     * remote execution
     */
    private void init_remoteDriver(String browser) {
        System.out.println("Running test cases on remote GRID machine....with browser: " + browser);
        try {
            switch (browser) {

                case "chrome":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
                    break;
                case "firefox":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
                    break;
                case "edge":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
                    break;
                default:
                    System.out.println("Please pass the right browser for remote execution...." + browser);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public Properties initProp() {
         prop = new Properties();
        FileInputStream fp = null;
        String envname = System.getProperty("env");//use when we want to pass env value from cmd mvn
        // String envname=System.getenv("env");//used when we want to pass env value through run config-->env vars-->add->env,dev
        System.out.println("Running Test Cases on the environment: " + envname);
        LOG.info("-----> Running test cases on environment: ----->" + envname);
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
                        throw new FrameworkException(AppError.ENV_NOT_FOUND);
                        //  break;when we throw, it will come out of the loop.
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

    public static String getScreenshot() {
        String srcPath = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
/*        String destpath = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File source=new File(srcPath);
        File destination = new File(destpath);

        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return "data:image/jpg;base64, " + srcPath;

      /* // works fine
       String scnShot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        return "data:image/jpg;base64, " + scnShot;*/


    }
}
