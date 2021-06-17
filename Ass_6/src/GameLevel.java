//322094111

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
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

public class GameLevel implements Animation {
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter remainingblocks;
    private Counter remainingballs;
    private Counter score;
    private LevelInformation levelInformation;

    /**
     * constructor.
     */
    public GameLevel(LevelInformation levelInformation) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("blah blah blah, joke joke joke, commentary.", 800, 600);
        this.sleeper = new Sleeper();
        this.remainingblocks = new Counter();
        this.remainingballs = new Counter();
        this.score = new Counter();
        this.keyboard = this.gui.getKeyboardSensor();
        this.runner = new AnimationRunner(gui);
        this.running = true;
        this.levelInformation = levelInformation;
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
     * adds Obstacle Blocks to game.
     *
     * @param br  type BlockRemover
     * @param bl  type BallRemover
     * @param stl type ScoreTrackingListener
     */
    public void addObstacleBlock(BlockRemover br, BallRemover bl, ScoreTrackingListener stl) {

        for (Block x : this.levelInformation.blocks()) {
            x.addToGame(this);
            x.addHitListener(br);
            x.addHitListener(stl);
        }
        this.remainingblocks.increase(this.levelInformation.numberOfBlocksToRemove());
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
        double widthSides = 25;
        double widthBottom = widthTop - 2 * widthSides;
        double heightSides = gui.getDrawSurface().getHeight() - widthSides - 20;
        Point one = new Point(0, 20);
        Point two = new Point(20, 600);
        Point three = new Point(780, 45);
        Point four = new Point(0, 45);
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
//        Ball ball1 = new Ball(new Point(600, 560), 5, Color.MAGENTA);
//        Velocity v1 = Velocity.fromAngleAndSpeed(45, 5);
//        ball1.setVelocity(v1);
//        ball1.addToGame(this);
//        this.remainingballs.increase(1);
//        Ball ball2 = new Ball(new Point(650, 555), 5, Color.MAGENTA);
//        Velocity v2 = Velocity.fromAngleAndSpeed(45, 4);
//        ball2.setVelocity(v2);
//        ball2.addToGame(this);
//        this.remainingballs.increase(1);
//        Ball ball3 = new Ball(new Point(600, 550), 5, Color.MAGENTA);
//        ball3.setVelocity(v1);
//        ball3.addToGame(this);
//        this.remainingballs.increase(1);

        for (Velocity x : this.levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(new Point(400, 545), 6, Color.MAGENTA);
            ball.setVelocity(x);
            ball.addToGame(this);
        }
        this.remainingballs.increase(this.levelInformation.numberOfBalls());
    }

    public void addPaddle(){
        double pWidth = this.levelInformation.paddleWidth();
        Paddle paddle = new Paddle(new Rectangle(new Point(400 - (pWidth / 2), 560),
                pWidth, 20), this.gui, this.levelInformation.paddleSpeed());
        paddle.getCollisionRectangle().setColor(Color.YELLOW);
        paddle.addToGame(this);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        BlockRemover br = new BlockRemover(this, this.remainingblocks);
        BallRemover bl = new BallRemover(this, this.remainingballs);
        ScoreTrackingListener stl = new ScoreTrackingListener(this.score);
        //add background
        this.sprites.addSprite(this.levelInformation.getBackground());
        //add level indicator
        LevelIndicator li = new LevelIndicator(this.levelInformation.levelName());
        this.sprites.addSprite(li);
        //create and add paddle
        addPaddle();
        //adds all blocks to game obstacle and border.
        addObstacleBlock(br, bl, stl);
        ScoreIndicator si = new ScoreIndicator(this.score);
        si.addToGame(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        if (this.remainingblocks.getValue() == 0 || this.remainingballs.getValue() == 0) {
            if (this.remainingblocks.getValue() == 0) {
                this.score.increase(100);
            }
            gui.close();
            this.running = false;
        }

    }


    /**
     * Runs the game by start the animation loop.
     */
    public void run() {
        addBalls();
        this.running = true;
        this.runner.run(this);

    }
}