package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPage {

    private final WebDriver driver;

    public RecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By linkLogin = By.linkText("Войти");

    @Step("Клин на ссылку 'Войти' из страницы восстановления пароля")
    public void clickLinkLogin() {
        driver.findElement(linkLogin).click();
    }

}
