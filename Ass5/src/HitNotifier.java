//322094111

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 04/06/21
 * Interface that creates a HitNotifier.
 * <p>
 * Interface that creates a HitNotifier.
 **/

public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl type HitListener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl type HitListener
     */
    void removeHitListener(HitListener hl);
}
