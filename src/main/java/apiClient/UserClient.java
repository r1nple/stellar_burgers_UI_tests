package apiClient;

import constants.Endpoints;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import models.LoginRequest;
import models.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(Endpoints.REGISTER)
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(User user) {
        LoginRequest request = new LoginRequest(user.getEmail(), user.getPassword());
        return given()
                .baseUri(Endpoints.BASE_URL)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(Endpoints.LOGIN)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .baseUri(Endpoints.BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete(Endpoints.DELETE_USER)
                .then();
    }

}
