//322094111

import biuoop.DrawSurface;

import java.awt.Color;

public class Background implements Sprite {
    private Rectangle rect1;
    private Rectangle rect2;

    /**
     * constructor
     *
     * @param color type color
     */
    public Background(Color color) {
        this.rect1 = new Rectangle(new Point(0, 0), 800, 600, color);
        this.rect2 = new Rectangle(new Point(0, 0), 800, 20, Color.PINK);
    }

    /**
     * accessor
     *
     * @return type rectangle
     */
    public Rectangle getRect1() {
        return this.rect1;
    }

    public Rectangle getRect2() {
        return this.rect2;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.rect1.getColor());
        d.fillRectangle((int) this.rect1.getUpperLeft().getX(), (int) this.rect1.getUpperLeft().getY(),
                (int) this.rect1.getWidth(), (int) this.rect1.getHeight());

        d.setColor(this.rect2.getColor());
        d.fillRectangle((int) this.rect2.getUpperLeft().getX(), (int) this.rect2.getUpperLeft().getY(),
                (int) this.rect2.getWidth(), (int) this.rect2.getHeight());
    }

    @Override
    public void timePassed() {
        return;
    }
}
