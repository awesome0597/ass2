import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * name: Adira Weiss.
 * id: 322094111
 * version 1.0.1
 * date: 22/4/21
 *
 * <p>
 * Class that creates a game.
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
        this.gui = new GUI("title", 800, 600);
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

//        int numOfRows = 6;
//        int numOfBlocks = 7;
//        int maxNumOfBlocks = 12;
//        double blockWidth = 50;
//        double blockHeight = 30;
//        double nextX = 800 - 20 - blockWidth;
//        double nextY = 300 - blockHeight;
//
//        // Create the pattern blocks and add them to game
//        for (int i = 0; i < numOfRows; i++) {
//            Color newColor = getRandomColor();
//            for (int j = 0; (j < numOfBlocks) && (numOfBlocks <= maxNumOfBlocks); j++) {
//                Rectangle newRect = new Rectangle(new Point(nextX, nextY), blockWidth, blockHeight);
//                newRect.setColor(newColor);
//                Block newBlock = new Block(newRect);
//                newBlock.addToGame(this);
//                nextX = nextX - blockWidth;
//            }
//            nextY = nextY - blockHeight;
//            numOfBlocks++;
//            nextX = 800 - 20 - blockWidth;
//        }
        addBorderBlock();
    }

    /**
     * adds Border Blocks to game.
     */
    public void addBorderBlock() {
        //add border blocks to GE
        Point one = new Point(0, 0);
        Point two = new Point(20, 580);
        Point three = new Point(780, 20);
        Point four = new Point(0, 20);
        List<Block> blockList = new ArrayList<>();
        blockList.add(new Block(new Rectangle(one, 800, 20, Color.GRAY)));
        blockList.add(new Block(new Rectangle(two, 760, 20, Color.GRAY)));
        blockList.add(new Block(new Rectangle(four, 20, 580, Color.GRAY)));
        blockList.add(new Block(new Rectangle(three, 20, 580, Color.GRAY)));
        for (Block x : blockList) {
            x.addToGame(this);
        }
    }


    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        Ball ball = new Ball(new Point(400, 500), 5, Color.MAGENTA);
        Velocity v = Velocity.fromAngleAndSpeed(180,5);
        ball.setVelocity(v);
        ball.addToGame(this);
        addObstacleBlock();
        Paddle paddle = new Paddle(new Rectangle(new Point(335, 560), 130, 20), this.gui);
        paddle.getCollisionRectangle().setColor(Color.YELLOW);
        paddle.addToGame(this);
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
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}