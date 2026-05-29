import constants.Endpoints;
import generators.UserGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegisterTest extends BaseUiTest {

    private static final String EXPECTED_LOGIN_URL = Endpoints.BASE_URL + "/login";
    private static final String EXPECTED_ERROR_TEXT = "Некорректный пароль";
    private String tempToken;

    @Before
    public void initUser() {
        user = UserGenerator.userGenerate();
    }

    @After
    public void deleteCreatedUser() {
        if (tempToken == null) {
            tempToken = userClient.loginUser(user).extract().path("accessToken");
        }
        if (tempToken != null) userClient.deleteUser(tempToken);
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка, что регистрация проходит, и открывается страница авторизации")
    public void registerSuccessfullyWithValidDataTest() {
        goToRegisterPage();
        registerPage.setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .clickButtonRegister();
        loginPage.loginPageToLoad();
        assertEquals(EXPECTED_LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля. Минимальный пароль — шесть символов.")
    @Description("Проверка, что при вводе в поле пароля меньше 6 символов, появляется ошибка")
    public void showErrorForShortPasswordTest() {
        goToRegisterPage();
        registerPage.setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword("qwe")
                .clickButtonRegister();
        String error = registerPage.getExceptionText();
        assertEquals(EXPECTED_ERROR_TEXT, error);
    }

    @Step("Открытие страницы авторизации через кнопку 'Личный кабинет'")
    private void goToRegisterPage() {
        mainPage.clickButtonPersonalAccount();
        loginPage.loginPageToLoad();
        loginPage.clickLinkRegister();
    }

}
