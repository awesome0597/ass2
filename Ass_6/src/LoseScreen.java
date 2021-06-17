//322094111

import biuoop.DrawSurface;

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 17/06/21
 * Class that creates a game.
 * <p>
 * Draws the Lose Screen
 **/

public class LoseScreen implements Animation {
    private boolean stop;
    private int score;

    /**
     * constructor.
     *
     * @param s type String
     */
    public LoseScreen(int s) {
        this.stop = false;
        this.score = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
