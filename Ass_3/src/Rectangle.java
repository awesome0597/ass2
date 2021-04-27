import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.1
 * date: 22/4/21
 *
 * <p>
 * Class that creates a Rectangle made up of two double variables (height and width) and the top left corner of the
 * rectangle. All values can be accessed. there is a method to calculate the remaining three points of the rectangle.
 * There is a method that finds all the intersection points of one line with the rectangle, and a method to draw the
 * rectangle on a draw surface.
 **/



public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;


    /**
     * constructor.
     *
     * @param upperLeft type Point. Upper left point of rectangle.
     * @param width     type Double. Width of rectangle.
     * @param height    type Double. Height of rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }


    /**
     * constructor.
     *
     * @param color type Color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * accessor.
     *
     * @return type Color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * accessor.
     *
     * @return type double. width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * accessor.
     *
     * @return type double. height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * accessor.
     *
     * @return type Point. Upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * calculates the bottom left corner of the rectangle.
     *
     * @return type Point.
     */
    public Point getBottomLeft() {

        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + getHeight());

    }

    /**
     * calculates the bottom right corner of the rectangle.
     *
     * @return type Point.
     */
    public Point getBottomRight() {
        return new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY() + getHeight());
    }

    /**
     * calculates the top right corner of the rectangle.
     *
     * @return type Point.
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY());
    }

    /**
     * resets the upper left corner of a given rectangle.
     *
     * @param upperLeft type Point.
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * checks if the intersection function returns a point. If it does its added to the list of points.
     *
     * @param line type Line. The line with which we check the rectangle has intersection points with
     * @return type list of intersection points with rectangle
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> a = new ArrayList<>();
        if (line.intersectionWith(new Line(this.getUpperLeft(), this.getBottomLeft())) != null) {
            a.add(line.intersectionWith(new Line(this.getUpperLeft(), this.getBottomLeft())));
        }
        if (line.intersectionWith(new Line(this.getUpperRight(), this.getUpperLeft())) != null) {
            a.add(line.intersectionWith(new Line(this.getUpperLeft(), this.getUpperRight())));
        }
        if (line.intersectionWith(new Line(this.getUpperRight(), this.getBottomRight())) != null) {
            a.add(line.intersectionWith(new Line(this.getUpperRight(), this.getBottomRight())));
        }
        if (line.intersectionWith(new Line(this.getBottomLeft(), this.getBottomRight())) != null) {
            a.add(line.intersectionWith(new Line(this.getBottomLeft(), this.getBottomRight())));
        }
        return a;
    }

}
