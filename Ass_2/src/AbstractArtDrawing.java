import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.0
 * date: 7/4/21
 *
 * In this class we present an image that contains 10 lines, drawn in black. The middle point in each line
 * is indicated in blue, while the intersection points between the lines are indicated in red.
 * <p>
 * In this class we present an image that contains 10 lines, drawn in black. The middle point in each line
 * is indicated in blue, while the intersection points between the lines are indicated in red.
 **/

public class AbstractArtDrawing {
    /**
     * Called from drawAbstractArt, a random line is generated while the x and y points are bound by the width and
     * height of the surface (width 400, height 300), so they won't be drawn outside of window.
     * @return new Line
     */
    public Line generateRandomLine() {
        Random rand = new Random(); // create a random-number generator
        return (new Line(rand.nextInt(400), rand.nextInt(300),
                rand.nextInt(400), rand.nextInt(300)));

    }

    /**
     *A line and drawSurface are sent from drawAbstractArt. the color of the line is set to black and the line is drawn
     * on the GUI surface. Then a new variable (type Point) is created to hold the middle point of the line. That circle
     * is filled in blue and is also added to the surface.
     * @param l Line
     * @param d DrawSurface
     */
    void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
        d.setColor(Color.BLUE);
        Point p = l.middle();
        d.fillCircle((int) p.getX(), (int) p.getY(), 3);
    }

    /**
     * function that presents an abstract drawing of lines their center, and intersection point with one another.
     * A GUI is created (size is 400*300 per instructions). An array of type Line (size 10 as in instructions), is
     * created. Within a loop at every spot in the array a new line is created using the generateRandomLine function,
     * and it is then drawn on the drawSurface using the drawLine function. We then enter another loop that checks, the
     * potential intersection point of the current line with the previous lines, if it exists it is marked with a red
     * circle. After we exit the loop the GUI surface is presented.
     */
    public void drawAbstractArt() {

        GUI gui = new GUI("Abstract Art Drawing", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] arr = new Line[10];
        for (int i = 0; i < 10; ++i) {
            arr[i] = generateRandomLine();
            drawLine(arr[i], d);
            for (int j = 0; j < i; j++) {
                if (arr[i].isIntersecting(arr[j])) {
                    d.setColor(Color.RED);
                    Point p = arr[i].intersectionWith(arr[j]);
                    d.fillCircle((int) p.getX(), (int) p.getY(), 3);
                }

            }
        }
        gui.show(d);
    }

    /**
     * Purpose is to initialize drawAbstractArt.
     *
     * @param args array of strings. no arguments received.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawAbstractArt();
    }
}
