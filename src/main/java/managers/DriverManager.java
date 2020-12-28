package managers;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropConst;

import static utils.PropConst.TYPE_BROWSER;

public class DriverManager {

        private static WebDriver driver;

        private static TestPropManager propManager = TestPropManager.getTestPropManager();

        private DriverManager() {
        }

        private static void initDriver() {
            if(OS.isFamilyWindows())
            switch (propManager.getProperty(TYPE_BROWSER)) {
                default:
                    System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropConst.PATH_CHROME_DRIVER_WINDOWS));
                    driver = new ChromeDriver();
            } else {
                switch (propManager.getProperty(TYPE_BROWSER)) {
                    default:
                    System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropConst.PATH_CHROME_DRIVER_MAC));
                    driver = new ChromeDriver();
                }
            }
        }

        public static WebDriver getDriver() {
            if (driver == null) {
                initDriver();
            }
            return driver;
        }

        public static void quitDriver() {
            driver.quit();
            driver = null;
        }
    }
