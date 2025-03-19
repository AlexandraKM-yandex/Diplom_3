package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    private final By logo = By.className("AppHeader_header__logo__2D0X2");

    void inputTextField(By fieldLocator, String inputText) throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));

        driver.findElement(fieldLocator).click();
        driver.findElement(fieldLocator).sendKeys(inputText);
    }

    void clickElementButton(By elementLocator){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(elementLocator));
        driver.findElement(elementLocator).click();
    }

    public void clickOnLogo(){
        clickElementButton(logo);
    }
}
