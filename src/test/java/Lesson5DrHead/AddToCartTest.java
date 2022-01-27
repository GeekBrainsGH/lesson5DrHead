package Lesson5DrHead;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Add to cart")
public class AddToCartTest extends BaseTest{

    @DisplayName("Add to cart: positive")
    @ParameterizedTest
    @ValueSource(strings = {"Sony DLC-HX10"})
    void addToCartTest(String productName) throws InterruptedException {

        webDriver.get("https://doctorhead.ru/");
        webDriver.manage().window().setSize(new Dimension(1500, 1000));

        // Пока вынесла авторизацию как предусловие
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")));
        webDriver.findElement(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")).click();
        webDriver.findElement(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")).click();

        By authLocator = By.xpath("//div[contains(@class, 'modal-wr is-open')]");
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(authLocator));
        WebElement authForm = webDriver.findElement(authLocator);

        authForm.findElement(By.name("USER_LOGIN")).sendKeys("test_202334@mail.ru");
        authForm.findElement(By.name("USER_PASSWORD")).sendKeys("123456789n");
        authForm.findElement(By.name("Login")).click();
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='header-menu__item_login-img lozad-loaded']")));

        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='top-search-line']")));
        webDriver.findElement(By.xpath("//input[@id='top-search-line']")).sendKeys("Sony");
        webDriver.findElement(By.xpath("//div[contains(@class, 'header-content')]//button[contains(@class, 'search-block__button')]")).click();

        webDriver.findElement(By.xpath("//div[contains(@class, 'catalog-list__item') and contains(., '" + productName + "')]"))
                .findElement(By.xpath(".//button[contains(@class, 'buy-button')]")).click();

        // Запасной вариант со стримами
        //List<WebElement> products = webDriver.findElements(By.xpath("//div[contains(@class, 'catalog-list__item')]"));
//        products.stream()
//                .filter(pr -> pr.getText().contains(productName))
//                .findFirst()
//                .orElseThrow(() -> new NoSuchElementException("Товар " + productName + " отсутствует на странице"))
//                .findElement(By.xpath(".//button[contains(@class, 'buy-button')]")).click();

        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='go_to_cart']")));
        webDriver.findElement(By.xpath("//div[@id='go_to_cart']//button[contains(@class, 'button button_primary')]")).click();

        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id = 'content']//table[@class = 'cart-table']")));

        List<WebElement> productsInCart = webDriver.findElements(By.xpath("//div[@id = 'content']//table[@class = 'cart-table']"));
        assertThat(productsInCart).hasSize(1);
        assertThat(productsInCart.get(0).findElement(By.xpath(".//tr[contains(@class, 'js-basket-elem')]//a[@class = 'link']")).getText())
                .as("Название продукта в корзине должно быть: " + productName)
                .isEqualTo(productName);

        webDriver.findElement(By.xpath("//tr[contains(@class, 'js-basket-elem')]//button[contains(@class, 'button-remove')]")).click();
        webDriver.findElement(By.xpath("//tr[contains(@class, 'js-basket-elem')]//button[contains(@class, 'button-remove')]")).click();
        webDriver.findElement(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")).click();
        webDriver.findElement(By.xpath("//div[contains(@class, 'lk-nav')]//a[@href='/personal/?logout=yes']")).click();


    }
}
