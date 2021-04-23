import biuoop.DrawSurface;

import java.awt.*;

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;

    public Paddle(Rectangle rect) {
        this.rect = rect;
    }
//    public void moveLeft();
//    public void moveRight();

    // Sprite
    public void timePassed() {
        return;
    }

    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(this.rect.getColor());
        d.fillRectangle((int) this.rect.getUpperLeft().getX() + 1, (int) this.rect.getUpperLeft().getY() + 1,
                (int) this.rect.getWidth() - 2, (int) this.rect.getHeight() - 2);
    }

    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    private boolean checkRegion(double x, double region, double size) {
        return x >= this.rect.getUpperLeft().getX() + size * (region - 1)
                && (x < this.rect.getUpperLeft().getX() + size * region);
    }

    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double size = this.rect.getWidth() / 5;
        if (checkRegion(collisionPoint.getX(), 1, size)) {
            Velocity v = currentVelocity.fromDxDy();
            return v.fromAngleAndSpeed(v.getDx() - 60, v.getDy());
        } else if (checkRegion(collisionPoint.getX(), 2, size)) {
            Velocity v = currentVelocity.fromDxDy();
            return v.fromAngleAndSpeed(v.getDx() - 30, v.getDy());
        } else if (checkRegion(collisionPoint.getX(), 3, size)) {
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if (checkRegion(collisionPoint.getX(), 4, size)) {
            Velocity v = currentVelocity.fromDxDy();
            return v.fromAngleAndSpeed(v.getDx() + 30, v.getDy());
        } else if (checkRegion(collisionPoint.getX(), 5, size + 1)) {
            Velocity v = currentVelocity.fromDxDy();
            return v.fromAngleAndSpeed(v.getDx() + 60, v.getDy());
        } else {
            return currentVelocity;
        }

//       if (collisionPoint.getX() == this.rect.getUpperLeft().getX()
//                || collisionPoint.getX() == this.rect.getBottomRight().getX()) {
//            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
//        } else if (collisionPoint.getY() == this.rect.getUpperLeft().getY()
//                || collisionPoint.getY() == this.rect.getBottomRight().getY()) {
//            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
//        } else {
//            return currentVelocity;
//        }
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

}
