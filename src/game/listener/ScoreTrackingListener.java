package game.listener;

import game.sprites.Ball;
import game.sprites.Block;
import auxiliary.Counter;

/**
 * Keeps track of score.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructs a new ScoreTrackingListener with the given counter.
     * @param scoreCounter A Counter object representing the games' score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
