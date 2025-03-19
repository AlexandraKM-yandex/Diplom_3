import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.RegisterPage;
import user.User;
import user.UserApi;

import java.time.Duration;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class RegisterTest extends BaseUITest {

    private final Faker faker = new Faker();
    private final String name = faker.name().firstName();
    private final String email = faker.internet().emailAddress();
    private final String password = faker.internet().password(8, 12);

    @After
    public void deleteUser() {
        UserApi userApi = new UserApi();
        User user = new User(null, password, email);
        ValidatableResponse response = userApi.loginUser(user);
        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        String accessToken = response.extract().path("accessToken");
        response = userApi.deleteUser(accessToken);
        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .body("success", is(true));
    }

    @Test
    @DisplayName("Регистрация нового пользователя через UI")
    @Description("Проверка регистрации нового пользователя, после регистрации проверяется переход на страницу логина.")
    public void testUserRegistration() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegistrationPage();

        registerPage.setName(name);
        registerPage.setEmail(email);
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(LoginPage.LOGIN_PAGE_URL));

        String currentUrl = driver.getCurrentUrl();
        assertEquals(LoginPage.LOGIN_PAGE_URL, currentUrl);
    }
}
