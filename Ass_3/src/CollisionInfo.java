/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.1
 * date: 22/4/21
 *
 * <p>
 * Class that creates collision info consisting of a collision point and the object that is collided with at that point.
 **/
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     *
     * @param collisionPoint  type Point
     * @param collisionObject type Collidable
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return type Point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return type Collidable.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

}
