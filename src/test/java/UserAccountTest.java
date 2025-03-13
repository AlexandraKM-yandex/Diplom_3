import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ConstructorPage;
import pages.LoginPage;
import pages.UserAccountPage;
import user.User;
import user.UserApi;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class UserAccountTest extends BaseUITest {

    protected UserApi userApi;
    protected User user;
    private final String name = "Sasha";
    private final String password = "sasha1234567";
    private final String email = "sasha@qweasdmail.com";
    protected String accessToken;

    @Before
    public void setUp() throws InterruptedException {
        userApi = new UserApi();
        user = new User(name, password, email);

        ValidatableResponse response = userApi.createUser(user);
        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("success", is(true));

        user.setName(null);
        response = userApi.loginUser(user);
        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        accessToken = response.extract().path("accessToken");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(LoginPage.LOGIN_PAGE_URL));

        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(ConstructorPage.CONSTRUCTOR_PAGE_URL));

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.clickUserAccountButton();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(UserAccountPage.userAccountPageUrl));
    }

    @After
    public void userDelete() {
        ValidatableResponse response = userApi.deleteUser(accessToken);
        response.log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .and()
                .body("success", is(true));
    }

    @Test
    @DisplayName("Открытие страницы учетной записи пользователя")
    @Description("Проверка, что при входе в систему пользователь может попасть на страницу своей учетной записи.")
    public void openUserAccount() throws InterruptedException {
        String currentUrl = driver.getCurrentUrl();
        assertEquals(UserAccountPage.userAccountPageUrl, currentUrl);
    }

    @Test
    @DisplayName("Выход из учетной записи пользователя")
    @Description("Проверка, что пользователь может выйти из своей учетной записи и перейти на страницу логина.")
    public void logoutFromUserAccount() {

        UserAccountPage userAccountPage = new UserAccountPage(driver);
        userAccountPage.clickLogoutButton();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(LoginPage.LOGIN_PAGE_URL));

        String currentUrl = driver.getCurrentUrl();
        assertEquals(LoginPage.LOGIN_PAGE_URL, currentUrl);
    }
}
