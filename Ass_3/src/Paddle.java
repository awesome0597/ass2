import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * name: Adira Weiss.
 * id: 322094111
 * version 2.0.1
 * date: 23/4/21
 *
 * <p>
 *     this class creates a paddle
 **/


public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;

    /**
     * constructor.
     *
     * @param rect type Rectangle
     * @param gui  type GUI
     */
    public Paddle(Rectangle rect, GUI gui) {
        this.rect = rect;
        this.keyboard = gui.getKeyboardSensor();

    }

    /**
     * Changes the upper left corner of rectangle to make it look like the paddle is moving left.
     */
    public void moveLeft() {
        if (this.rect.getUpperLeft().getX() - 5 > 20) {
            this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() - 5, this.rect.getUpperLeft().getY()));
        } else {
            this.rect.setUpperLeft(new Point(20, this.rect.getUpperLeft().getY()));
        }


    }

    /**
     * Changes the upper left corner of rectangle to make it look like the paddle is moving right.
     */
    public void moveRight() {
        if (this.rect.getUpperRight().getX() + 5 < 780) {
            this.rect.setUpperLeft(new Point(this.rect.getUpperLeft().getX() + 5, this.rect.getUpperLeft().getY()));
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
        d.setColor(Color.BLACK);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(this.rect.getColor());
        d.fillRectangle((int) this.rect.getUpperLeft().getX() + 1, (int) this.rect.getUpperLeft().getY() + 1,
                (int) this.rect.getWidth() - 2, (int) this.rect.getHeight() - 2);
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
     * @param collisionPoint  point of collision with the paddle
     * @param currentVelocity current velocity of the moving ball
     * @return type Velocity
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

        double paddleX = this.rect.getUpperLeft().getX();
        double collisionPointX = collisionPoint.getX();
        double paddlePart = (this.rect.getWidth() / 5 + paddleX);
        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        if (collisionPointX <= paddlePart) {
            return currentVelocity.fromAngleAndSpeed(300, speed);
        } else if (collisionPointX <= 2 * paddlePart) {
            return currentVelocity.fromAngleAndSpeed(330, speed);
        } else if (collisionPointX <= 3 * paddlePart) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (collisionPointX <= 4 * paddlePart) {
            return currentVelocity.fromAngleAndSpeed(30, speed);
        } else if (collisionPointX <= 5 * paddlePart) {
            return currentVelocity.fromAngleAndSpeed(60, speed);
        } else {
            return new Velocity(currentVelocity.getDx(), (-1) * currentVelocity.getDy());
        }

    }

    // Add this paddle to the game.

    /**
     * Add this paddle to the game.
     *
     * @param g type Game
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

}
