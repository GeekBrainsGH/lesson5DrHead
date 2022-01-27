package org.example;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Triangle {

    private double a;
    private double b;
    private double c;
    double sqrt;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean isValid() {
        double maxSide = Math.max(a, Math.max(b, c));
        return maxSide < (a + b + c - maxSide);
    }


    public boolean hasPositiveSides() {
        return a > 0 && b > 0 && c > 0;
    }

    public String isValidFormula() {
        double sqrt1 = Math.sqrt(((a + b + c) / 2 * ((a + b + c) / 2 - a) * ((a + b + c) / 2 - b) * ((a + b + c) / 2 - c)));
        String formulaStr = Double.toString(sqrt1);
        return formulaStr;
    }


    public double countTriangleArea() throws Exception {


        if (!hasPositiveSides()) {
            throw new IllegalArgumentException("The sides must be positive and greater 0");
        }

        if (!isValid()) {
            throw new IllegalArgumentException("Max side must be less then sum of the other sides");
        }

        sqrt = Math.sqrt(((a + b + c) / 2) * ((a + b + c) / 2 - a) * ((a + b + c) / 2 - b) * ((a + b + c) / 2 - c));

        if (!isValidFormula().equals(Double.toString(sqrt))) {
            throw new Exception("Invalid formula");
        }

        return sqrt;
    }


    public static void main(String[] args) throws Exception {
Triangle triangle = new Triangle(66633388, 66633380, 66633390);

        System.out.println(triangle.countTriangleArea());
    }

    /////////////


    public Triangle createSimilarTriangle(double coefficient) {
        if (!hasPositiveSides()) {
            throw new IllegalArgumentException("The sides must be positive");
        }

        if (!isValid()) {
            throw new IllegalArgumentException("The triangle must be valid");
        }
        if (coefficient <= 0) {
            throw new IllegalArgumentException("The coefficient must be positive");
        }

        return new Triangle(a * coefficient, b * coefficient, c * coefficient);
    }


    ////////////////////


    public List<Triangle> createSimilarTriangle(double sinceCoefficient, double toCoefficient) {
        if (!hasPositiveSides()) {
            throw new IllegalArgumentException("The sides must be positive");
        }

        if (!isValid()) {
            throw new IllegalArgumentException("The triangle must be valid");
        }
        if (sinceCoefficient <= 0 || toCoefficient <= 0) {
            throw new IllegalArgumentException("The coefficient must be positive");
        }

        if (sinceCoefficient > toCoefficient) {
            throw new IllegalArgumentException("The end Coefficient must be greater then start Coefficient");
        }

        List<Triangle> triangles = new ArrayList<>();

        for (double i = sinceCoefficient; i < toCoefficient + 1; i++) {
            triangles.add(new Triangle(a * i, b * i, c * i));
            return triangles;
        }
return triangles;
    }
}



