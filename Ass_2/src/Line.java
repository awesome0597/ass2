/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.2
 * date: 7/4/21
 *
 * <p>
 **/
public class Line {
    private Point start;
    private Point end;

    // constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    // Return the length of the line
    public double length() {
        return this.start.distance(end);
    }

    // Returns the middle point of the line
    public Point middle() {

        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    // Returns the start point of the line
    public Point start() {
        return this.start;

    }

    // Returns the end point of the line
    public Point end() {
        return this.end;
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        return (intersectionWith(other) != null);
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        double m1;
        double m2;
        double x;
        double y;
        double n1;
        double n2;

        if (this.start.equals(this.end) && !other.start.equals(other.end)) { //if first line is point
            if (other.start.getX() - other.end.getX() == 0) { // line is parallel to x axis
                if ((this.start.getX() == other.start.getX()) && (((this.start.getY() <= other.start.getY()) && this.start.getY() >= other.end.getY()) || ((this.start.getY() >= other.start.getY()) && this.start.getY() <= other.end.getY()))) {
                    return new Point(this.start.getX(), this.start.getY());
                }
                return null;
            } else {
                m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
                n2 = other.start.getY() - (m2 * other.start.getX());
            }
            y = m2 * this.start.getX() + n2;
            if (y == this.start.getY()) {
                return new Point(this.start.getX(), this.start.getY());
            }
            return null;

        } else if (other.start.equals(other.end) && !this.start.equals(this.end)) { //if second line is point
            if (this.start.getX() - this.end.getX() == 0) { // line is parallel to x axis
                if ((this.start.getX() == other.start.getX()) && (((other.start.getY() <= this.start.getY()) && other.start.getY() >= this.end.getY()) || ((other.start.getY() >= this.start.getY()) && other.start.getY() <= this.end.getY()))) {
                    return new Point(other.start.getX(), other.start.getY());
                }
                return null;
            } else {
                m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
                n1 = this.start.getY() - (m1 * this.start.getX());
            }
            y = m1 * other.start.getX() + n1;
            if (y == other.start.getY()) {
                return new Point(other.start.getX(), other.start.getY());
            }
            return null;
        } else if (this.start.equals(this.end) && other.start.equals(other.end)) { //if both lines are points
            if (this.start.equals(other.start)) {
                return new Point(this.start.getX(), this.start.getY());
            }
            return null;
        } else if ((this.start.getX() == this.end.getX()) && (other.start.getX() == other.end.getX())) {//both lines are parallel to y axis
            if (this.length() < other.length()) {
                if (((this.start.getY() > other.start.getY()) && (this.start.getY() < other.end.getY())) || ((this.start.getY() < other.start.getY()) && (this.start.getY() > other.end.getY()))) {
                    return null;
                } else if (((this.end.getY() > other.start.getY()) && (this.end.getY() < other.end.getY())) || ((this.end.getY() < other.start.getY()) && (this.end.getY() > other.end.getY()))) {
                    return null;
                }
            } else if (this.length() > other.length()) {
                if (((other.start.getY() > this.start.getY()) && (other.start.getY() < this.end.getY())) || ((other.start.getY() < this.start.getY()) && (other.start.getY() > this.end.getY()))) {
                    return null;
                } else if (((other.end.getY() > this.start.getY()) && (other.end.getY() < this.end.getY())) || ((other.end.getY() < this.start.getY()) && (other.end.getY() > this.end.getY()))) {
                    return null;
                }
            } else if ((this.start.equals(other.start)) || (this.start.equals(other.end))) {
                return new Point(this.start.getX(), this.start.getY());
            } else if ((this.end.equals(other.start)) || (this.end.equals(other.end))) {
                return new Point(this.end.getX(), this.end.getY());
            }
            return null;
        } else if ((this.start.getY() == this.end.getY()) && (other.start.getY() == other.end.getY())) {//both lines are parallel to x axis
            if (((this.start.getX() > other.start.getX()) && (this.start.getX() < other.end.getX())) || ((this.start.getX() < other.start.getX()) && (this.start.getX() > other.end.getX()))) {
                return null;
            } else if (((this.end.getX() > other.start.getX()) && (this.end.getX() < other.end.getX())) || ((this.end.getX() < other.start.getX()) && (this.end.getX() > other.end.getX()))) {
                return null;
            } else if ((this.start.equals(other.start)) || (this.start.equals(other.end))) {
                return new Point(this.start.getX(), this.start.getY());
            } else if ((this.end.equals(other.start)) || (this.end.equals(other.end))) {
                return new Point(this.end.getX(), this.end.getY());
            }
        }

        if (this.start.getX() - this.end.getX() == 0) {
            x = this.start.getX();
            if (!((x >= other.start.getX()) && (x <= other.end.getX())) || ((x <= other.start.getX()) && (x >= other.end.getX()))) {
                return null;
            }
            m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
            n2 = other.start.getY() - (m2 * other.start.getX());
            y = m2 * x + n2;
            if (((y >= this.start.getY()) && (y <= this.end.getY())) || ((y <= this.start.getY()) && (y >= this.end.getY()))) {
                return new Point(x, y);
            }
            return null;

        } else {
            m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            n1 = this.start.getY() - (m1 * this.start.getX());
        }
        if (other.start.getX() - other.end.getX() == 0) {
            x = other.start.getX();
            if (!((x >= this.start.getX()) && (x <= this.end.getX())) || ((x <= this.start.getX()) && (x >= this.end.getX()))) {
                return null;
            }
            m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            n1 = this.start.getY() - (m1 * this.start.getX());
            y = m1 * x + n1;
            if (((y >= other.start.getY()) && (y <= other.end.getY())) || ((y <= other.start.getY()) && (y >= other.end.getY()))) {
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
          //  x = (other.start.getY() - n1) / m1;
            return null;
        }
        if (((x >= this.start.getX() && x <= this.end.getX()) || (x >= this.end.getX() && x <= this.start.getX())) && ((x >= other.start.getX() && x <= other.end.getX()) || (x >= other.end.getX() && x <= other.start.getX()))) {
            y = m1 * x + n1;
            return new Point(x, y);
        }
        return null;

    }

    // equals -- return true if lines are equal, false otherwise
    public boolean equals(Line other) {
        return ((this.start == other.start && this.end == other.end) || (this.start == other.end && this.end == other.start));
    }
}
