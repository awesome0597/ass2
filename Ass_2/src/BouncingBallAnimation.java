import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.Random;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 4.0.0
 * date: 13/4/21
 *
 * <p>
 * class that creates image of a bouncing ball in a set size window.
 */

public class BouncingBallAnimation {

    /**
     * function that generates random color, used when creating new Ball.
     *
     * @return type Color
     */
    static private Color getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return (new Color(r, g, b));
    }

    /**
     * A GUI is created in size 200*200 (per instructions), and a  sleeper is created to give the illusion of a moving
     * picture. A new frame is created based on the dimensions of the GUI window ,and a ball is created using the Point
     * received from the main, a radius of 30 (per instructions), and a random color using the getRandomColor function.
     * Then velocity is converted from 'speed' and 'angle' to dx, dy using the fromSpeedAndAngle function in velocity,
     * and it is assigned to ball. We then enter the loop that draws the animation. First we call changeVelocity so
     * moveOneStep won't send the ball out of the frame (both methods reside in Ball class). We then create our
     * drawSurface draw the Ball on it and have it shown to the user. Then there is a sleeper to give the illusion of
     * a smooth moving ball across the screen.
     *
     * @param start type Point, used as center of Ball
     * @param dx    type double, used as angle
     * @param dy    type double, used as speed
     */

    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Frame one = new Frame(new Point(0, 0), new Point(0, 200),
                new Point(200, 200), new Point(200, 0), null);
        Ball ball = new Ball(new Point(start.getX(), start.getY()), 30, getRandomColor());
        Velocity v = Velocity.fromAngleAndSpeed(dx, dy);
        ball.setVelocity(v);
        while (true) {
            ball.changeVelocity(one);
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Purpose is to receive 4 integers as arguments and then send them to drawAbstractArt as a point and two variables.
     * There are validity checks to make sure proper variables were given for a center point (that they reside within
     * the frame), and that a proper value was given for speed (speed > 0).
     * @param args array of strings. meant to receive four positive integers.
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Invalid Input - incorrect number of args");
            return;
        }
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        Point p = new Point(a, b);
        //if intersect with y borders (height)
        if (p.distance(new Point(p.getX(), 0)) <= 30
                || p.distance(new Point(p.getX(), 200)) <= 30) {
            System.out.println("Invalid Input");
            return;
        }
        double c = Integer.parseInt(args[2]);
        double d = Integer.parseInt(args[3]);
        if (Integer.parseInt(args[3]) <= 0) {
            java.util.Random rand = new java.util.Random();
            d = rand.nextInt(10) + 1;
        }
        drawAnimation(p, c, d);


    }
}
