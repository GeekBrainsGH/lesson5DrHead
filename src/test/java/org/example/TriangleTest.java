//package org.example;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assumptions.assumeFalse;
//import static org.junit.jupiter.api.Assumptions.assumeTrue;
//
//
//import org.example.providers.InvalidTriangleProvider;
//import org.example.providers.NotPositiveSidesProvider;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.MethodSource;
//
//import java.util.stream.Stream;
//
//
//@DisplayName("Треугольник")
//public class TriangleTest {
//
//    public static Stream<Arguments> triangles() {
//        return Stream.of(Arguments.of(new Triangle(3,4,6), 5.332682251925386),
//                Arguments.of(new Triangle(3,3,3), 3.897114317029974),
//                Arguments.of(new Triangle(3,4,5), 6.0),
//                Arguments.of(new Triangle(3,3,1), 1.479019945774904)
//        );
//    }
//
//    @ParameterizedTest(name = "Площадь треугольника {0} равен {1}")
//    @MethodSource("triangles")
//    public void countTriangleTest(Triangle triangle, double expectedArea) throws Exception {
//        double area = triangle.countTriangleArea();
//        assertEquals(expectedArea, area);
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(InvalidTriangleProvider.class)
//    public void areaInvalidTriangleNegativeTest (Triangle triangle){
//        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countTriangleArea);
//        assertEquals("The triangle must be valid", illegalArgumentException.getMessage());
//    }
//
//    @ParameterizedTest
//    @ArgumentsSource(NotPositiveSidesProvider.class)
//    public void notPositiveTriangleSidesTest (Triangle triangle) {
//        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countTriangleArea);
//        assertEquals("The sides must be positive", illegalArgumentException.getMessage());
//    }
//
//
//
//////////////////////////////////////////////////
//
//
//
////    @Test
////    @Disabled("Перенесены в параметризированный тест")
////    //@RepeatedTest(5)   // (личная пометка) при использовании этого метода убрать @Test
////    @DisplayName("Площадь треугольника: прямоугольный")
////    public void countAreaTriangleEgyptTest() {
////        Triangle triangle = new Triangle(3,4,5);  //Arrange
////        double area = triangle.countTriangleArea();       //Act
////        assertEquals(6.0, area);                 //Assert
////    }
////
////    @Test
////    @Disabled("Перенесены в параметризированный тест")
////    @DisplayName("Площадь треугольника: расносторонний")
////    public void countAreaWithEqualsSidesTest() {
////        Triangle triangle = new Triangle(3, 3, 3);
////        double area = triangle.countTriangleArea();
////        assertEquals(3.897114317029974, area);
////    }
////    @Test
////    @Disabled("Перенесены в параметризированный тест")
////    @DisplayName("Площадь треугольника: тупоугольный")
////    public void countAreaGreaterThan90Test() {
////        Triangle triangle = new Triangle(3, 4, 6);
////        double area = triangle.countTriangleArea();
////        assertEquals(5.332682251925386, area);
////
////    }
//
//
//
//
//}
