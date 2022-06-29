package mx.tc.j2se.evaluation;

public class Circle {
    private int radius;

    public Circle() {
        this.radius = 1;
    }

    public Circle(int radius) throws IllegalArgumentException {
        if (radius <= 0) {
            throw new IllegalArgumentException("The radius is invalid");
        } else {
            this.radius = radius;
        }
    }

    public void setRadius(int radius) throws IllegalArgumentException {
        if (radius <= 0) {
            throw new IllegalArgumentException("The radius is invalid");
        } else {
            this.radius = radius;
        }
    }

    public int getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
