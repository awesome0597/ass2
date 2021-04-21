import biuoop.DrawSurface;

import java.awt.*;

public class Block implements Collidable {
    private Rectangle rect;

    public Block(Rectangle rect){
        this.rect = rect;
    }

    // Return the "collision shape" of the object.
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     *
     * Notify the object that we collided with it at collisionPoint with a given velocity. The return is the new
     * velocity expected after the hit (based on the force the object inflicted on us). If we hit the y axis we change
     * the value of the Dx, else we change the value of Dy.
     *
     * @param collisionPoint type Point
     * @param currentVelocity type Velocity
     * @return type Velocity
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getX() == this.rect.getUpperLeft().getX()
                || collisionPoint.getX() == this.rect.getBottomRight().getX()) {
            return new Velocity((-1)*currentVelocity.getDx(), currentVelocity.getDy());
        } else if (collisionPoint.getY() == this.rect.getUpperLeft().getY()
                || collisionPoint.getY() == this.rect.getBottomRight().getY()) {
            return new Velocity(currentVelocity.getDx(), (-1)*currentVelocity.getDy());
        } else {
            return currentVelocity;
        }
    }

    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLUE);
        surface.fillRectangle((int)this.rect.getUpperLeft().getX(),(int)this.rect.getUpperLeft().getY(),
                (int)this.rect.getWidth(), (int)this.rect.getHeight());
    }

}
