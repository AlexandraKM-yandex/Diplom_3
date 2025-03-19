package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static base.Constants.BASE_URL;

public class LoginPage extends BasePage {

    private final By emailInputField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInputField = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    public static final String LOGIN_PAGE_URL = BASE_URL + "login";

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Открыть страницу входа")
    public LoginPage openLoginPage(){
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    @Step("Заполнить поле с Email")
    public void setEmail(String email) throws InterruptedException {
        inputTextField(emailInputField, email);
    }

    @Step("Заполнить поле с паролем")
    public void setPassword(String password) throws InterruptedException {
        inputTextField(passwordInputField, password);
    }

    @Step("Нажать на кнопку 'Войти'")
    public void clickLoginButton() {
        clickElementButton(loginButton);
    }
}

