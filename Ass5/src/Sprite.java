//322094111

import biuoop.DrawSurface;

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 24/4/21
 * Class that creates a Sprite.
 * <p>
 * Sprites can be drawn on the screen, and can be notified that time has passed.
 **/
public interface Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d the drawsurface the sprites are drawn on.
     */
    void drawOn(DrawSurface d);


    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
