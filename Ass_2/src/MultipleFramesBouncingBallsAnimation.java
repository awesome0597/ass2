import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.Random;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.0
 * date: 14/4/21
 *
 * <p>
 **/
public class MultipleFramesBouncingBallsAnimation {

    static private Color getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return (new Color(r, g, b));
    }

    static private Ball createNewBall(int r, int min, int max) {
        java.util.Random rand = new java.util.Random();
        if (r * 2 >= max - min) {
            r = (max - min) / 2 - 1;
        }
        //when defining point, x and y are bound to 139 and then have radius plus one added so to avoid (0,0)-(30,30)
        return (new Ball(new Point(rand.nextInt((max - min) - 2 * r - 1) + r + min, rand.nextInt((max - min) - 2 * r - 1) + r + min), r, getRandomColor()));
    }

    static private Frame createNewFrame(double min, double max, Color color) {
        return (new Frame(new Point(min, min), new Point(min, max), new Point(max, max),
                new Point(max, min), color));
    }


    static private void drawAnimation(int[] str) {
        GUI gui = new GUI("title", 650, 650);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        java.util.Random rand = new java.util.Random();
        Frame one = createNewFrame(50, 500, Color.GRAY);
        Frame two = createNewFrame(450, 600, Color.YELLOW);
        Ball[] balls = new Ball[str.length];
        for (int i = 0; i < str.length; i++) {
            if (i < str.length / 2) {
                balls[i] = createNewBall(str[i], 50, 500);
            } else {
                balls[i] = createNewBall(str[i], 450, 600);
            }
            // add plus 1 to speed so it is not 0
            Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(10) + 1);
            balls[i].setVelocity(v);
        }


        while (true) {
            DrawSurface d = gui.getDrawSurface();
            one.drawOn(d);
            two.drawOn(d);
            for (int i = 0; i < str.length; i++) {
                balls[i].moveOneStep();
                if (i < (str.length / 2)) {
                    balls[i].changeVelocity(one);
                } else {
                    balls[i].changeVelocity(two);
                }
                balls[i].drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    public static void main(String[] args) {
        int[] rad = null;
        int j = 0;
        for (int i = 0; i < args.length; i++) {
            if (Integer.parseInt(args[i]) > 0) {
                rad = new int[j + 1];
                for (int k = 0; k < j; k++) {
                    rad[k] = Integer.parseInt(args[k]);
                }
                rad[i] = Integer.parseInt(args[j]);
                j++;
            } else {
                System.out.print("Invalid Radius");
                return;
            }
        }
        if (rad == null) {
            return;
        }
        drawAnimation(rad);
    }

}
