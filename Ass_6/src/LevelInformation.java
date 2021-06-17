//322094111

import java.util.List;


/**
 * @author Adira Weiss.
 * @version 2.0.1
 * @since: 04/06/21
 * Class that creates a game.
 * <p>
 * Class that creates a game, its members are a game, sprite, GUI and sleeper. It initializes the game, adds all the
 * components that make it up (ball, blocks and paddle), and then runs them.
 **/
public interface LevelInformation {
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    List<Velocity> initialBallVelocities();
    int paddleSpeed();
    int paddleWidth();
    // the level name will be displayed at the top of the screen.
    String levelName();
    // Returns a sprite with the background of the level
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}