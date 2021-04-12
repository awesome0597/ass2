/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.1
 * date: 4/4/21
 *
 * <p>
 **/

public class Point {
    // constructor
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = other.getX();
        double y2 = other.getY();
        double buf = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
        double dis = Math.sqrt(buf);
        return dis;
    }

    // equals -- return true is the points are equal, false otherwise
    public boolean equals(Point other) {
        return other != null && (this.getX() == other.getX() && this.getY() == other.getY()) ;
    }

    // Return the x and y values of this point
    public double getX() {
       return this.x;

    }

    public double getY() {
        return this.y;
    }
}
