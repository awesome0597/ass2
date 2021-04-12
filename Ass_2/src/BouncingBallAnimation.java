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

public class BouncingBallAnimation {

    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball(new Point(start.getX(), start.getY()) , 30, Color.MAGENTA);
        Velocity v = Velocity.fromAngleAndSpeed(dx,dy);
        ball.setVelocity(v);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Invalid Input");
            return;
        }
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        Point p = new Point(a,b);
        //if intersect with y borders (height)
        if (p.distance(new Point(p.getX(),0)) <= 30
                || p.distance(new Point(p.getX(),200)) <= 30){
            System.out.println("Invalid Input");
            return;
        }
        //if intersect with x border (width)
        if (p.distance(new Point(0, p.getY())) <= 30
                || p.distance(new Point(200, p.getY())) <= 30){
            System.out.println("Invalid Input");
            return;
        }
        double c = Integer.parseInt(args[2]);
        double d = Integer.parseInt(args[3]);
        drawAnimation(p,c,d);




    }
}
