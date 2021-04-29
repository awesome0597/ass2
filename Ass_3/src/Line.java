//322094111

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adira Weiss.
 * @version 6.0.1
 * @since: 27/4/21
 * <p>
 * Class that creates a Line.
 * <p>
 * A class that creates a line. A line is made up of two points, a start and an end (not an indication for which is
 * bigger or smaller they just signify the limits within which the line is contained). Both start and end can be
 * accessed as can their x and y values. The length of the line can be found as can the middle point of the line, and
 * two lines can be compared to check if they are equal (meaning the same line). It can be checked if two lines have
 * an intersecting point (boolean), and the point of intersection can also be found (function returns Point).
 **/
public class Line {
    private Point start;
    private Point end;
    private double epsilon = 0.00001;

    /**
     * constructor.
     *
     * @param start type Point
     * @param end   type Point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     *
     * @param x1 type double
     * @param y1 type double
     * @param x2 type double
     * @param y2 type double
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * accessor.
     *
     * @return type Point line start
     */
    public Point start() {
        return this.start;

    }

    /**
     * accessor.
     *
     * @return type Point line end
     */
    public Point end() {
        return this.end;
    }

    /**
     * Return the length of the line.
     *
     * @return type double distance between start and end
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return type Point middle point of the line
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * check if lines are equal distance.
     *
     * @param other type Line
     * @return true if lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start)));
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line. We check the size of the list and based on that is our return. If the list is empty, there are
     * no intersection points with the rectangle, if there is only one point we return that point, and in case of two
     * points we check which distance between the two is closest to the start point of the line and return that point.
     * the case where the line hits a corner is covered because the if statement contains ">=".
     *
     * @param rect type Rectangle
     * @return type Point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> a = new ArrayList<>();
        a = rect.intersectionPoints(this);
        if (a.size() == 0) {
            return null;
        } else if (a.size() == 1) {
            return a.get(0);
        } else {
            double dis = this.start.distance(a.get(0));
            int index = 0;
            for (int i = 1; i < a.size(); i++) {
                double tmp = this.start.distance(a.get(i));
                if (tmp <= dis) {
                    dis = tmp;
                    index = i;
                }
            }
            return a.get(index);
        }

    }

    /**
     * Helper method for intersectionWith.
     * checks if y value is in lines range.
     *
     * @param one type Line
     * @param y   type double
     * @return true or false
     */
    private boolean isYInRange(Line one, double y) {
        return ((y >= one.start.getY()) && (y <= one.end.getY()))
                || ((y <= one.start.getY()) && (y >= one.end.getY()));
    }

    /**
     * Helper method for intersectionWith.
     * checks if x value is in lines range.
     *
     * @param one type Line
     * @param x   type double
     * @return true or false
     */
    private boolean isXInRange(Line one, double x) {
        return ((x >= one.start.getX())
                && (x <= one.end.getX()))
                || ((x <= one.start.getX())
                && (x >= one.end.getX()));
    }

    /**
     * Helper method for intersectionWith.
     * checks if a line is parallel to the X axis.
     *
     * @param line type Line
     * @return true or false
     */
    private boolean isParallelX(Line line) {
        return Math.abs(line.start.getY() - line.end.getY()) <= epsilon;
    }

    /**
     * Helper method for intersectionWith.
     * checks if a line is parallel to the Y axis.
     *
     * @param line type Line
     * @return true or false
     */
    private boolean isParallelY(Line line) {
        return Math.abs(line.start.getX() - line.end.getX()) <= epsilon;
    }

    /**
     * Helper method for intersectionWith.
     * calculates the slope of a line.
     *
     * @param line type line
     * @return type double (the slope)
     */
    private double getSlope(Line line) {
        return (line.start.getY() - line.end.getY()) / (line.start.getX() - line.end.getX());
    }

    /**
     * Helper method for intersectionWith.
     * calculates the b value of a lines linear equation using the slope and a line.
     *
     * @param line type Line
     * @param a    type double
     * @return type double (b of linear equation)
     */
    private double getB(Line line, double a) {
        return line.start.getY() - (a * line.start.getX());
    }

    /**
     * Helper method for intersectionWith.
     * checks the case that only one of the lines is parallel to either the Y or X axis
     * and sends to accommodating method.
     * returns the point of intersection if exists or null.
     *
     * @param other type Line
     * @return type Point
     */
    private Point oneOfLinesIsParallel(Line other) {
        if (isParallelY(this)) {
            return oneLineParallelY(this, other);
        } else if (isParallelY(other)) {
            return oneLineParallelY(other, this);
        } else if (isParallelX(this)) {
            return oneLineParallelX(this, other);
        } else {
            return oneLineParallelX(other, this);
        }
    }

    /**
     * Helper method for intersectionWith.
     * Finds if there is an intersection point if line one is parallel to the X axis
     *
     * @param one type Line
     * @param two type Line
     * @return type Point
     */
    private Point oneLineParallelX(Line one, Line two) {
        double a = getSlope(two);
        double b = two.start.getY() - (a * two.start.getX());
        double x = (one.start.getY() - b) / a;
        Point p = new Point(x, one.start.getY());
        if (isYInRange(one, p.getY()) && isYInRange(two, p.getY())
                && isXInRange(two, p.getX()) && isXInRange(one, p.getX())) {
            return p;
        }
        return null;
    }

    /**
     * Helper method for intersectionWith.
     * Finds if there is an intersection point if line one is parallel to the Y axis
     *
     * @param one type Line
     * @param two type Line
     * @return type Point
     */
    private Point oneLineParallelY(Line one, Line two) {
        double a = getSlope(two);
        double b = two.start.getY() - (a * two.start.getX());
        double y = a * one.start.getX() + b;
        Point p = new Point(one.start.getX(), y);
        if (isYInRange(one, p.getY()) && isYInRange(two, p.getY())
                && isXInRange(two, p.getX()) && isXInRange(one, p.getX())) {
            return p;
        }
        return null;
    }

    /**
     * Helper method for intersectionWith.
     * Checks case that both lines are parallel to opposite axes.
     *
     * @param other type Line
     * @return type Point
     */
    private Point bothLinesAreParallel(Line other) {
        if (isParallelX(this) && isParallelY(other)) {
            if (isXInRange(this, other.start.getX()) && isYInRange(other, this.start.getY())) {
                return new Point(other.start.getX(), this.start.getY());
            }
        } else {
            if (isXInRange(other, this.start.getX()) && isYInRange(this, other.start.getY())) {
                return new Point(this.start.getX(), other.start.getY());
            }
        }
        return null;
    }

    /**
     * Helper method for intersectionWith.
     * checks if any of the ends of either line are the same point.
     *
     * @param other type Line
     * @return type Point
     */
    private Point findCommonPoint(Line other) {
        if ((this.start.equals(other.start)) || (this.start.equals(other.end))) {
            return new Point(this.start.getX(), this.start.getY());
        } else if ((this.end.equals(other.start)) || (this.end.equals(other.end))) {
            return new Point(this.end.getX(), this.end.getY());
        }
        return null;

    }

    /**
     * Helper method for intersectionWith.
     * Checks if one lines Y values are contained within other lines Y values
     *
     * @param one type Line
     * @param two type Line
     * @return true or false
     */
    private boolean isLineContainedY(Line one, Line two) {
        return ((((one.start.getY() > two.start.getY()) && (one.start.getY() < two.end.getY()))
                || ((one.start.getY() < two.start.getY()) && (one.start.getY() > two.end.getY())))
                || (((one.end.getY() > two.start.getY()) && (one.end.getY() < two.end.getY()))
                || ((one.end.getY() < two.start.getY()) && (one.end.getY() > two.end.getY()))));

    }

    /**
     * Helper method for intersectionWith.
     * Checks if one lines X values are contained within other lines X values
     *
     * @param one type Line
     * @param two type Line
     * @return true or false
     */
    private boolean isLineContainedX(Line one, Line two) {
        return (((one.start.getX() > two.start.getX()) && (one.start.getX() < two.end.getX()))
                || ((one.start.getX() < two.start.getX()) && (one.start.getX() > two.end.getX())))
                || (((one.end.getX() > two.start.getX()) && (one.end.getX() < two.end.getX()))
                || ((one.end.getX() < two.start.getX()) && (one.end.getX() > two.end.getX())));
    }

    /**
     * Method that returns whether two lines have an intersecting point.
     *
     * @param other type Line
     * @return true or false
     */
    public boolean isIntersecting(Line other) {
        return (intersectionWith(other) != null);
    }

    /**
     * Method that finds if two lines have an intersecting point.
     * if yes then method returns the point, else returns null.
     *
     * @param other type Line
     * @return type Point or null
     */
    public Point intersectionWith(Line other) {
        if (isParallelX(this) && isParallelX(other) || isParallelY(this) && isParallelY(other)) {
            if (isLineContainedY(this, other) || isLineContainedX(this, other)) {
                return null;
            }
            return findCommonPoint(other);
        }

        if (findCommonPoint(other) != null) {
            return findCommonPoint(other);
        }

        if (isParallelX(this) && isParallelY(other) || isParallelX(other) && isParallelY(this)) {
            return bothLinesAreParallel(other);
        }

        if (isParallelY(this) || isParallelY(other) || isParallelX(this) || isParallelX(other)) {
            return oneOfLinesIsParallel(other);
        }

        double a1 = getSlope(this);
        double a2 = getSlope(other);
        double b1 = getB(this, a1);
        double b2 = getB(other, a2);
        if (Math.abs(a1 - a2) <= epsilon) {
            return null;
        }
        double x = ((b2 - b1) / (a1 - a2));
        if (isXInRange(this, x) && isXInRange(other, x)) {
            return new Point(x, a1 * x + b1);
        }
        return null;
    }
}