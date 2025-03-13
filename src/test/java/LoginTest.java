import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ConstructorPage;
import pages.LoginPage;
import pages.ForgotPasswordPage;
import pages.RegisterPage;
import user.User;
import user.UserApi;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginTest extends BaseUITest {

    protected UserApi userApi;
    protected User user;
    private final String name;
    private final String password;
    private final String email;
    private final int loginVersion;
    protected String accessToken;

    public LoginTest(int loginVersion, String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.loginVersion = loginVersion;
    }

    @Before
    public void setUp() {
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

    @Parameterized.Parameters
    public static Object[][] getUserData() {
        return new Object[][] {
                { 1, "Sashka", "password123", "sashka@123.com" },
                { 2, "Sveta", "qwerty123", "svetik12345@123.com" },
                { 3, "Masha", "123qwerty123", "mariya123@123.com" },
                { 4, "Dasha", "password115", "darya12345@321.com" }
        };
    }

    @Test
    @DisplayName("Авторизация через различные способы")
    @Description("Проверка успешной авторизации через разные варианты входа в систему.")
    public void testLoginWithDifferentMethods() throws InterruptedException {
        switch (loginVersion) {
            case 1:
            case 2:
                ConstructorPage constructorPage = new ConstructorPage(driver);
                constructorPage.openConstructorPage();
                constructorPage.loginVersions(loginVersion);
                break;
            case 3:
                RegisterPage registerPage = new RegisterPage(driver);
                registerPage.openRegistrationPage();
                registerPage.switchToLoginPage();
                break;
            case 4:
                ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
                forgotPasswordPage.openForgotPasswordPage();
                forgotPasswordPage.switchToLoginPage();
                break;
        }

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(LoginPage.LOGIN_PAGE_URL));

        String currentUrl = driver.getCurrentUrl();
        assertEquals(LoginPage.LOGIN_PAGE_URL, currentUrl);

        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(ConstructorPage.CONSTRUCTOR_PAGE_URL));
        currentUrl = driver.getCurrentUrl();
        assertEquals(ConstructorPage.CONSTRUCTOR_PAGE_URL, currentUrl);
    }
}
