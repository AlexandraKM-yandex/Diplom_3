package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Browser {

    protected WebDriver driver;
    public WebDriver initDriver() throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/browser.properties"));
        String browserProperty = properties.getProperty("testBrowser");

        BrowserType browserType = BrowserType.valueOf(browserProperty);

        switch (browserType){
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();

                break;

            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe/");
                driver = new ChromeDriver();
                driver.manage().window().maximize();

                break;

            default:
                throw new RuntimeException("Browser undefined");

        }
        return driver;
    }
}
