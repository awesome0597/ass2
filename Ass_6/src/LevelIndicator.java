//322094111

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 17/06/21
 * Class that creates a game.
 * <p>
 * draws level name on top right of screen
 **/

public class LevelIndicator implements Sprite {
    private String levelName;

    /**
     * constructor.
     *
     * @param s type string
     */
    public LevelIndicator(String s) {
        this.levelName = s;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(600, 15,
                "Level Name: " + levelName, 15);
    }

    @Override
    public void timePassed() {

    }
}
