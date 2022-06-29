package mx.tc.j2se.evaluation;

public class Evaluation1 {
    /**
     * Static method to know the index of the largest circle in a Circle array.
     * @param arrayCircle It's an array which contains Circle objects.
     * @return the index of the largest circle.
     */
    public static int biggestCircle(Circle[] arrayCircle) {
        int index = -1, r = -1;
        for (int i = 0; i < arrayCircle.length; i++) {
            if (arrayCircle[i].getRadius() > r) {
                index = i;
                r = arrayCircle[i].getRadius();
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println("Evaluation 1");
        System.out.println("============");
        try {
            Circle circle1 = new Circle(0);
        } catch (IllegalArgumentException err) {
            System.out.println("The radius cannot be negative or zero");
        }

        Circle[] arrayCircle = new Circle[3];
        arrayCircle[0] = new Circle();
        arrayCircle[1] = new Circle(7);
        arrayCircle[2] = new Circle(4);

        int indexOfLargeCircle = biggestCircle(arrayCircle);
        System.out.println("The radius of the largest circle is: " + arrayCircle[indexOfLargeCircle].getRadius());
    }
}
