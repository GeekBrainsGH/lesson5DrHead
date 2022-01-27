package Lesson5DrHead;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Authorization DrHead")
public class AuthDrHeadTest extends BaseTest{

    @Test
    @DisplayName("Authorization DrHead: positive")
    void successfulAuthTest() {
        webDriver.get("https://doctorhead.ru/");
        webDriver.manage().window().setSize(new Dimension(1500, 1000));
        webDriver.findElement(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")).click();

        webDriver.findElement(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")).click();

        By authLocator = By.xpath("//div[contains(@class, 'modal-wr is-open')]");
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(authLocator));
        WebElement authForm = webDriver.findElement(authLocator);

        authForm.findElement(By.name("USER_LOGIN")).sendKeys("test_202334@mail.ru");
        authForm.findElement(By.name("USER_PASSWORD")).sendKeys("123456789n");
        authForm.findElement(By.name("Login")).click();

        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='header-menu__item_login-img lozad-loaded']")));

        webDriver.findElement(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")).click();

        List<WebElement> lkElements = webDriver.findElements(By.xpath("//div[@class = 'lk-content']"));
        assertThat(lkElements.get(0).findElement(By.xpath(".//div[@class = 'lk-section__title']")).getText())
                .isEqualTo("Личные данные");


        //Постусловие: выход из личного кабинета пользователя
        webDriver.findElement(By.xpath("//div[contains(@class, 'lk-nav')]//a[@href='/personal/?logout=yes']")).click();
        new WebDriverWait(webDriver,15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")));



    }

    @Test
    @DisplayName("Authorization DrHead: negative - Invalid password")
    void negativeAuthInvalidPasswordTest() throws InterruptedException {

        webDriver.get("https://doctorhead.ru/");

        webDriver.manage().window().setSize(new Dimension(1500, 1000));

        webDriver.findElement(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")).click();

        webDriver.findElement(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")).click();

        By authLocator = By.xpath("//div[contains(@class, 'modal-wr is-open')]");
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(authLocator));
        WebElement authForm = webDriver.findElement(authLocator);

        authForm.findElement(By.name("USER_LOGIN")).sendKeys("test_202334@mail.ru");
        authForm.findElement(By.name("USER_PASSWORD")).sendKeys("invalidpassword1");
        authForm.findElement(By.name("Login")).click();

        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text() = 'Неверный логин или пароль.']")));

        assertThat(webDriver.findElement(By.xpath("//div[contains(text(), 'Неверный логин')]")).getText())
                .isEqualTo("Неверный логин или пароль.");
    }

    }

