import biuoop.DrawSurface;
/**
 * name: Adira Weiss.
 * id: 322094111
 * version 2.0.1
 * date: 23/4/21
 *
 * <p>
 *  Sprites can be drawn on the screen, and can be notified that time has passed.
 **/
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d the drawsurface the sprites are drawn on.
     */
    void drawOn(DrawSurface d);


    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
