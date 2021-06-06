//322094111

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 04/06/21
 * Class that creates a Listener to keep track of the score.
 * <p>
 * Class that keeps track of the score in the game that implemets the Hitlistener. Its members is the current score.
 * It is updated everytime a removable block is hit.
 **/
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter type Counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}

