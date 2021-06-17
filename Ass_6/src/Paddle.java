//322094111

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * @author Adira Weiss.
 * @version 4.0.1
 * @since: 27/4/21
 * Class that creates a game.
 * <p>
 * Class that implements both the collidable and sprite methods. Its members are a rectangle (its shape), and a keyboard
 * sensor allowing it to move on the screen during the game.
 **/


public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private int speeed;

    /**
     * constructor.
     *
     * @param rect type Rectangle
     * @param gui  type GUI
     * @param s    type int
     */
    public Paddle(Rectangle rect, GUI gui, int s) {
        this.rect = rect;
        this.keyboard = gui.getKeyboardSensor();
        this.speeed = s;

    }

    /**
     * Changes the upper left corner of rectangle to make it look like the paddle is moving left.
     */
    public void moveLeft() {
        if (this.rect.getUpperLeft().getX() - speeed > 20) {
            this.rect.setUpperLeft(
                    new Point(this.rect.getUpperLeft().getX() - speeed, this.rect.getUpperLeft().getY()));
        } else {
            this.rect.setUpperLeft(new Point(20, this.rect.getUpperLeft().getY()));
        }


    }

    /**
     * Changes the upper left corner of rectangle to make it look like the paddle is moving right.
     */
    public void moveRight() {
        if (this.rect.getUpperRight().getX() + speeed < 780) {
            this.rect.setUpperLeft(
                    new Point(this.rect.getUpperLeft().getX() + speeed, this.rect.getUpperLeft().getY()));
        } else {
            this.rect.setUpperLeft(new Point(780 - this.rect.getWidth(), this.rect.getUpperLeft().getY()));
        }

    }

    /**
     * this method notifies the paddle that time has passed.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * this method draws the paddle on the drawsurface.
     *
     * @param d the drawsurface the sprites are drawn on.
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
     * returns the object of paddle.
     *
     * @return type Rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * changes the velocity of the ball based on the area of the paddle that is hit.
     *
     * @param hitter          type Ball
     * @param collisionPoint  point of collision with the paddle
     * @param currentVelocity current velocity of the moving ball
     * @return type Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double paddleX = this.rect.getUpperLeft().getX();
        double collisionPointX = collisionPoint.getX();
        double partOfPaddle = (this.rect.getWidth() / 5);
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));

        if (collisionPointX > this.rect.getBottomRight().getX() || collisionPointX < this.rect.getUpperLeft().getX()) {
            return new Velocity((-1) * currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if (collisionPoint.getY() > this.rect.getUpperLeft().getY()) {
            return new Velocity((-1) * currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        } else if (collisionPointX <= partOfPaddle + paddleX) {
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if (collisionPointX <= (2 * partOfPaddle + paddleX)) {
            return Velocity.fromAngleAndSpeed(330, speed);
        } else if (collisionPointX <= (3 * partOfPaddle + paddleX)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (collisionPointX <= (4 * partOfPaddle + paddleX)) {
            return Velocity.fromAngleAndSpeed(30, speed);
        } else {
            return Velocity.fromAngleAndSpeed(60, speed);
        }

    }


    /**
     * Add this paddle to the game.
     *
     * @param g type Game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

}
