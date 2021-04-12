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

public class MultipleBouncingBallsAnimation {

    static private Color getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return (new Color(r, g, b));
    }

    static private Ball createNewBall(int r) {
        java.util.Random rand = new java.util.Random();
        //when defining point, x and y are bound to 139 and then have radius plus one added so to avoid (0,0)-(30,30)
        return (new Ball(new Point(rand.nextInt(139) + r + 1, rand.nextInt(139) + r + 1), r, getRandomColor()));
    }

    static private void drawAnimation(int[] str) {
        GUI gui = new GUI("title", 600, 600);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        java.util.Random rand = new java.util.Random();
        Ball[] balls = new Ball[str.length];
        Frame one = new Frame(new Point(0, 600), new Point(0, 0), new Point(600, 0), new Point(600, 600), null);
        for (int i = 0; i < str.length; i++) {
            balls[i] = createNewBall(str[i]);
            // add plus 1 to speed so it is not 0
            Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(10) + 1);
            balls[i].setVelocity(v);
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < str.length; i++) {
                balls[i].moveOneStep();
                balls[i].changeVelocity(one);
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    public static void main(String[] args) {
        int[] str = null;
        int j = 0;
        for (int i = 0; i < args.length; i++) {
           if (Integer.parseInt(args[i]) > 0){
               str = new int[j + 1];
               for (int k = 0; k < j; k++) {
                   str[k] = Integer.parseInt(args[k]);
               }
               str[i] = Integer.parseInt(args[j]);
               j++;
           } else {
               System.out.print("Invalid Radius");
               return;
           }
        }
        if (str == null) {
            return;
        }
        drawAnimation(str);
    }
}
