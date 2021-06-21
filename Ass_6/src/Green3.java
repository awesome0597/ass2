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
 * Fourth level in game
 **/

public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(315 + (i * 90), 5);
            ballVelocities.add(v);
        }
        return ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green3";
    }

    @Override
    public Sprite getBackground() {
        return new Background(new Color(153, 255, 153));
    }

    /**
     * random color generator.
     *
     * @return type Color
     */

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int numOfCollums = 7;
        int numOfRows = 6;
        Point start = new Point(425, 300);
        double width = 50;
        double height = 20;
        for (int i = 0; i < numOfRows; i++) {
            Color color = new Color(100,  36 * i, 100);
            for (int j = 0; j < numOfCollums; j++) {
                Block block = new Block(new Rectangle(
                        new Point(start.getX() + (width * j), start.getY()), width, height, color), true);
                blockList.add(block);
            }
            start = new Point(start.getX() - width, start.getY() - height);
            numOfCollums++;
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 57;
    }
}
