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
     * @param v type Velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
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
     * @param g type GameEnvironment.
     */
    public void setGameEnviroment(GameEnvironment g) {
        this.g = g;
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
     * Called from drawAnimation. This function moves the ball forward one step according to its given velocity.
     */
    public void moveOneStep() {
        CollisionInfo tmp;
        tmp = this.g.getClosestCollision(new Line(this.center, this.getVelocity().applyToPoint(this.center)));
        if (tmp != null) {
            this.setVelocity(tmp.collisionObject().hit(tmp.collisionPoint(), this.v));
            if (this.g.getListOfCollidables().get(0).getCollisionRectangle().getUpperLeft().getY()
                    < this.center.getY()) {
                this.center = new Point(center.getX(), this.center.getY() - (this.r / 2));
            }
            for (int i = 1; i < g.getListOfCollidables().size(); i++) {
                Rectangle collRec = g.getListOfCollidables().get(i).getCollisionRectangle();
                Point leftmost = collRec.getUpperLeft();
                Point rightMost = collRec.getUpperRight();
                double iHeight = collRec.getHeight();
                //spit ball out on opposite upper corner if gets trapped by the paddle
                if (leftmost.getX() < center.getX()
                        && rightMost.getX() > center.getX()
                        && rightMost.getY() < center.getY()
                        && iHeight + rightMost.getY() > center.getY()) {
                    if (leftmost.getX() > 0) {
                        this.center = new Point(collRec.getBottomLeft().getX() - 2,
                                collRec.getBottomLeft().getX() + 2);
                    } else {
                        this.center = new Point(730, 50);
                    }
                }
            }
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        this.center = this.getVelocity().applyToPoint(this.center);

    }


    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface type DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        //draw full black shape fpr outline
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.getSize());
        //draw shape with 1 width border
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
        this.setGameEnviroment(game.getEnvironment());

    }
}

