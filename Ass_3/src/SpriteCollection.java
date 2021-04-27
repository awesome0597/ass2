import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 2.0.1
 * date: 23/4/21
 *
 * <p>
 *     Sprite Collection holds a list of all the sprites used in the game. it can add a sprite to the list, draws them
 *     all on the board, and invokes the method for each sprite that allows it to move.
 **/

public class SpriteCollection {
    private List<Sprite> listOfSprites;

    /**
     * constructor.
     *
     */
    public SpriteCollection() {
        this.listOfSprites = new ArrayList<Sprite>();
    }

    /**
     * adds sprite to the list of sprites.
     *
     * @param s the sprite added to the list.
     */
    public void addSprite(Sprite s) {
        this.listOfSprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite x : listOfSprites) {
            x.timePassed();
        }
    }



    /**
     * call drawOn(d) on all sprites.
     * @param d the drawsurface the sprites are drawn on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite x : listOfSprites) {
            x.drawOn(d);
        }
    }
}
