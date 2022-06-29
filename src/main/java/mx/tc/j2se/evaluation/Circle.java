package mx.tc.j2se.evaluation;

public class Circle {
    /**
     * It's literally the radius of the circle.
     */
    private int radius;

    /**
     * Constructs a circle with radius 1.
     */
    public Circle() {
        this.radius = 1;
    }

    /**
     * Constructs a circle with the given radius
     * @param radius It's the radius of the circle.
     * @throws IllegalArgumentException The radius of the circle cannot be negative or zero.
     */
    public Circle(int radius) throws IllegalArgumentException {
        if (radius <= 0) {
            throw new IllegalArgumentException("The radius is invalid");
        } else {
            this.radius = radius;
        }
    }

    /**
     * Method to modify the radius of a circle.
     * @param radius It's the new radius of the circle.
     * @throws IllegalArgumentException The radius of the circle cannot be negative or zero.
     */
    public void setRadius(int radius) throws IllegalArgumentException {
        if (radius <= 0) {
            throw new IllegalArgumentException("The radius is invalid");
        } else {
            this.radius = radius;
        }
    }

    /**
     * Method to shows the radius of a circle.
     * @return the radius of the circle.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Method to shows the area of a circle.
     * @return the area of the circle.
     */
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
