package astondevs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CareerPageTest {
    WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("start-maximized");
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("https://astondevs.com/career");
        Duration dur = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, dur);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("jdiv")));
    }

    @AfterEach
    void quitAll() {
        driver.quit();
    }

    //    негативные тест-кейсы
//    1. без заполненных полей
    @Test
    void shouldNotSendCVWithoutData() {
        CareerPageFactory careerPageFactory = PageFactory.initElements(driver, CareerPageFactory.class);
        careerPageFactory.clickOnPolicyBtn();
        careerPageFactory.closeChat();
        careerPageFactory.scrollPageToForm();
        careerPageFactory.clickOnAgreeBtn();
        careerPageFactory.clickOnSendCV();
    }

    //    2. неверное поле имени
    @Test
    void shouldNotSendWithWrongName() {
        CareerPageFactory careerPageFactory = PageFactory.initElements(driver, CareerPageFactory.class);
        careerPageFactory.clickOnPolicyBtn();
        careerPageFactory.closeChat();
        careerPageFactory.scrollPageToForm();
        careerPageFactory.clickOnAgreeBtn();
        careerPageFactory.scrollPageToForm();
        careerPageFactory.fillNameInput();
        careerPageFactory.clickOnSendCV();

        Duration dur = Duration.ofSeconds(6);
        WebDriverWait wait = new WebDriverWait(driver, dur);
        By err = By.xpath("//*[@class='FormErrorCV-module--title--9hx6v']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(err));
        WebElement errorMessage = driver.findElement(err);
        errorMessage.getText();
        String error = "Формат: Иванов Иван";
        assertEquals(error, errorMessage.getText());
    }

    // 3. выбор "уровнь специалиста" и "уровень англ" из выпадающих списков
    @Test
    void shouldChooseLevelSpecialist() {
        CareerPageFactory careerPageFactory = PageFactory.initElements(driver, CareerPageFactory.class);
        careerPageFactory.clickOnPolicyBtn();
        careerPageFactory.closeChat();
        careerPageFactory.scrollPageToForm();
        careerPageFactory.clickOnAgreeBtn();
        careerPageFactory.scrollPageToForm();
        careerPageFactory.chooseLevelSpecialist();
        careerPageFactory.chooseLevelEnglish();
    }
}