import biuoop.DrawSurface;
import java.awt.Color;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 2.0.1
 * date: 13/4/21
 *
 * <p>
 * a Class that creates the frame that can limit the area of a window that a ball can move around in. A frame is made up
 * of four Point the bottom left (min,min), top left(min,max), top right(max,max), bottom right(max,min) and a color.
 * the names correspond to the first quarter of a Cartesian coordinate system. All the point of the frame can be
 * accessed (and as such so can their x and y values, because they are of type Point), and the Height and Width can be
 * calculated. The frame can be drawn on a drawSurface.
 **/
public class Frame {

    private Point bleft;
    private Point tleft;
    private Point tright;
    private Point bright;
    private java.awt.Color color;


    /**
     * constructor.
     *
     * @param bleft  Point, bottom left corner of frame
     * @param tleft  Point, top left corner of frame
     * @param tright Point, top right corner of frame
     * @param bright Point, bottom left corner of frame
     * @param color  type Color
     */
    public Frame(Point bleft, Point tleft, Point tright, Point bright, java.awt.Color color) {
        this.tleft = tleft;
        this.tright = tright;
        this.bright = bright;
        this.bleft = bleft;
        this.color = color;
    }

    /**
     * accessor.
     *
     * @return color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * accessor.
     *
     * @return bottom left corner
     */
    public Point getBleft() {
        return this.bleft;
    }

    /**
     * accessor.
     *
     * @return bottom right corner
     */
    public Point getBright() {
        return this.bright;
    }

    /**
     * accessor.
     *
     * @return top left corner
     */
    public Point getTleft() {
        return this.tleft;
    }

    /**
     * accessor.
     *
     * @return top right corner
     */
    public Point getTright() {
        return this.tright;
    }

    /**
     * calculates width of frame using distance method from point.
     *
     * @return width of frame
     */
    public double getWidth() {
        return (this.bleft.distance(this.bright));
    }

    /**
     * calculates height of frame using distance method from point.
     * @return height of frame
     */
    public double getHeight() {
        return (this.bleft.distance(this.tleft));
    }

    /**
     * Draw the frame on the given DrawSurface.
     *
     * @param surface type DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.bleft.getX(), (int) this.bleft.getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }
}
