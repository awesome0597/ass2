// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = game.getRemainingblocks();
        this.remainingBlocks.decrease(removedBlocks.getValue());
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        int a = this.game.getEnvironment().getListOfCollidables().size() - 1;
        for (int i = a; i > a - 4; i--) {
            if (this.game.getEnvironment().getListOfCollidables().get(i) == beingHit) {
                return;
            }
        }
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
    }
}