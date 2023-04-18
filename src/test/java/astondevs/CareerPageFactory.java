package astondevs;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CareerPageFactory {
    WebDriver driver;

    public CareerPageFactory(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(className = "CookiesPolicy-module--button--JZrW1")
    WebElement policy;

    @FindBy(xpath = ".//h2[text()='Напишите нам']/..")
    WebElement scrollPage;

    @FindBy(className = "CheckboxCV-module--checkbox--PnnuN")
    WebElement agree;

    @FindBy(className = "RegistrationForm-module--sendButton--+gcvb")
    WebElement btnSend;

    public void clickOnPolicyBtn() {
        Duration dur = Duration.ofSeconds(6);
        WebDriverWait wait = new WebDriverWait(driver, dur);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("CookiesPolicy-module--button--JZrW1")));
        policy.click();
    }

    //    закрывает чат
    public void closeChat() {
        Duration dur = Duration.ofSeconds(6);
        WebDriverWait wait = new WebDriverWait(driver, dur);
        WebElement closeIt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("jdiv")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'display:none')", closeIt);
    }

    public void clickOnAgreeBtn() {
        agree.click();
    }

    //    скролл страницы вниз до формы отправки cv
    public void scrollPageToForm() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", scrollPage);
    }

    public void clickOnSendCV() {
        btnSend.click();
    }

    public void fillNameInput() {
        Duration dur = Duration.ofSeconds(6);
        WebDriverWait wait = new WebDriverWait(driver, dur);
        WebElement nameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("full_name")));
        nameInput.click();
        nameInput.sendKeys("Иванов");
    }

    public void chooseLevelSpecialist() {
        Duration dur = Duration.ofSeconds(3);
        WebDriverWait wait = new WebDriverWait(driver, dur);
        WebElement listSpec = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("level_specialist")));
        listSpec.click();
        WebElement levelSpec = driver.findElement(By.xpath("//*[@class='Select-module--optionButton--hqY6o'][1]"));
        levelSpec.click();
    }

    public void chooseLevelEnglish() {
        Duration dur = Duration.ofSeconds(3);
        WebDriverWait wait = new WebDriverWait(driver, dur);
        WebElement listEng = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("level_english")));
        listEng.click();
        WebElement levelEng = driver.findElement(By.xpath("//*[@class='Select-module--optionButton--hqY6o'][4]"));
        levelEng.click();
    }
}