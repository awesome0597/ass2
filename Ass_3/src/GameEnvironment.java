import java.util.ArrayList;
import java.util.List;


public class GameEnvironment {
    private List<Collidable> listOfCollidables;

    public GameEnvironment() {
       this.listOfCollidables = new ArrayList<Collidable>();
    }

    public List<Collidable> getListOfCollidables(){
        return this.listOfCollidables;
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.listOfCollidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
       Point collisionPoint = trajectory.end();
       Collidable collisionObject = null;
       if (this.listOfCollidables.size() > 0){
           for (Collidable x: listOfCollidables){
               List<Point> temp = x.getCollisionRectangle().intersectionPoints(trajectory);
               if (temp.size() != 0){
                   Point tmp = trajectory.closestIntersectionToStartOfLine(x.getCollisionRectangle());
                   if (trajectory.start().distance(tmp) < trajectory.start().distance(collisionPoint) && tmp != null){
                       collisionPoint = tmp;
                       collisionObject = x;
                   }
               }

           }
       }
       if (collisionObject != null){
           return new CollisionInfo(collisionPoint,collisionObject);
       } else   {
           return null;
       }

    }
}
