import apiClient.UserClient;
import constants.Endpoints;
import constants.LoginEntryPoint;
import generators.UserGenerator;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import pages.MainPage;
import pages.RecoveryPage;
import pages.RegisterPage;

import java.time.Duration;

public abstract class BaseUiTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RecoveryPage recoveryPage;
    protected RegisterPage registerPage;
    protected UserClient userClient;
    protected User user;
    protected String accessToken;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        if ("yandex".equals(browser)) {
            // Для Yandex Browser
            System.setProperty("webdriver.chrome.driver", "C:/yandexdriver-26.4.3.894-win64/yandexdriver.exe");
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            try {
                driver = new ChromeDriver(options);
            } catch (Exception e) {
                System.err.println("Не удалось создать драйвер: " + e.getMessage());
                throw e;
            }
        } else {
            // Для Chrome
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(Endpoints.BASE_URL);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        recoveryPage = new RecoveryPage(driver);
        registerPage = new RegisterPage(driver);
        userClient = new UserClient();
        if (!(this instanceof RegisterTest)) {
            user = UserGenerator.userGenerate();
            accessToken = userClient.createUser(user).extract().path("accessToken");
        }
    }

    @After
    public void tearDown() {
        if (accessToken != null) userClient.deleteUser(accessToken);
        if (driver != null) driver.quit();
    }

    @Step("Открытие страниц авторизации через разные {entryPoint}")
    public void openLoginPage(LoginEntryPoint entryPoint) {
        switch (entryPoint) {
            case HOME_PAGE:
                mainPage.clickButtonLogin(); break;
            case PERSONAL_ACCOUNT:
                mainPage.clickButtonPersonalAccount(); break;
            case REGISTER_PAGE:
                mainPage.clickButtonPersonalAccount();
                loginPage.loginPageToLoad();
                loginPage.clickLinkRegister();
                registerPage.clickLinkLogin(); break;
            case RECOVERY_PAGE:
                mainPage.clickButtonPersonalAccount();
                loginPage.loginPageToLoad();
                loginPage.clickLinkRecoverPassword();
                recoveryPage.clickLinkLogin(); break;
        }
    }

}
