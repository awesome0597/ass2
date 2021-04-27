//322094111

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 27/4/21
 * Class that creates a game.
 * <p>
 * A class to set or calculate and then set the Velocity of a Ball (or potentially and moving object). receives
 * variables of type Double dx and dy. Both can be accessed. A velocity represented as an angle and speed can be
 * converted to dx and dy, and the velocity can be implemented to a point using the method applyToPoint to make a ball.
 * or potentially any point.
 **/


public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx type double, Delta x
     * @param dy type double, Delta y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * accessor.
     *
     * @return type double dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * accessor.
     *
     * @return type double dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This function receives two variables, angle and speed, and converts them to delta x and delta y.
     *
     * @param angle type double, the angle of the vector representing the velocity
     * @param speed type double, the length of the vector representing the velocity
     * @return type Velocity, returns the conversion of angle and speed to dx, dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(angle * Math.PI / 180);
        double dy = -speed * Math.cos(angle * Math.PI / 180);
        return new Velocity(dx, dy);
    }


    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy) in order to make the ball move
     * forward.
     *
     * @param p type Point
     * @return type Point, the new center
     */
    public Point applyToPoint(Point p) {
        return (new Point(p.getX() + this.dx, p.getY() + this.dy));
    }

}
