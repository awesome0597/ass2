/**
 * name: Adira Weiss.
 * id: 322094111
 * version 2.0.1
 * date: 14/4/21
 *
 * <p>
 **/

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.Random;


public class Ball {

    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;

    // constructor

    /**
     * @param center
     * @param r
     * @param color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    public void setVelocity(Velocity v) {
        this.v = v;


    }

    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));

    }


    public Velocity getVelocity() {

        return this.v;
    }


    public void moveOneStep() {

        this.center = this.getVelocity().applyToPoint(this.center);


    }

    public void changeVelocity(Frame one) {
        //if intersect with y borders (height)
        if (this.center.distance(new Point(this.center.getX(), one.getBleft().getY())) <= this.r
                || this.center.distance(new Point(this.center.getX(), one.getTleft().getY())) <= this.r) {
            setVelocity(this.v.getDx(), (-1) * this.v.getDy());
            this.color = getRandomColor();
        }
        //if intersect with x border (width)
        if (this.center.distance(new Point(one.getTleft().getX(), this.center.getY())) <= this.r
                || this.center.distance(new Point(one.getBright().getX(), this.center.getY())) <= this.r) {
            setVelocity((-1) * this.v.getDx(), this.v.getDy());
            this.color = getRandomColor();
        }

    }

    // accessors
    public int getX() {
        return (int) this.center.getX();
    }

    public int getY() {
        return (int) this.center.getY();
    }

    public int getSize() {
        return this.r;
    }

    public java.awt.Color getColor() {
        return this.color;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.getSize());
    }

    static private Color getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return (new Color(r, g, b));
    }

    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        java.util.Random rand = new java.util.Random();
        Ball ball = new Ball(new Point(start.getX(), start.getY()), 30, getRandomColor());
        // ball.setVelocity(dx, dy);
        Frame one = new Frame(new Point(0, 200), new Point(0, 0), new Point(200, 0), new Point(200, 200), null);
        Velocity v = Velocity.fromAngleAndSpeed(dx, dy);
        ball.setVelocity(v);
        while (true) {
            ball.moveOneStep();
            ball.changeVelocity(one);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    public static void main(String[] args) {
        java.util.Random rand = new java.util.Random();
        double dx = rand.nextInt(360);
        double dy = rand.nextInt(10) + 1;
        drawAnimation(new Point(rand.nextInt(160) + 30 + 1, rand.nextInt(160) + 30 + 1), dx, dy);
    }
}
