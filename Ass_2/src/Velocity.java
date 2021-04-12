/**
 * name: Adira Weiss.
 * id: 322094111
 * version 2.0.1
 * date: 14/4/21
 *
 * <p>
 **/
import java.lang.Math;

// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx;
    private double dy;


    // constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle - 90));
        double dy = speed * Math.sin(Math.toRadians(angle - 90));
        return new Velocity(dx, dy);
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {

        return (new Point(p.getX() + this.dx, p.getY() + this.dy));
    }


}
