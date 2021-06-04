//322094111

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 04/06/21
 * Class that creates a Listener to keep track of the balls.
 * <p>
 * Class that removes balls from the game that implements the HitListener. Its members are game and remaining
 * balls. It is updated everytime a ball hit the 'death region' and removes it from the game.
 **/

public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game type Game
     * @param removedBalls type Counter.
     */
    public BallRemover(Game game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = game.getRemainingballs();
        this.remainingBalls.decrease(removedBalls.getValue());
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.remainingBalls.decrease(1);
    }
}
