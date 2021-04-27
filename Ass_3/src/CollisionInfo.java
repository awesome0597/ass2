//322094111

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 26/4/21
 * Class that creates a CollisionInfo.
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
