package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By buttonRegister = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By errorTextPassword = By.xpath(".//p[@class='input__error text_type_main-default']");
    private final By linkLogin = By.linkText("Войти");

    @Step("Ввод имени: {name}")
    public RegisterPage setName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        return this;
    }

    @Step("Ввод email: {email}")
    public RegisterPage setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public RegisterPage setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        return this;
    }

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickButtonRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonRegister)).click();
    }

    @Step("Текст ошибки под полем пароль отображается")
    public String getExceptionText() {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorTextPassword));
        return error.getText();
    }

    @Step("Клик на ссылку 'Войти' для перехода на страницу авторизации")
    public void clickLinkLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(linkLogin)).click();
    }



}
