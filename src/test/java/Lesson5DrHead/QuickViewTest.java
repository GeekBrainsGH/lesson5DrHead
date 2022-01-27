package Lesson5DrHead;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Quick view")
public class QuickViewTest extends BaseTest{

    @Test
    @DisplayName("Quick view: positive")
    void quickViewTest() throws InterruptedException {
        webDriver.get("https://doctorhead.ru/catalog/personal-audio/naushniki/besprovodnye-bluetooth/");
        webDriver.manage().window().setSize(new Dimension(1500, 1000));
        webDriver.findElement(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")).click();

        new Actions(webDriver)
                .moveToElement(webDriver.findElements(By.xpath("//div[contains(@class, 'check-show')]")).get(0))
                .click(webDriver.findElement(By.xpath("//button[contains(@class, 'product-quick')]")))
                .build()
                .perform();

        assertThat(webDriver.findElement(By.xpath("//button[contains(@class, 'quick-view-preview__button')]")).getText().toLowerCase())
                .isEqualTo("подробнее о товаре");
    }
}
