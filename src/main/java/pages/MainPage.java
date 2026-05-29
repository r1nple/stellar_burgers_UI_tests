package pages;

import constants.SectionsNames;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By buttonPersonalAccount = By.xpath("//a[@href='/account']");
    private final By buttonLogin = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By headerConstructor = By.xpath(".//h1[text()='Соберите бургер']");
    private final By tabBuns = By.xpath("//span[text()='Булки']/parent::div");
    private final By tabSauces = By.xpath("//span[text()='Соусы']/parent::div");
    private final By tabFillings = By.xpath("//span[text()='Начинки']/parent::div");

    @Step("Клик по кнопке 'Войти в аккаунт' из главной страницы")
    public void clickButtonLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonLogin))
                .click();
    }

    @Step("Клик по кнопке 'Личный кабинет' из главной страницы")
    public void clickButtonPersonalAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonPersonalAccount))
                .click();
    }

    @Step("Отображение конструктора на главной странице")
    public void constructorToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(headerConstructor));
    }

    @Step("Клики по секциям конструктора: {section}")
    public void clickSection(SectionsNames section) {
        By tabLocator = getSectionLocator(section);
        WebElement tabElement = new WebDriverWait(driver, Duration.ofSeconds(50))
                .until(ExpectedConditions.elementToBeClickable(tabLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tabElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tabElement);
        new WebDriverWait(driver, Duration.ofSeconds(50))
                .until(ExpectedConditions.attributeContains(tabLocator, "class", "tab_tab_type_current"));
    }

    @Step("Извлечение атрибута 'class' секций: {section}")
    public String getClassName(SectionsNames section) {
        return driver.findElement(getSectionLocator(section)).getAttribute("class");
    }

    private By getSectionLocator(SectionsNames section) {
        switch (section) {
            case BUNS: return tabBuns;
            case SAUCES: return tabSauces;
            case FILLINGS: return tabFillings;
            default:
                throw new IllegalArgumentException("Раздел конструктора неизвестен: " + section);
        }
    }

}
