/**
 * name: Adira Weiss.
 * id: 322094111
 * version 2.0.1
 * date: 14/4/21
 *
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
     * the reason the cosine corresponds to dx and sinus to dy is because the java GUI mirrors the first quarter of the
     * Cartesian coordinate system. To make up for that, and to have window operate as the first quarter of the
     * coordinate system we flip both sin and cos and subtract 90 from the angle.
     *
     * @param angle type double, the angle of the vector representing the velocity
     * @param speed type double, the length of the vector representing the velocity
     * @return type Velocity, returns the conversion of angle and speed to dx, dy
     */
    public Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = (speed * Math.cos(Math.toRadians(angle - 90)));
        double dy = (speed * Math.sin(Math.toRadians(angle - 90)));
        return new Velocity(dx, dy);
    }

    public Velocity fromDxDy() {
        double speed = new Point(this.dx, this.dy).distance(new Point(2 * this.dx, 2 * this.dy));
        double angle = Math.toDegrees(Math.acos(Math.toRadians(this.dx / speed))) - 90;
        return new Velocity(angle, speed);
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
