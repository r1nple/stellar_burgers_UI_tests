import constants.Endpoints;
import constants.SectionsNames;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ConstructorTest extends BaseUiTest {

    private WebDriver driver;
    private MainPage mainPage;
    private final SectionsNames sectionsNames;

    public ConstructorTest(SectionsNames sectionsNames) {
        this.sectionsNames = sectionsNames;
    }

    @Parameterized.Parameters(name = "Блок конструктора: {0}")
    public static Object[][] testData() {
        return new Object[][] {
                {SectionsNames.FILLINGS},
                {SectionsNames.SAUCES},
                {SectionsNames.BUNS}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        driver.get(Endpoints.BASE_URL);
        mainPage.constructorToLoad();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переходы по блокам конструктора")
    @Description("Проверка, что при клике на блоки активируется блок")
    public void switchConstructorSectionTest() {
        if (sectionsNames == SectionsNames.FILLINGS) mainPage.clickSection(SectionsNames.FILLINGS);
        else mainPage.clickSection(SectionsNames.FILLINGS);
        mainPage.clickSection(sectionsNames);
        String cls = mainPage.getClassName(sectionsNames);
        assertTrue("Блок не активен: " + sectionsNames, cls.contains("tab_tab_type_current"));
    }

}
