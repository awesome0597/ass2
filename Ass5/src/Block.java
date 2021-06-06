//322094111

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * @author Adira Weiss.
 * @version 3.0.1
 * @since: 04/06/21
 *
 * <p>
 * Class that creates a block implementing the Collidable, Sprite and HitNotifier interface. The Collidable type is a
 * rectangle that can be accessed. There is a method that changes a balls velocity if it is enroute to collide with it.
 **/

public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private List<HitListener> hitListeners;
    private Boolean hasBorder;

    /**
     * constructor.
     *
     * @param rect      type Rectangle
     * @param br        type BlockRemover
     * @param hasBorder type Boolean
     */
    public Block(Rectangle rect, BlockRemover br, Boolean hasBorder) {
        this.rect = rect;
        this.hitListeners = new ArrayList<HitListener>();
        this.hitListeners.add(br);
        this.hasBorder = hasBorder;
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

    /**
     * Notifies all Neccessary Listeners of the hit.
     *
     * @param hitter type Ball
     */
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
     * dx and dy. in case nothing is hit we return the current velocity. Notifies Listeners Of hitting object (ball),
     * and object that was hit (block).
     *
     * @param collisionPoint  type Point
     * @param currentVelocity type Velocity
     * @param hitter          type Ball
     * @return type Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        if (collisionWithX(collisionPoint) && collisionWithY(collisionPoint)) {
            this.notifyHit(hitter);
            return new Velocity((-1) * currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if (collisionWithX(collisionPoint)) {
            this.notifyHit(hitter);
            return new Velocity((-1) * currentVelocity.getDx(), currentVelocity.getDy());
        } else if (collisionWithY(collisionPoint)) {
            this.notifyHit(hitter);
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else {
            this.notifyHit(hitter);
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
        if (this.hasBorder) {
            d.setColor(Color.BLACK);
            d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
        }

    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        return;
    }

    /**
     * adds type to list of sprites and collidables.
     *
     * @param g type Game
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);

    }

    /**
     * removes type from list of sprites and collidables.
     *
     * @param g type Game
     */
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
