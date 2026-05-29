import constants.Endpoints;
import constants.LoginEntryPoint;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginTest extends BaseUiTest {

    private final LoginEntryPoint entryPoint;

    public LoginTest(LoginEntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    @Parameterized.Parameters(name = "Место входа: {0}")
    public static Object[][] testData() {
        return new Object[][] {
                {LoginEntryPoint.HOME_PAGE},
                {LoginEntryPoint.PERSONAL_ACCOUNT},
                {LoginEntryPoint.RECOVERY_PAGE},
                {LoginEntryPoint.REGISTER_PAGE}
        };
    }

    @Test
    @DisplayName("Авторизация пользователя из разных мест входа")
    @Description("Проверка, что пользователь успешно авторизуется из разных мест входа")
    public void loginFromDifferentEntryPointTest() {
        openLoginPage(entryPoint);
        loginPage.loginPageToLoad();
        loginPage.emailEnter(user.getEmail());
        loginPage.passwordEnter(user.getPassword());
        loginPage.clickButtonLogin();
        mainPage.constructorToLoad();
        assertEquals(Endpoints.BASE_URL + "/", driver.getCurrentUrl());
    }

}
