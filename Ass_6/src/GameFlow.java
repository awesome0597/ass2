//322094111


import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI g) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.gui = g;
    }

    public void runLevels(List<LevelInformation> levels) {
        // ...
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
                level.getRunner().run(new KeyPressStoppableAnimation(this.keyboardSensor, "space" ,new LoseScreen(score)));
                return;
            }

        }
        new AnimationRunner(this.gui).run(new KeyPressStoppableAnimation(this.keyboardSensor, "space" ,new WinScreen(score)));


    }
}
