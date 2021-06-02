import biuoop.DrawSurface;

import java.awt.Color;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 2.0.1
 * date: 13/4/21
 *
 * <p>
 * This class is called to create a ball. The components that make up a ball are a center point (center type Point),
 * a radius (type int), a color (type Color), and a velocity (type Velocity). Velocity can be set through a dx,dy or,
 * through a set variable of type Velocity. You can access the x and y values of the center, the size of the ball
 * (the radius), color and velocity. A ball can be moved one step based on its velocity, and the velocity can be
 * changed in case ball reaches out of bounds of the window its in. The ball can also be drawn on a drawSurface.
 **/

public class Ball implements Sprite {

    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment g;

    /**
     * constructor.
     *
     * @param center type Point
     * @param r      type Double
     * @param color  type Color
     */

    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constructor.
     *
     * @param velocity type Velocity
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * constructor.
     *
     * @param dx type Double
     * @param dy type Double
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);

    }

    /**
     * adds game enviroment to ball.
     *
     * @param gameEnvironment type GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.g = gameEnvironment;
    }

    /**
     * accessor.
     *
     * @return The x value of the Center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * accessor.
     *
     * @return The y value of the Center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * accessor.
     *
     * @return The radius (r) of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * accessor.
     *
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * accessor.
     *
     * @return The Velocity (v) of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * method that checks if collision point is one of the corners of the collidable object.
     *
     * @param collision type Collision Info
     * @return type Point
     */
//    public boolean collisionPointIsCorner(CollisionInfo collision) {
//        Rectangle rect = collision.collisionObject().getCollisionRectangle();
//        return collision.collisionPoint().equals(rect.getUpperRight())
//                || collision.collisionPoint().equals(rect.getBottomRight())
//                || collision.collisionPoint().equals(rect.getUpperLeft())
//                || collision.collisionPoint().equals(rect.getBottomLeft());
//    }

    /**
     * Method that finds the closer corner to the collision point.
     *
     * @param collision type Collision Info
     * @return type Point
     */
    public Point findCloserCorner(CollisionInfo collision) {
        Rectangle rect = collision.collisionObject().getCollisionRectangle();
        if (rect.getBottomRight().distance(collision.collisionPoint())
                < rect.getBottomRight().distance(collision.collisionPoint())) {
            return rect.getBottomRight();
        } else {
            return rect.getBottomLeft();
        }
    }

    /**
     * Method that checks if applying velocity to point sends it into a different collidable.
     *
     * @param ball type Ball
     * @return true or false
     */
    public boolean isInCollidable(Ball ball) {
        for (int i = 1; i < g.getListOfCollidables().size(); i++) {
            Rectangle collRec = g.getListOfCollidables().get(i).getCollisionRectangle();
            Point upperLeft = collRec.getUpperLeft();
            Point upperRight = collRec.getUpperRight();
            double height = collRec.getHeight();
            if (upperLeft.getX() < ball.getX() && upperRight.getX() > ball.getX()
                    && upperRight.getY() < ball.getY() && height + upperRight.getY() > ball.getY()) {
                if (upperLeft.getX() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Called from drawAnimation. This function moves the ball forward one step according to its given velocity.
     */
    public void moveOneStep() {
        CollisionInfo maybeCollision = this.g.getClosestCollision(
                new Line(this.center, this.getVelocity().applyToPoint(this.center)));
        if (maybeCollision != null) {
            this.setVelocity(maybeCollision.collisionObject().
                    hit(this, maybeCollision.collisionPoint(), this.v));
            if (maybeCollision.collisionPoint().equals(this.center)) {
                Ball tmpBall = this;
                tmpBall.center = this.getVelocity().applyToPoint(tmpBall.center);
                if (isInCollidable(tmpBall)) { //if moving ball traps it in a collidable change velocity again.
                    this.setVelocity(maybeCollision.collisionObject().
                            hit(this, findCloserCorner(maybeCollision), this.v));
                    this.center = this.getVelocity().applyToPoint(findCloserCorner(maybeCollision));
                } else {
                    this.center = this.getVelocity().applyToPoint(this.center);
                }

            } else {
                maybeCollision = this.g.getClosestCollision(
                        new Line(this.center, this.getVelocity().applyToPoint(this.center)));
                if (maybeCollision != null) {
                    this.setVelocity(maybeCollision.collisionObject().
                            hit(this, maybeCollision.collisionPoint(), this.v));
                }

            }

            if (this.g.getListOfCollidables().get(0).getCollisionRectangle().getUpperLeft().getY()
                    < this.center.getY()) {
                this.center = new Point(center.getX(), this.center.getY() - (this.r));
            }

        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        if (isInCollidable(this)) {
            this.center = new Point(35, 35);
        }
    }


    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface type DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.getSize() - 1);
    }

    /**
     * notify sprite that time has passed. Invokes move one step.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * adds Sprite to Game.
     *
     * @param game type Game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        this.setGameEnvironment(game.getEnvironment());

    }
}

