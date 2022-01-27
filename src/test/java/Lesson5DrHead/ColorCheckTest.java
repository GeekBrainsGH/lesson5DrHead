package Lesson5DrHead;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Color switch")
public class ColorCheckTest extends BaseTest {

    @Test
    @DisplayName("Color switch: positive")
    void colorSwitchPositiveTest() throws InterruptedException {

        // Допустимо ли сразу переходить на станицу проверяемого товара или лучше прописывать шаги в тесте?
        webDriver.get("https://doctorhead.ru/product/anker_soundcore_liberty_3_pro_nova_white/");

        webDriver.manage().window().setSize(new Dimension(1500, 1000));
        webDriver.findElement(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")).click();

//        List<WebElement> colors = webDriver.findElements(By.xpath("//div[@class = 'quick-view-color']"));
        // colors.get(1).findElement(By.xpath(".//div[@class = 'quick-view-color__item']")).click();
//        colors.get(1).findElement(By.xpath(".//input[@name = 'color']")).click();

        // Сначала хотела сделать через лист, но по какой то причине я не могу найти элементы, так как длина массива меньше 4.
        //Index 1 out of bounds for length 1

        webDriver.findElement(By.xpath("//div[@class = 'quick-view-color__item'][2]")).click();
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(), 'Grey')]")));

        assertThat(webDriver.findElement(By.xpath("//h1[@class = 'quick-view-title']")).getText())
                .isEqualTo("Anker Soundcore Liberty 3 Pro Star Grey");


    }
}
