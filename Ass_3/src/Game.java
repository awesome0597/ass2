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
 * @version 1.0.1
 * @since: 27/4/21
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

    /**
     * constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("blah blah blah, joke joke joke, commentary.", 800, 600);
        this.sleeper = new Sleeper();
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
     */
    public void addObstacleBlock() {
        int numOfCollums = 7;
        int numOfRows = 6;
        Point start = new Point(430, 300);
        double width = 50;
        double height = 20;
        for (int i = 0; i < numOfRows; i++) {
            Color random = getRandomColor();
            for (int j = 0; j < numOfCollums; j++) {
                Block block = new Block(new Rectangle(
                        new Point(start.getX() + (width * j), start.getY()), width, height, random));
                block.addToGame(this);
            }
            start = new Point(start.getX() - width, start.getY() - height);
            numOfCollums++;
        }
        addBorderBlock();
    }

    /**
     * adds Border Blocks to game.
     */
    public void addBorderBlock() {
        //add border blocks to GE
        double widthTop = gui.getDrawSurface().getWidth();
        double widthSides = 20;
        double widthBottom = widthTop - 2 * widthSides;
        double heightSides = gui.getDrawSurface().getHeight() - widthSides;
        Point one = new Point(0, 0);
        Point two = new Point(20, 580);
        Point three = new Point(780, 20);
        Point four = new Point(0, 20);
        List<Block> blockList = new ArrayList<>();
        blockList.add(new Block(new Rectangle(two, widthBottom, widthSides, Color.GRAY)));
        blockList.add(new Block(new Rectangle(four, widthSides, heightSides, Color.GRAY)));
        blockList.add(new Block(new Rectangle(three, widthSides, heightSides, Color.GRAY)));
        blockList.add(new Block(new Rectangle(one, widthTop, widthSides, Color.GRAY)));


        for (Block x : blockList) {
            x.addToGame(this);
        }
    }


    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        Ball ball1 = new Ball(new Point(775, 25), 5, Color.MAGENTA);
//        Velocity v1 = Velocity.fromAngleAndSpeed(45,5);
        Velocity v1 = new Velocity(-4, 4);
        ball1.setVelocity(v1);
        ball1.addToGame(this);
        Ball ball2 = new Ball(new Point(760, 560), 5, Color.MAGENTA);
//        Velocity v2 = Velocity.fromAngleAndSpeed(45,5);
        Velocity v2 = new Velocity(4, 4);
        ball2.setVelocity(v2);
        ball2.addToGame(this);
        Paddle paddle = new Paddle(new Rectangle(new Point(335, 560), 130, 20), this.gui);
        paddle.getCollisionRectangle().setColor(Color.YELLOW);
        paddle.addToGame(this);
        addObstacleBlock();
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
        }
    }

    /**
     * main, creates a new game and calls initialize and run methods.
     *
     * @param args not received
     */

}