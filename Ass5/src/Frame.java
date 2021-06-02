import biuoop.DrawSurface;

import java.awt.Color;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 23.0.1
 * date: 14/4/21
 *
 * <p>
 * a Class that creates the frame that can limit the area of a window that a ball can move around in. A frame is made up
 * of two points the minimum point (x1,y1), containing the minimum values of x and y, maximum containing the max values
 * of x and y and a color. All the point of the frame can be found , the x and y values of min and max can be accessed,
 * and the Height and Width can be calculated. The frame can be drawn on a drawSurface.
 **/
public class Frame {

    private Point min;
    private Point max;
    private java.awt.Color color;


    /**
     * constructor.
     *
     * @param x1    double x value of min
     * @param y1    double y value of min
     * @param x2    double x value of max
     * @param y2    double y value of max
     * @param color gives the frame a color if desired
     */
    public Frame(double x1, double y1, double x2, double y2, java.awt.Color color) {
        this.min = new Point(x1, y1);
        this.max = new Point(x2, y2);
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
     * @return the min point of the frame
     */
    public Point getMin() {
        return this.min;
    }

    /**
     * gets the x value of the minimum point.
     *
     * @return double
     */
    public double getMinX() {
        return this.min.getX();
    }

    /**
     * returns the y value of the minimum point.
     *
     * @return double
     */
    public double getMinY() {
        return this.min.getY();
    }


    /**
     * accessor.
     *
     * @return the max point of the frame
     */
    public Point getMax() {
        return this.max;
    }

    /**
     * finds the point with the x value of min and the y value of max.
     *
     * @return new Point with the the x value of min and the y value of max
     */
    public Point getMinMax() {
        return new Point(min.getX(), max.getY());
    }

    /**
     * finds the point with the x value of max and the y value of min.
     *
     * @return new Point with the the x value of max and the y value of min
     */
    public Point getMaxMin() {
        return new Point(max.getX(), min.getY());
    }

    /**
     * calculates width of frame using distance method from point.
     *
     * @return double
     */
    public double getWidth() {
        return (this.min.distance(this.getMinMax()));
    }

    /**
     * calculates height of frame using distance method from point.
     *
     * @return double
     */
    public double getHeight() {
        return (this.min.distance(this.getMaxMin()));
    }

    /**
     * Draw the frame on the given DrawSurface.
     *
     * @param surface type DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.min.getX(), (int) this.min.getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }
}
