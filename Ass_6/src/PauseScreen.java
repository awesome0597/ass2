//322094111

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Adira Weiss.
 * @version 2.0.1
 * @since: 13/06/21
 * Class that creates a game.
 * <p>
 * Class that creates a game, its members are a game, sprite, GUI and sleeper. It initializes the game, adds all the
 * components that make it up (ball, blocks and paddle), and then runs them.
 **/



public class PauseScreen implements Animation {
    private boolean stop;
    public PauseScreen() {
        this.stop = false;
    }
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    public boolean shouldStop() { return this.stop; }
}
