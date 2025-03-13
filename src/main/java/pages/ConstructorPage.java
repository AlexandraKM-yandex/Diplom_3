package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static base.Constants.BASE_URL;

public class ConstructorPage extends BasePage {

    public static final String CONSTRUCTOR_PAGE_URL = BASE_URL;

    private final By accountButton = By.xpath("//a[@href='/account']");
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By bunsTabButton = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesTabButton = By.xpath("//div[./span[text()='Соусы']]");
    private final By fillingsTabButton = By.xpath("//div[./span[text()='Начинки']]");

    public ConstructorPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Открыть страницу конструктора")
    public ConstructorPage openConstructorPage(){
        driver.get(CONSTRUCTOR_PAGE_URL);
        return this;
    }

    @Step("Открыть аккаунт пользователя")
    public void clickUserAccountButton(){
        clickElement(accountButton);
    }

    @Step("Перейти на вкладку входа в аккаунт")
    public void clickEnterUserAccountButton(){
        clickElement(loginButton);
    }

    private void clickElement(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Выбрать вкладку 'Булки'")
    public void clickBunsTab(){
        clickTab(bunsTabButton);
    }

    @Step("Выбрать вкладку 'Соусы'")
    public void clickSaucesTab(){
        clickTab(saucesTabButton);
    }

    @Step("Выбрать вкладку 'Начинки'")
    public void clickFillingsTab(){
        clickTab(fillingsTabButton);
    }

    private void clickTab(By tabLocator){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(tabLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(tabLocator));
    }

    private boolean isTabCurrent(By tabLocator) {
        WebElement tabElement = driver.findElement(tabLocator);
        return tabElement.getAttribute("class").contains("current");
    }

    public boolean checkIfTabIsCurrent(By tabLocator, By... otherTabs) {
        if (isTabCurrent(tabLocator)) {
            for (By otherTab : otherTabs) {
                if (isTabCurrent(otherTab)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean checkIfBunsTabIsCurrent(){
        return checkIfTabIsCurrent(bunsTabButton, saucesTabButton, fillingsTabButton);
    }

    public boolean checkIfSaucesTabIsCurrent(){
        return checkIfTabIsCurrent(saucesTabButton, bunsTabButton, fillingsTabButton);
    }

    public boolean checkIfFillingsTabIsCurrent(){
        return checkIfTabIsCurrent(fillingsTabButton, bunsTabButton, saucesTabButton);
    }

    @Step("Перейти на страницу входа")
    public void loginVersions(int version){
        if (version == 1) {
            clickUserAccountButton();
        } else if (version == 2) {
            clickEnterUserAccountButton();
        }
    }
}

