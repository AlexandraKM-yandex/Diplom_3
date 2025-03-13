import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.ConstructorPage;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.RegisterPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class BurgerLogoTest extends BaseUITest {

    @Test
    @DisplayName("Клик по логотипу на странице регистрации ведет на конструктор")
    @Description("Проверка, что клик по логотипу на странице регистрации перенаправляет на страницу конструктора.")
    public void verifyLogoRedirectsToConstructorFromRegisterPage() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegistrationPage();
        registerPage.clickOnLogo();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(ConstructorPage.CONSTRUCTOR_PAGE_URL));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(ConstructorPage.CONSTRUCTOR_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Клик по логотипу на странице входа ведет на конструктор")
    @Description("Проверка, что клик по логотипу на странице входа перенаправляет на страницу конструктора.")
    public void verifyLogoRedirectsToConstructorFromLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.clickOnLogo();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(ConstructorPage.CONSTRUCTOR_PAGE_URL));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(ConstructorPage.CONSTRUCTOR_PAGE_URL, currentUrl);
    }

    @Test
    @DisplayName("Клик по логотипу на странице восстановления пароля ведет на конструктор")
    @Description("Проверка, что клик по логотипу на странице восстановления пароля перенаправляет на страницу конструктора.")
    public void verifyLogoRedirectsToConstructorFromPasswordRecoveryPage() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.openForgotPasswordPage();
        forgotPasswordPage.clickOnLogo();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(ConstructorPage.CONSTRUCTOR_PAGE_URL));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(ConstructorPage.CONSTRUCTOR_PAGE_URL, currentUrl);
    }
}
