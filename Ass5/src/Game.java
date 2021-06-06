//322094111

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Adira Weiss.
 * @version 2.0.1
 * @since: 04/06/21
 * Class that creates a game.
 * <p>
 * Class that creates a game, its members are a game, sprite, GUI and sleeper. It initializes the game, adds all the
 * components that make it up (ball, blocks and paddle), and then runs them.
 **/

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter remainingblocks;
    private Counter remainingballs;
    private Counter score;

    /**
     * constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("blah blah blah, joke joke joke, commentary.", 800, 600);
        this.sleeper = new Sleeper();
        this.remainingblocks = new Counter();
        this.remainingballs = new Counter();
        this.score = new Counter();
    }

    /**
     * accessor.
     *
     * @return type GameEnvironment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * accessor.
     *
     * @return type SpriteCollection
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * accessor.
     *
     * @return type Counter
     */
    public Counter getRemainingblocks() {
        return this.remainingblocks;
    }

    /**
     * accessor.
     *
     * @return type Counter
     */
    public Counter getRemainingballs() {
        return this.remainingballs;
    }

    /**
     * accessor.
     *
     * @return type Counter
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * adds collidable to list of collidables.
     *
     * @param c type Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * adds sprite to Sprites.
     *
     * @param s type Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes Collidable from game environment.
     *
     * @param c type Collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.getListOfCollidables().remove(c);
    }

    /**
     * Removes Sprite from list of sprites in game.
     *
     * @param s type Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.getListOfSprites().remove(s);
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

    /**
     * adds Obstacle Blocks to game.
     *
     * @param br  type BlockRemover
     * @param bl  type BallRemover
     * @param stl type ScoreTrackingListener
     */
    public void addObstacleBlock(BlockRemover br, BallRemover bl, ScoreTrackingListener stl) {
        int numOfCollums = 7;
        int numOfRows = 6;
        Point start = new Point(430, 300);
        double width = 50;
        double height = 20;
        for (int i = 0; i < numOfRows; i++) {
            Color random = getRandomColor();
            for (int j = 0; j < numOfCollums; j++) {
                Block block = new Block(new Rectangle(
                        new Point(start.getX() + (width * j), start.getY()), width, height, random), br, true);
                block.addToGame(this);
                this.remainingblocks.increase(1);
                block.addHitListener(stl);
            }
            start = new Point(start.getX() - width, start.getY() - height);
            numOfCollums++;
        }
        addBorderBlock(br, bl);
    }

    /**
     * adds Border Blocks to game.
     *
     * @param br type BlockRemover
     * @param bl type BallRemover
     */
    public void addBorderBlock(BlockRemover br, BallRemover bl) {
        //add border blocks to GE
        double widthTop = gui.getDrawSurface().getWidth();
        double widthSides = 20;
        double widthBottom = widthTop - 2 * widthSides;
        double heightSides = gui.getDrawSurface().getHeight() - widthSides - 20;
        Point one = new Point(0, 20);
        Point two = new Point(20, 600);
        Point three = new Point(780, 40);
        Point four = new Point(0, 40);
        List<Block> blockList = new ArrayList<>();
        //death region
        blockList.add(new Block(new Rectangle(two, widthBottom, widthSides, Color.GRAY), br, false));
        //left border
        blockList.add(new Block(new Rectangle(four, widthSides, heightSides, Color.GRAY), br, false));
        //right border
        blockList.add(new Block(new Rectangle(three, widthSides, heightSides, Color.GRAY), br, false));
        //top border
        blockList.add(new Block(new Rectangle(one, widthTop, widthSides, Color.GRAY), br, false));

        for (Block x : blockList) {
            x.addToGame(this);
        }

        blockList.get(0).addHitListener(bl);
    }

    /**
     * adds Balls to game.
     */
    public void addBalls() {
        Ball ball1 = new Ball(new Point(600, 560), 5, Color.MAGENTA);
        Velocity v1 = Velocity.fromAngleAndSpeed(45, 5);
        ball1.setVelocity(v1);
        ball1.addToGame(this);
        this.remainingballs.increase(1);
        Ball ball2 = new Ball(new Point(650, 555), 5, Color.MAGENTA);
        Velocity v2 = Velocity.fromAngleAndSpeed(45, 4);
        ball2.setVelocity(v2);
        ball2.addToGame(this);
        this.remainingballs.increase(1);
        Ball ball3 = new Ball(new Point(600, 550), 5, Color.MAGENTA);
        ball3.setVelocity(v1);
        ball3.addToGame(this);
        this.remainingballs.increase(1);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        BlockRemover br = new BlockRemover(this, this.remainingblocks);
        BallRemover bl = new BallRemover(this, this.remainingballs);
        ScoreTrackingListener stl = new ScoreTrackingListener(this.score);
        addBalls();
        Paddle paddle = new Paddle(new Rectangle(new Point(335, 560), 130, 20), this.gui);
        paddle.getCollisionRectangle().setColor(Color.YELLOW);
        paddle.addToGame(this);
        addObstacleBlock(br, bl, stl);
        ScoreIndicator si = new ScoreIndicator(this.score);
        si.addToGame(this);
    }

    /**
     * Runs the game by start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            if (this.remainingblocks.getValue() == 0 || this.remainingballs.getValue() == 0) {
                if (this.remainingblocks.getValue() == 0) {
                    this.score.increase(100);
                }
                gui.close();
                return;
            }
        }
    }

}