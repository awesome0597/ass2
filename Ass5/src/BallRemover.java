public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    public BallRemover(Game game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = game.getRemainingballs();
        this.remainingBalls.decrease(removedBalls.getValue());
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
    }
}
