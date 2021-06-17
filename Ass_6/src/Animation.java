//322094111

import biuoop.DrawSurface;

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 13/06/21
 * Interface that creates the Template method.
 * <p>
 **/
public interface Animation {
    /**
     * runs the animation loop and ends it when player wins/loses.
     *
     * @param d type DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * changes the status of boolean
     *
     * @return type boolean
     */
    boolean shouldStop();
}
