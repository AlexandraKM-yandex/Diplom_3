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

    private final By inactiveBunsTabButton = By.xpath("//span[text()='Булки']/parent::div");
    private final By activeBunsTabButton = By.xpath("//div[contains(@class, 'current') and ./span[text()='Булки']]");

    private final By inactiveSaucesTabButton = By.xpath("//div[./span[text()='Соусы']]");
    private final By activeSaucesTabButton = By.xpath("//div[contains(@class, 'current') and ./span[text()='Соусы']]");

    private final By inactiveFillingsTabButton = By.xpath("//div[./span[text()='Начинки']]");
    private final By activeFillingsTabButton = By.xpath("//div[contains(@class, 'current') and ./span[text()='Начинки']]");

    private final By accountButton = By.xpath("//a[@href='/account']");
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть страницу конструктора")
    public ConstructorPage openConstructorPage() {
        driver.get(CONSTRUCTOR_PAGE_URL);
        return this;
    }

    @Step("Открыть аккаунт пользователя")
    public void clickUserAccountButton() {
        clickElement(accountButton);
    }

    @Step("Перейти на вкладку входа в аккаунт")
    public void clickEnterUserAccountButton() {
        clickElement(loginButton);
    }

    private void clickElement(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Выбрать вкладку 'Булки'")
    public void clickBunsTab() {
        clickTab(inactiveBunsTabButton, activeBunsTabButton);
    }

    @Step("Выбрать вкладку 'Соусы'")
    public void clickSaucesTab() {
        clickTab(inactiveSaucesTabButton, activeSaucesTabButton);
    }

    @Step("Выбрать вкладку 'Начинки'")
    public void clickFillingsTab() {
        clickTab(inactiveFillingsTabButton, activeFillingsTabButton);
    }

    private void clickTab(By inactiveTabButton, By activeTabButton) {
        WebElement tabElement = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(inactiveTabButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabElement);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(activeTabButton, "class", "tab_tab_type_current__2BEPc"));
    }

    private boolean isTabCurrent(By activeTabLocator) {
        try {
            WebElement tabElement = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(activeTabLocator));
            return tabElement.getAttribute("class").contains("tab_tab_type_current__2BEPc");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить, является ли вкладка 'Булки' активной")
    public boolean checkIfBunsTabIsCurrent() {
        return isTabCurrent(activeBunsTabButton);
    }

    @Step("Проверить, является ли вкладка 'Соусы' активной")
    public boolean checkIfSaucesTabIsCurrent() {
        return isTabCurrent(activeSaucesTabButton);
    }

    @Step("Проверить, является ли вкладка 'Начинки' активной")
    public boolean checkIfFillingsTabIsCurrent() {
        return isTabCurrent(activeFillingsTabButton);
    }

    @Step("Перейти на страницу входа")
    public void loginVersions(int version) {
        if (version == 1) {
            clickUserAccountButton();
        } else if (version == 2) {
            clickEnterUserAccountButton();
        }
    }
}

