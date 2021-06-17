//322094111

import biuoop.DrawSurface;


/**
 * @author Adira Weiss.
 * @version 2.0.1
 * @since: 13/06/21
 * Class that creates a game.
 * <p>
 * Animation that creates a pause screen
 **/


public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
