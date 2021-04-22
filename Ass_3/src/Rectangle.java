import biuoop.DrawSurface;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
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

    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }


    public Point getBottomLeft() {

        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + getHeight());

    }

    public Point getBottomRight() {
        return new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY() + getHeight());
    }

    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY());
    }

    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLUE);
        surface.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY(),
                (int) this.getWidth(), (int) this.getHeight());
    }


}
