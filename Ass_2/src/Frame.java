import biuoop.DrawSurface;

import java.awt.*;

public class Frame {

    private Point tleft;
    private Point tright;
    private Point bright;
    private Point bleft;
    private java.awt.Color color;


    // constructor
    public Frame(Point bleft, Point tleft, Point tright, Point bright, java.awt.Color color) {
        this.tleft = tleft;
        this.tright = tright;
        this.bright = bright;
        this.bleft = bleft;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Point getBleft() {
        return bleft;
    }

    public Point getBright() {
        return bright;
    }

    public Point getTleft() {
        return tleft;
    }

    public Point getTright() {
        return tright;
    }

    public void drawOn(DrawSurface surface) {
        double rib = this.tleft.distance(this.bleft);
        surface.setColor(this.getColor());
        surface.fillRectangle((int)this.bleft.getX(), (int)this.bleft.getY(), (int) rib, (int) rib);
    }
}
