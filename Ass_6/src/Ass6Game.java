//322094111

import biuoop.KeyboardSensor;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * blah blah blah, joke joke joke
 * commentary.
 */
public class Ass6Game {

    /**
     * main that starts and runs game.
     *
     * @param args not received for this main
     */
    public static void main(String[] args) {
        GUI gui = new GUI("blah blah blah, joke joke joke, commentary.", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui), ks, gui);
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());
        levels.add(new FinalFour());

        gameFlow.runLevels(levels);

        gui.close();


    }
}
