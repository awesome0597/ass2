//322094111

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 17/06/21
 * Class that creates a game.
 * <p>
 * Stops animation screen
 **/

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private boolean stop;
    private String key;
    private boolean isAlreadyPressed;
    private Animation animation;

    /**
     * constructor.
     *
     * @param sensor    type KeyboardSensor
     * @param key       type String
     * @param animation type Animation
     */

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.stop = false;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.sensor.isPressed(key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
