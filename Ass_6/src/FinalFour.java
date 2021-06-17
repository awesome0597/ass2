//322094111

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(315 + (i * 45), 5);
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Background(Color.CYAN);
    }

    /**
     * random color generator.
     *
     * @return type Color
     */
    private static Color getRandomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return (new Color(r, g, b));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int numOfCollums = 15;
        int numOfRows = 7;
        Point start = new Point(25, 300);
        double width = 50.25;
        double height = 20;
        for (int i = 0; i < numOfRows; i++) {
            Color random = getRandomColor();
            for (int j = 0; j < numOfCollums; j++) {
                blockList.add(new Block(
                        new Rectangle(new Point(start.getX() + (j * width), start.getY()),
                                51, 20, random), true));
            }
            start = new Point(start.getX(), start.getY() - height);
        }

        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
