import java.util.ArrayList;
import java.util.List;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.2
 * date: 7/4/21
 *
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
        return ((this.start == other.start && this.end == other.end)
                || (this.start == other.end && this.end == other.start));
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
                if (tmp <= dis){
                    dis = tmp;
                    index = i;
                };
            }
            return a.get(index);
        }

    }

    /**
     * related to 'intersectionWith' method. Checks case that one line is point and other is a line.
     *
     * @param one type Line
     * @param two type Line
     * @return value of point if intersect and null otherwise
     */
    private Point pointLineRelation(Line one, Line two) {
        double m;
        double n;
        double y;
        if (one.start.equals(one.end) && !two.start.equals(two.end)) { //if first line is point
            if (two.start.getX() - two.end.getX() == 0) { // line is parallel to x axis
                if ((one.start.getX() == two.start.getX())
                        && (((one.start.getY() <= two.start.getY())
                        && one.start.getY() >= two.end.getY())
                        || ((one.start.getY() >= two.start.getY())
                        && one.start.getY() <= two.end.getY()))) {
                    return new Point(one.start.getX(), one.start.getY());
                }
                return null;
            } else {
                m = (two.start.getY() - two.end.getY()) / (two.start.getX() - two.end.getX());
                n = two.start.getY() - (m * two.start.getX());
            }
            y = m * one.start.getX() + n;
            if (y == one.start.getY()) {
                return new Point(one.start.getX(), one.start.getY());
            }

            return null;

        }
        return null;
    }

    /**
     * related to 'intersectionWith' method. Checks case if both lines are points.
     *
     * @param one type Line
     * @param two type Line
     * @return value of point if intersect and null otherwise
     */
    private Point bothLinesArePoints(Line one, Line two) {
        if (one.start.equals(one.end) && two.start.equals(two.end)) { //if both lines are points
            if (one.start.equals(two.start)) {
                return new Point(one.start.getX(), two.start.getY());
            }
            return null;
        }
        return null;
    }

    /**
     * related to 'intersectionWith' method. Checks if y value of one line is contained in the other.
     *
     * @param one type Line
     * @param two type Line
     * @return true if conditions met false otherwise
     */
    private boolean isLineContainedY(Line one, Line two) {
        if (((one.start.getY() > two.start.getY()) && (one.start.getY() < two.end.getY()))
                || ((one.start.getY() < two.start.getY()) && (one.start.getY() > two.end.getY()))) {
            return true;
        } else if (((one.end.getY() > two.start.getY()) && (one.end.getY() < two.end.getY()))
                || ((one.end.getY() < two.start.getY()) && (one.end.getY() > two.end.getY()))) {
            return true;
        }
        return false;
    }

    /**
     * related to 'intersectionWith' method. Checks if x value of one line is contained in the other.
     *
     * @param one type Line
     * @param two type Line
     * @return true if conditions met false otherwise
     */
    private boolean isLineContainedX(Line one, Line two) {
        if (((one.start.getX() > two.start.getX()) && (one.start.getX() < two.end.getX()))
                || ((one.start.getX() < two.start.getX()) && (one.start.getX() > two.end.getX()))) {
            return true;
        } else if (((one.end.getX() > two.start.getX()) && (one.end.getX() < two.end.getX()))
                || ((one.end.getX() < two.start.getX()) && (one.end.getX() > two.end.getX()))) {
            return true;
        }
        return false;
    }

    /**
     * related to 'intersectionWith' method. Checks if both lines are the same.
     *
     * @param other type line
     * @return value of point if intersect and null otherwise
     */
    private Point areEndsSame(Line other) {
        if ((this.start.equals(other.start)) || (this.start.equals(other.end))) {
            return new Point(this.start.getX(), this.start.getY());
        } else if ((this.end.equals(other.start)) || (this.end.equals(other.end))) {
            return new Point(this.end.getX(), this.end.getY());
        }
        return null;

    }

    /**
     * related to 'intersectionWith' method. Checks if y is in range.
     *
     * @param one type line
     * @param y   double from line equation
     * @return true if conditions met false otherwise
     */
    private boolean isYInRange(Line one, double y) {
        return ((y >= one.start.getY()) && (y <= one.end.getY()))
                || ((y <= one.start.getY()) && (y >= one.end.getY()));
    }

    /**
     * related to 'intersectionWith' method. Checks if x is in range.
     *
     * @param one type line
     * @param x   value of start of other line
     * @return true if conditions met false otherwise
     */
    private boolean isXInRange(Line one, double x) {
        return (((x >= one.start.getX())
                && (x <= one.end.getX()))
                || ((x <= one.start.getX())
                && (x >= one.end.getX())));
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other type Line
     * @return boolean
     */
    public boolean isIntersecting(Line other) {
        return (intersectionWith(other) != null);
    }

    /**
     * finds if their is intersection point between two lines.
     *
     * @param other type Line
     * @return Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double m1;
        double m2;
        double x;
        double y;
        double n1;
        double n2;

        if (pointLineRelation(this, other) != null) {
            return new Point(this.start.getX(), this.start.getY());
        }
        if (pointLineRelation(other, this) != null) {
            return new Point(other.start.getX(), other.start.getY());
        }
        if (bothLinesArePoints(this, other) != null) {
            return new Point(this.start.getX(), this.start.getY());
        } else if ((this.start.getX() == this.end.getX())
                && (other.start.getX() == other.end.getX())) { //both lines are parallel to y axis
            if (this.length() < other.length()) {
                if (isLineContainedY(this, other)) {
                    return null;
                }
            } else if (this.length() > other.length()) {
                if (isLineContainedY(other, this)) {
                    return null;
                }
            } else if (areEndsSame(other) != null) {
                return areEndsSame(other);
            }
            return null;
        } else if ((this.start.getY() == this.end.getY())
                && (other.start.getY() == other.end.getY())) { //both lines are parallel to x axis
            if (isLineContainedX(this, other)) {
                return null;
            }
            if (areEndsSame(other) != null) {
                return areEndsSame(other);
            }
        }

        if (this.start.getX() - this.end.getX() == 0) {
            x = this.start.getX();
            if (!isXInRange(other, this.start.getX())) {
                return null;
            }
            m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            n2 = other.start.getY() - (m2 * other.start.getX());
            y = m2 * x + n2;
            if (isYInRange(this, y)) {
                return new Point(x, y);
            }
            return null;

        } else {
            m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            n1 = this.start.getY() - (m1 * this.start.getX());
        }
        if (other.start.getX() - other.end.getX() == 0) {
            x = other.start.getX();
            if (!isXInRange(this, x)) {
                return null;
            }
            m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            n1 = this.start.getY() - (m1 * this.start.getX());
            y = m1 * x + n1;
            if (isYInRange(other, y)) {
                return new Point(x, y);
            }
            return null;


        } else {
            m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            n2 = other.start.getY() - (m2 * other.start.getX());
        }
        if ((m2 == m1) && (n1 == n2)) {
            return null;
        }

        if (m2 != m1) {
            x = ((n2 - n1) / (m1 - m2));
        } else {
            return null;
        }
        if (((x >= this.start.getX() && x <= this.end.getX())
                || (x >= this.end.getX() && x <= this.start.getX()))
                && ((x >= other.start.getX() && x <= other.end.getX())
                || (x >= other.end.getX() && x <= other.start.getX()))) {
            y = m1 * x + n1;
            return new Point(x, y);
        }
        return null;

    }

}
