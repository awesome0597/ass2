import java.util.ArrayList;
import java.util.List;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.1
 * date: 22/4/21
 *
 * <p>
 * Class that creates a game environment. The game environment holds a list of all the 'collidable' objects, in the
 * given environment. The class can add new obstacles to the list, and can find the closest object to a possible
 * collision.
 **/


public class GameEnvironment {
    private List<Collidable> listOfCollidables;

    /**
     * constructor.
     * creates a new empty list of collidable objects.
     */
    public GameEnvironment() {
        this.listOfCollidables = new ArrayList<Collidable>();
    }

    /**
     * accessor.
     *
     * @return type List<Collidable>.
     */
    public List<Collidable> getListOfCollidables() {
        return this.listOfCollidables;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c type Collidable
     */
    public void addCollidable(Collidable c) {
        this.listOfCollidables.add(c);
    }


    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, returns null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory type Line
     * @return type Collision Info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collisionPoint = trajectory.end();
        Collidable collisionObject = null;
        if (this.listOfCollidables.size() > 0) {
            for (Collidable x : listOfCollidables) {
                List<Point> temp = x.getCollisionRectangle().intersectionPoints(trajectory);
                if (temp.size() != 0) {
                    Point tmp = trajectory.closestIntersectionToStartOfLine(x.getCollisionRectangle());
                    if (trajectory.start().distance(tmp) < trajectory.start().distance(collisionPoint) && tmp != null) {
                        collisionPoint = tmp;
                        collisionObject = x;
                    }
                }

            }
        }
        if (collisionObject != null) {
            return new CollisionInfo(collisionPoint, collisionObject);
        } else {
            return null;
        }

    }
}
