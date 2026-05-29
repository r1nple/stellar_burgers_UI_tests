package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By headerLogin = By.xpath(".//h2[text()='Вход']");
    private final By emailField = By.xpath("//input[@name='name' or @type='text']");
    private final By passwordField = By.xpath("//input[@name='Пароль' or @type='password']");
    private final By buttonLogin = By.xpath(".//button[text()='Войти']");
    private final By linkRegister = By.linkText("Зарегистрироваться");
    private final By linkRecoverPassword = By.linkText("Восстановить пароль");

    @Step("Загрузка страницы логина")
    public void loginPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> d.findElement(headerLogin).isDisplayed());
    }

    @Step("Ввод email: {email}")
    public void emailEnter(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void passwordEnter(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonLogin() {
        driver.findElement(buttonLogin).click();
    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public void clickLinkRegister() {
        driver.findElement(linkRegister).click();
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public void clickLinkRecoverPassword() {
        driver.findElement(linkRecoverPassword).click();
    }

}
