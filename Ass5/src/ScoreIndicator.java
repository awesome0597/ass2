//322094111

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 04/06/21
 * Class that creates a ScoreIndicator.
 * <p>
 * Class that creates a ScoreIndicator that implemets the Sprite interface., its members are a score. It is in charge of
 * showing the score on the screen.
 **/

public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * constructor.
     *
     * @param s type Counter.
     */
    ScoreIndicator(Counter s) {
        this.score = s;
    }

    /**
     * adds this sprite to game.
     *
     * @param game type Game
     */
    public void addToGame(Game game) {
        game.addSprite(this);

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(350, 15,
                "score:" + score.getValue(), 15);
    }

    @Override
    public void timePassed() {
        return;
    }
}
