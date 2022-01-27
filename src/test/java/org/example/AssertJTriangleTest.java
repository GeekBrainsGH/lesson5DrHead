package org.example;


import org.example.providers.InvalidTriangleProvider;
import org.example.providers.NotPositiveSidesProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Треугольник")
public class
AssertJTriangleTest {

    public static Stream<Arguments> triangles() {
        return Stream.of(Arguments.of(new Triangle(3,4,6), 5.332682251925386),
                Arguments.of(new Triangle(3,3,3), 3.897114317029974),
                Arguments.of(new Triangle(3,4,5), 6.0),
                Arguments.of(new Triangle(3,3,1), 1.479019945774904)
        );
    }

    // Проверка тупоугольного, прямоугольного, равностороннего и равнобедренного треугольников

    @ParameterizedTest(name = "Площадь треугольника {0} равен {1}")
    @MethodSource("triangles")
    public void countTriangleTest(Triangle triangle, double expectedArea) throws Exception {
        double area = triangle.countTriangleArea();
        assertThat(area).as("Сравниваем актуальную площадь с ожиданиемой").isEqualTo(expectedArea);
        //assertEquals(expectedArea, area);
    }

    @Test
    @DisplayName("Проверка работы со множеством чисел после запятой")
    public void countAreaWithDoubleTest() throws Exception {
        Triangle triangle = new Triangle(3.33633347, 4.346223, 5.563346);
        double area = triangle.countTriangleArea();
        assertThat(area).as("Сравниваем актуальную площадь с ожиданиемым 7.2464954233213525").isEqualTo(7.2464954233213525);
    }

    @Test
    @DisplayName("Проверка работы с большми значенияи")
    public void countAreaLongNumbersTest() throws Exception {
        Triangle triangle = new Triangle(66633388, 66633380, 66633390);
        double area = triangle.countTriangleArea();
        assertThat(area).as("Сравниваем актуальную площадь с ожиданиемым 1.922579916718917E15").isEqualTo(1.922579916718917E15);
    }

    @Test
    @DisplayName("Проверка работы со значениями, приближенными к 0")
    public void countAreaSmallNumbersTest() throws Exception {
        Triangle triangle = new Triangle(0.00012, 0.00013, 0.00015);
        double area = triangle.countTriangleArea();
        assertThat(area).as("Сравниваем актуальную площадь с ожиданиемым 7.483314773547882E-9").isEqualTo(7.483314773547882E-9);
    }

    // ВОПРОС: Если результаты вычислений равны таким числам как выше (7.483314773547882E-9 и 1.922579916718917E15)
    // это считать за коректную работу или есть смысл прописать Exception для недопустимых значений (указать максимум и минимум)?

    // Проверка на валидность треугольника (максильная сторона не превышает сумму двух других сторон и не равна ей)

    @ParameterizedTest
    @ArgumentsSource(InvalidTriangleProvider.class)
    public void areaInvalidTriangleNegativeTest (Triangle triangle){
        assertThatThrownBy(triangle::countTriangleArea)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Max side must be less then sum of the other sides");

//        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countTriangleArea);
//        assertEquals("The triangle must be valid", illegalArgumentException.getMessage());
    }

    // Проверка на положительность всех сторон

    @ParameterizedTest
    @ArgumentsSource(NotPositiveSidesProvider.class)
    public void notPositiveTriangleSidesTest (Triangle triangle) {
        assertThatThrownBy(triangle::countTriangleArea)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The sides must be positive and greater 0");
    }

    // Проверка работы с CsvSource

    @ParameterizedTest
    @CsvSource({ "3, 3, 3" })
    void countWithCsvSourceTest(double a, double b, double c) throws Exception {
        Triangle triangle = new Triangle(a, b, c);
        double area = triangle.countTriangleArea();
        assertThat(area).isEqualTo(3.897114317029974);
    }


    // Пыталась также написать тест для проверки правильности написания формулы,
    // но очень долгие попытки к работающему решению не привели :(

    // Так же зашла в тупик с негативным тестом на проверку формата переменных.
    // Например если через CSV передать string начение
    // Но с другими форматами код и так не запустится. Есть ли смысл в таком случае делать негативные тесты?


//    @ParameterizedTest
//    @CsvSource({ "3, 3, m" })
//    void countWithCsvSourceNegativeTest(double a, double b, String c) throws Exception {
//        Triangle triangle = new Triangle(a, b, c);
//        assertThatThrownBy(triangle::countTriangleArea)
//                .isInstanceOf(Exception.class)
//                .hasMessage("");
//    }
    }







    //////////////////////////////////////////////// Тренировочное



//    @Test
//    //@Disabled("Перенесены в параметризированный тест")
//    //@RepeatedTest(5)   // (личная пометка) при использовании этого метода убрать @Test
//    @DisplayName("Площадь треугольника: прямоугольный")
//    public void countAreaTriangleEgyptTest() {
//        Triangle triangle = new Triangle(3,4,5);  //Arrange
//        double area = triangle.countTriangleArea();       //Act
//        assertThat(area).as("Сравниваем актуальную площадь с ожиданиемым 6.0").isEqualTo(6.0);
//        //assertEquals(6.0, area);                 //Assert
//    }
//
//    @Test
//   // @Disabled("Перенесены в параметризированный тест")
//    @DisplayName("Площадь треугольника: расносторонний")
//    public void countAreaWithEqualsSidesTest() {
//        Triangle triangle = new Triangle(3, 3, 3);
//        double area = triangle.countTriangleArea();
//        assertThat(area).as("Сравниваем актуальную площадь с ожиданиемым 3.897114317029974").isEqualTo(3.897114317029974);
//    }
//
//    @Test
//   // @Disabled("Перенесены в параметризированный тест")
//    @DisplayName("Площадь треугольника: тупоугольный")
//    public void countAreaGreaterThan90Test() {
//        Triangle triangle = new Triangle(3, 4, 6);
//        double area = triangle.countTriangleArea();
//        assertThat(area).as("Сравниваем актуальную площадь с ожиданиемым 5.332682251925386").isEqualTo(5.332682251925386);
//
//    }

