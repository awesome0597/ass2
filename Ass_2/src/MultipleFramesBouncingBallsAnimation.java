import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.Random;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.0
 * date: 14/4/21
 *
 * <p>
 * class that creates image of a multiple bouncing balls in a set size window, divided between two frames with a set
 * size and color, starts through a main that gets args from the user that are used as radii. based on the amount of
 * arguments received is the amount of balls that will appear in the window. The first half of the balls created go to
 * the first frame and the second half go to the second frame.
 **/
public class MultipleFramesBouncingBallsAnimation {

    /**
     * function that generates random color, used when creating new Ball.
     *
     * @return type Color
     */
    private static Color getRandomColor() {
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
     * @param r     type int, the radius received from the cmd for a ball
     * @param frame type Frame, set the borders the ball can be created in
     * @return new Ball
     */
    private static Ball createNewBall(int r, Frame frame) {
        java.util.Random rand = new java.util.Random();
        if (r * 2 >= frame.getWidth() || r * 2 >= frame.getHeight()) {
            if (frame.getWidth() >= frame.getHeight()) {
                r = ((int) frame.getHeight()) / 2 - 1;
            } else {
                r = ((int) frame.getWidth()) / 2 - 1;
            }

        }
        return new Ball(new Point((rand.nextInt(((int) frame.getWidth()) - 2 * r - 1)
                + ((int) frame.getMinX()) + r), (rand.nextInt(((int) frame.getHeight()) - 2 * r - 1)
                + ((int) frame.getMinY()) + r)), r, getRandomColor());

//        return new Ball(new Point(rand.nextInt(((int)(frame.getWidth() - 2 * r - 1) + r + ((int)frame.getMinX()),
//                rand.nextInt((int)(frame.getHeight() - 2 * r - 1) + r + (int)frame.getMinY())))),
//                r, getRandomColor());
    }

    /**
     * square frame is created based on min and max value received.
     *
     * @param min   type Point, minimum value of point for frame
     * @param max   type Point, maximum value of point for frame
     * @param color type Color
     * @return new frame
     */
    private static Frame createNewFrame(Point min, Point max, Color color) {
        return (new Frame(min.getX(), min.getY(), max.getX(), max.getY(), color));
    }

    /**
     * A GUI is created in size 650*650 (no specific size was given), and a  sleeper is created to give the illusion of
     * a moving picture and a Random number generator is created. Two frames are created, one between the coordinates
     * (50,50) to (500,500) and the second between (450,450) (600,600) and are given the colors gray and yellow
     * respectively. A loop runs through all the radii in the array creating a new variable of type Ball using the
     * functioמ createNewBall, and giving them a corresponding velocity in relation to their size (the bigger the ball
     * the slower it goes, note the speed of balls bigger and including 50 have a set speed),
     * Then velocity is received in the form 'speed' and 'angle' and is converted to dx, dy using the fromSpeedAndAngle
     * function in Velocity, and it is assigned to ball. Create new ball also receives a frame indicating
     * what the limits for the center can be. The first half of the array is sent with the values from the first frame
     * and the second half with those from the second frame. We then enter the loop that draws the animation. First we
     * call changeVelocity so moveOneStep won't send the ball out of the frame (both methods reside in Ball class). We
     * then create our drawSurface, draw the Ball on it and have it shown to the user. Then there is a sleeper to give
     * the illusion of a smooth moving ball across the screen.
     *
     * @param str type int[], contains all the radii of potential new Balls
     */
    private static void drawAnimation(int[] str) {
        GUI gui = new GUI("title", 650, 650);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        java.util.Random rand = new java.util.Random();
        Frame one = createNewFrame(new Point(50, 50), new Point(500, 500), Color.GRAY);
        Frame two = createNewFrame(new Point(450, 450), new Point(600, 600), Color.YELLOW);
        Ball[] balls = new Ball[str.length];
        for (int i = 0; i < str.length; i++) {
            if (i < str.length / 2) {
                balls[i] = createNewBall(str[i], one);
            } else {
                balls[i] = createNewBall(str[i], two);
            }
            double speed = 51 - balls[i].getSize();
            if (balls[i].getSize() >= 50) {
                speed = 1;
            }
            Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360), speed);
            balls[i].setVelocity(v);
        }


        while (true) {
            DrawSurface d = gui.getDrawSurface();
            one.drawOn(d);
            two.drawOn(d);
            for (int i = 0; i < str.length; i++) {
                if (i < (str.length / 2)) {
                    balls[i].changeVelocity(one);
                } else {
                    balls[i].changeVelocity(two);
                }
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
        Random rand = new Random();
        int[] rad = null;
        for (int i = 0; i < args.length; i++) {
            rad = new int[i + 1];
            for (int k = 0; k < i; k++) {
                rad[k] = Integer.parseInt(args[k]);
            }
            if (Integer.parseInt(args[i]) >= 0) {
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
