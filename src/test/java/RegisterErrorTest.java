import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import pages.RegisterPage;

import static org.junit.Assert.assertEquals;

public class RegisterErrorTest extends BaseUITest {

    @Test
    @DisplayName("Невозможно зарегистрироваться с коротким паролем")
    @Description("Проверка, что регистрация невозможна, если пароль короче 6 символов.")
    public void testRegistrationWithShortPassword() throws InterruptedException {

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegistrationPage();

        registerPage.setName(RandomStringUtils.randomAlphabetic(4));
        registerPage.setEmail(RandomStringUtils.randomAlphabetic(4));
        registerPage.setPassword(RandomStringUtils.randomAlphabetic(4));
        registerPage.clickRegisterButton();

        String expectedError = "Некорректный пароль";
        assertEquals("Ошибка при регистрации: " + registerPage.getRegistrationErrorMessage(), expectedError, registerPage.getRegistrationErrorMessage());
    }
}