package Lesson5DrHead;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Sort products by price")
public class ProductsSortingPriceTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"Sennheiser Orpheus HE-1"})
    @DisplayName("Sort products by descending price: positive")
    void ProductsSortingPriceDownTest(String productName) throws InterruptedException {

        webDriver.get("https://doctorhead.ru/");
        webDriver.manage().window().setSize(new Dimension(1500, 1000));

        webDriver.findElement(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")).click();

        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//div[contains(@class, 'header-nav')]//a[@href='/catalog/personal-audio/']")))
                .build()
                .perform();

        new WebDriverWait(webDriver,15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'header-catalog-section')]//div[text()='Полноразмерные наушники']")));

        webDriver.findElement(By.xpath("//div[contains(@class, 'header-catalog-section')]//div[text()='Полноразмерные наушники']")).click();
        webDriver.findElement(By.xpath("//div[@class='drop-down-headline']")).click();
        webDriver.findElement(By.xpath("//span[contains(@onclick, 'price&order=desc')]")).click();

        List<WebElement> products = webDriver.findElements(By.xpath("//div[@id='catalog-list']"));
        assertThat(products.get(0).findElement(By.xpath("//a[contains(@class, 'product-title')]")).getText())
                .as("Название продукта к наивысшей стоимостью должно быть: " + productName)
                .isEqualTo(productName);
    }
}
