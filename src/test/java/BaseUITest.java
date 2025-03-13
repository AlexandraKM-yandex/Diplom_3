import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import browser.Browser;

import java.io.IOException;

public class BaseUITest {

    protected WebDriver driver;

    @Before
    public void startUp() throws IOException {
        Browser browser = new Browser();
        driver = browser.initDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
