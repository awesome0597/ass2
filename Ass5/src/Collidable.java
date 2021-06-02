/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.1
 * date: 22/4/21
 *
 * <p>
 * Interface that describes a collidable object.
 **/
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     *
     * @return type Rectangle
     */
    Rectangle getCollisionRectangle();


    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  type Point
     * @param currentVelocity type Velocity
     * @return type Velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
