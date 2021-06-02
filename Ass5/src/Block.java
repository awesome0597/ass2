import biuoop.DrawSurface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.1
 * date: 22/4/21
 *
 * <p>
 * Class that creates a block implementing the Collidable interface. The Collidable type is a rectangle
 * that can be accessed. There is a method that changes a balls velocity if it is enroute to collide with it.
 **/

public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     *
     * @param rect type Rectangle.
     */
    public Block(Rectangle rect, PrintingHitListener pl) {
        this.rect = rect;
       this.hitListeners = new ArrayList<HitListener>();
       this.hitListeners.add(pl);
    }



    /**
     * accessor.
     * Return the "collision shape" of the object.
     *
     * @return type Rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * checks if X value of collisionPoint equals any of the X values of the block.
     *
     * @param collisionPoint type Point
     * @return boolean
     */
    private boolean collisionWithX(Point collisionPoint) {
        return collisionPoint.getX() == this.rect.getUpperLeft().getX()
                || collisionPoint.getX() == this.rect.getBottomRight().getX();
    }

    /**
     * checks if Y value of collisionPoint equals any of the Y values of the block.
     *
     * @param collisionPoint type Point
     * @return boolean
     */
    private boolean collisionWithY(Point collisionPoint) {
        return collisionPoint.getY() == this.rect.getUpperLeft().getY()
                || collisionPoint.getY() == this.rect.getBottomRight().getY();
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity. The return is the new
     * velocity expected after the hit (based on the force the object inflicted on us). If we hit the y axis we change
     * the value of the Dx, if we hit the X axis we change the value of Dy, if both are hit we change the values of both
     * dx and dy. in case nothing is hit we return the current velocity.
     *
     * @param collisionPoint  type Point
     * @param currentVelocity type Velocity
     * @return type Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        if (collisionWithX(collisionPoint) && collisionWithY(collisionPoint)) {
            return new Velocity((-1) * currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if (collisionWithX(collisionPoint)) {
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        } else if (collisionWithY(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else {
            return currentVelocity;
        }
    }

    /**
     * Draw the rectangle on the given DrawSurface.
     *
     * @param d type DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.rect.getColor());
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        return;
    }

    /**
     * adds type to list of sprites anf collidables.
     *
     * @param g type Game
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);

    }

    public void removeFromGame(Game g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
