import biuoop.DrawSurface;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 2.0.1
 * date: 13/4/21
 *
 * <p>
 * This class is called to create a ball. The components that make up a ball are a center point (center type Point),
 * a radius (type int), a color (type Color), and a velocity (type Velocity). Velocity can be set through a dx,dy or,
 * through a set variable of type Velocity. You can access the x and y values of the center, the size of the ball
 * (the radius), color and velocity. A ball can be moved one step based on its velocity, and the velocity can be
 * changed in case ball reaches out of bounds of the window its in. The ball can also be drawn on a drawSurface.
 **/

public class Ball {

    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;

    /**
     * constructor.
     *
     * @param center type Point
     * @param r      type Double
     * @param color  type Color
     */

    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constructor.
     *
     * @param v type Velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * constructor.
     *
     * @param dx type Double
     * @param dy type Double
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);

    }

    /**
     * accessor.
     *
     * @return The x value of the Center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * accessor.
     *
     * @return The y value of the Center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * accessor.
     *
     * @return The radius (r) of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * accessor.
     *
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * accessor.
     *
     * @return The Velocity (v) of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Called from drawAnimation. This function moves the ball forward one step according to its given velocity.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Called from drawAnimation. This function checks if the velocity of the ball would need to change before
     * moveOneStep. A temporary ball, tmp, is created and moveOneStep is then applied to its center. We then have two if
     * statements, one checking the y axis and one checking the x axis. We check that after moving the center if the
     * edge of the ball adjacent to that axis does not leave the frame. For example, to check if we intersect with the
     * y axis we see if the value of "x - r <= min", or if "x + r >= max". If the statement is true we times the value
     * of dy * (-1) (and similarly for the x axis).
     *
     * @param one type Frame, sent so we have the borders of the window.
     */
    public void changeVelocity(Frame one) {
        Ball tmp = this;
        tmp.moveOneStep();
        //check for x axis
        if (tmp.getY() - tmp.getSize() <= one.getMin().getY()
                || tmp.getY() + tmp.getSize() >= one.getMax().getY()) {
            this.setVelocity(this.v.getDx(), (-1) * this.v.getDy());
        }
        //check for y axis
        if (tmp.getX() - tmp.getSize() <= one.getMin().getX()
                || tmp.getX() + tmp.getSize() >= one.getMax().getX()) {
            this.setVelocity((-1) * this.v.getDx(), this.v.getDy());
        }

    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface type DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.getSize());
    }
}

