/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.0
 * date: 7/4/21
 *
 * <p>
 **/

import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class AbstractArtDrawing {

    Line generateRandomLine() {
        Random rand = new Random(); // create a random-number generator
        double x1 = rand.nextInt(400);
        double x2 = rand.nextInt(400);
        double y1 = rand.nextInt(300);
        double y2 = rand.nextInt(300);
        return (new Line(x1, y1, x2, y2));

    }

    void drawLine(Line l, DrawSurface d) {
        int x1 = (int) l.start().getX();
        int x2 = (int) l.start().getY();
        int y1 = (int) l.end().getX();
        int y2 = (int) l.end().getY();
        d.setColor(Color.BLACK);
        d.drawLine(x1, x2, y1, y2);
        d.setColor(Color.BLUE);
        Point p = l.middle();
        int x = (int)p.getX();
        int y = (int)p.getY();
        d.fillCircle(x,y,3);
    }

    public void drawAbstractArt() {

        GUI gui = new GUI("Abstract Art Drawing", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Random rand = new Random(); // create a random-number generator
        Line[] arr = new Line[10];
        for (int i = 0; i < 10; ++i) {
            arr[i] = generateRandomLine();
            drawLine(arr[i], d);
            for (int j = 0; j < i; j++) {
                if (arr[i].isIntersecting(arr[j])){
                    d.setColor(Color.RED);
                    Point p = arr[i].intersectionWith(arr[j]);
                    int x = (int)p.getX();
                    int y = (int)p.getY();
                    d.fillCircle(x,y,3);
                }

            }
        }
        gui.show(d);
    }

    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawAbstractArt();
    }
}
