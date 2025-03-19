package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static base.Constants.BASE_URL;

public class ForgotPasswordPage extends BasePage {

    private final By backToLoginButton = By.xpath("//a[@href='/login']");
    public static final String PASSWORD_RECOVERY_PAGE_URL = BASE_URL + "forgot-password";

    public ForgotPasswordPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Открыть страницу восстановления пароля")
    public ForgotPasswordPage openForgotPasswordPage(){
        driver.get(PASSWORD_RECOVERY_PAGE_URL);
        return this;
    }

    @Step("Перейти на страницу входа")
    public void switchToLoginPage() {
        clickElementButton(backToLoginButton);
    }
}
