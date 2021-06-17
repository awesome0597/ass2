//322094111


import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 17/06/21
 * Class that creates a game.
 * <p>
 * runs the entire game and changes the levels when needed.
 **/

public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;

    /**
     * constructor.
     *
     * @param ar type AnimationRunner
     * @param ks type KeyboardSensor
     * @param g  type GUI
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI g) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.gui = g;
    }

    /**
     * runs all the levels in the game.
     *
     * @param levels type List<LevelInformation>
     */
    public void runLevels(List<LevelInformation> levels) {
        int score = 0;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, gui);
            level.getScore().increase(score);
            level.initialize();

            while (level.getRemainingballs().getValue() != 0 && level.getRemainingblocks().getValue() != 0) {
                level.run();
            }

            score = level.getScore().getValue();

            if (level.getRemainingballs().getValue() == 0) {
                level.getRunner().run(
                        new KeyPressStoppableAnimation(this.keyboardSensor, "space", new LoseScreen(score)));
                return;
            }

        }
        new AnimationRunner(this.gui).run(
                new KeyPressStoppableAnimation(this.keyboardSensor, "space", new WinScreen(score)));


    }
}
