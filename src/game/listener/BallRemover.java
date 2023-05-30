package game.listener;

import auxiliary.Counter;
import game.Game;
import game.sprites.Ball;
import game.sprites.Block;
import geometry.Rectangle;

/**
 * Removes balls from the game.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter removedBalls;

    /**
     * Constructs a new BallRemover.
     * @param game A Game object
     * @param removedBalls A Counter object representing the number of balls removed
     */
    public BallRemover(Game game, Counter removedBalls) {
        this.game = game;
        this.removedBalls = removedBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        Rectangle beingHitColRect = beingHit.getCollisionRectangle();
        Rectangle bottomBorder = new Rectangle(20.0, 580.0, 760.0, 20.0);
        if (beingHitColRect.equals(bottomBorder)) {
            hitter.removeFromGame(this.game);
            this.removedBalls.increase(1);
        }
    }
}
