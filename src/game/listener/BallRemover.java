package game.listener;

import auxiliary.Counter;
import game.Game;
import game.sprites.Ball;
import game.sprites.Block;
import geometry.Rectangle;

/**
 * Removes balls from the game and counts remaining balls.
 */
public class BallRemover extends Remover {

    /**
     * Constructs a new BallRemover.
     * @param game A Game object
     * @param removedBalls A Counter object representing the number of balls removed
     */
    public BallRemover(Game game, Counter removedBalls) {
        super(game, removedBalls);
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        Rectangle beingHitOutline = beingHit.getCollisionRectangle();
        Rectangle bottomBorderOutline = new Rectangle(20.0, 580.0, 760.0, 20.0);
        if (beingHitOutline.equals(bottomBorderOutline)) {
            hitter.removeFromGame(super.getGame());
            super.getCounter().increase(1);
        }
    }
}
