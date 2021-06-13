//322094111

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 04/06/21
 * Interface that creates a HitListener.
 * <p>
 * Interface that creates a HitListener.
 **/
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit type Block
     * @param hitter type Ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
