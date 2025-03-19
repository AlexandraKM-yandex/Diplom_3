package user;

import base.HttpClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserApi extends HttpClient {

    public static final String CREATE_USER_URI = "/api/auth/register";
    public static final String LOGIN_USER_URI = "/api/auth/login";
    public static final String ACT_USER_URI = "/api/auth/user";

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user){
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(CREATE_USER_URI)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String token){
        return given()
                .spec(requestSpecification())
                .and()
                .header("Authorization", token)
                .when()
                .delete(ACT_USER_URI)
                .then();
    }

    @Step("Логин")
    public ValidatableResponse loginUser(User user){
        return given()
                .spec(requestSpecification())
                .and()
                .body(user)
                .when()
                .post(LOGIN_USER_URI)
                .then();
    }
}

