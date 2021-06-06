//322094111

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 04/06/21
 * Class that creates a Listener to keep track of the blocks.
 * <p>
 * Class that is in charge of removing blocks from the game, as well as keeping count of the number of blocks that
 * remain.
 **/

public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param game          type Game
     * @param removedBlocks type Counter
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = game.getRemainingblocks();
        this.remainingBlocks.decrease(removedBlocks.getValue());
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        int a = this.game.getEnvironment().getListOfCollidables().size() - 1;
        for (int i = a; i > a - 4; i--) {
            if (this.game.getEnvironment().getListOfCollidables().get(i) == beingHit) {
                return;
            }
        }
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
        this.remainingBlocks.decrease(1);
    }
}