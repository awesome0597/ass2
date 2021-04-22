/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.1
 * date: 13/4/21
 *
 * <p>
 * Class that creates a point made up of two double variables (x,y). both values can be accessed. there is a method to
 * calculate the distance between a point and another point, and a method to check if they are equal.
 **/

public class Point {

    private double x;
    private double y;

    /**
     * constructor.
     *
     * @param x type double
     * @param y type double
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Return the x and y values of this point

    /**
     * accessor.
     *
     * @return the X value of Point
     */
    public double getX() {
        return this.x;
    }

    /**
     * accessor.
     *
     * @return the Y value of Point
     */
    public double getY() {
        return this.y;
    }
    /**
     * Method that calculate this distance between point and 'other'.
     *
     * @param other type Point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = other.getX();
        double y2 = other.getY();
        double dis = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
        return Math.sqrt(dis);
    }

    // equals -- return true is the points are equal, false otherwise

    /**
     * Method that compares two point to see if they are identical.
     *
     * @param other type Point
     * @return true if identical and false otherwise
     */
    public boolean equals(Point other) {
        return other != null && (this.getX() == other.getX() && this.getY() == other.getY());
    }

}
