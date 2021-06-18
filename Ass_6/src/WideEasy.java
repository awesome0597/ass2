//322094111

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 17/06/21
 * Class that creates a game.
 * <p>
 * Second level in game
 **/

public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(280 + (i * 16), 4);
            ballVelocities.add(v);
        }
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.WHITE);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            blockList.add(new Block(
                    new Rectangle(new Point(25 + i * 50.25, 200), 51, 20, Color.RED), true));
        }

        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
