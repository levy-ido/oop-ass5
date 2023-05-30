package game.listener;

import game.sprites.Ball;
import auxiliary.Counter;
import game.Game;
import game.sprites.Block;

/**
 * This class is in charge of removing blocks from the game, as well as keeping count of the blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private Counter removedBlocks;

    /**
     * Constructs a new BlockRemover with the given game and counter.
     * @param game A Game object
     * @param removedBlocks A Counter object representing the number of blocks removed
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.removedBlocks = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        this.removedBlocks.increase(1);
    }
}
