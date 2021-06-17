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

    /**
     * number of balls in level.
     *
     * @return type int
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return type List<Velocity>
     */
    List<Velocity> initialBallVelocities();

    /**
     * speed of paddle in level.
     *
     * @return type int
     */
    int paddleSpeed();

    /**
     * width of paddle in level.
     *
     * @return type int
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return type String
     */
    String levelName();


    /**
     * Returns a sprite with the background of the level.
     *
     * @return type Sprite
     */
    Sprite getBackground();


    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return type List<Block>
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return type int
     */
    int numberOfBlocksToRemove();
}