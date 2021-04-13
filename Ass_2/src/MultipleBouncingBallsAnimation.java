import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.Random;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 5.0.1
 * date: 13/4/21
 *
 * <p>
 * class that creates image of a multiple bouncing balls in a set size window, starts through a main that gets args
 * from the user that are used as radii. based on the amount of arguments received is the amount of balls that will
 * appear in the window.
 **/

public class MultipleBouncingBallsAnimation {

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
     * Function that creates a new ball to be drawn on the GUI surface. first there is a validity check to make sure the
     * ball fit inside the allotted frame. If radius is too big it is then reset to the maximum value it can be so that
     * it will fit in its frame and still have movement. For example, if i have a 200*200 frame and my given radius is
     * 100, the radius will be reset to '200 / 2 - 1 = 99'.
     *
     * @param r type int, the radius received from the cmd for a ball
     * @return new Ball
     */
    static private Ball createNewBall(int r) {
        java.util.Random rand = new java.util.Random();
        if (r * 2 >= 600) {
            r = 600 / 2 - 1;
        }
        return (new Ball(new
                Point(rand.nextInt(600 - 2 * r - 1) + r + 1, rand.nextInt(600 - 2 * r - 1) + r + 1),
                r, getRandomColor()));
    }

    /**
     * A GUI is created in size 600*600 (no specific size was given), and a  sleeper is created to give the illusion of
     * a moving picture and a Random number generator is created. A new frame is created based on the dimensions of the
     * GUI window ,and a ball is created using the radii received from the main in the array str.
     * A loop runs through all the radii in the array creating a new variable of type Ball using the function
     * createNewBall, and giving them a corresponding velocity in relation to their size (the bigger the ball the slower
     * it goes, note the speed of balls bigger and including 50 have a set speed),
     * Then velocity is converted from 'speed' and 'angle' to dx, dy using the fromSpeedAndAngle function in velocity,
     * and it is assigned to ball. We then enter the loop that draws the animation. First we call changeVelocity so
     * moveOneStep won't send the ball out of the frame (both methods reside in Ball class). We then create our
     * drawSurface draw the Ball on it and have it shown to the user. Then there is a sleeper to give the illusion of
     * a smooth moving ball across the screen.
     *
     * @param str type int[], contains all the radii of potential new Balls
     */
    static private void drawAnimation(int[] str) {
        GUI gui = new GUI("title", 600, 600);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        java.util.Random rand = new java.util.Random();
        Ball[] balls = new Ball[str.length];
        Frame one = new Frame(new Point(0, 0), new Point(0, 600), new Point(600, 600), new Point(600, 0), null);
        for (int i = 0; i < str.length; i++) {
            balls[i] = createNewBall(str[i]);
            double speed = 51 - balls[i].getSize();
            if (balls[i].getSize() >= 50) {
                speed = 1;
            }
            Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360), speed);
            balls[i].setVelocity(v);
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < balls.length; i++) {
                balls[i].changeVelocity(one);
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Receives args that are converted to an array of int[] and sent to the function drawAnimation. validity check
     * that args isn't null and that all radii are valid (>0), if not a random size is assigned to the radius instead
     * (per instructions given from question 261 in Piazza).
     *
     * @param args array of strings containing an unset amount of arguments that must be bigger then 0
     */
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("No Arguments Received");
            return;
        }
        Random rand = new Random();
        int[] rad = null;
        for (int i = 0; i < args.length; i++) {
            rad = new int[i + 1];
            for (int k = 0; k < i; k++) {
                rad[k] = Integer.parseInt(args[k]);
            }
            if (Integer.parseInt(args[i]) > 0) {
                rad[i] = Integer.parseInt(args[i]);
            } else {
                rad[i] = rand.nextInt(299) + 1;
            }
        }
        if (rad == null) {
            return;
        }
        drawAnimation(rad);
    }
}
