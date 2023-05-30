package game.listener;

import game.sprites.Ball;
import game.sprites.Block;

/**
 * Objects implementing this interface listen for hit events and act accordingly.
 */
public interface HitListener {
    /**
     * This method defines this hit listeners' behavior when it is notified of a hit event.
     * @param beingHit A Block object representing the block that was hit
     * @param hitter A Ball object representing the ball that hit the above block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
