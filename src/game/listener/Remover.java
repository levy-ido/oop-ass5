package game.listener;

import game.Game;
import auxiliary.Counter;

/**
 * A base class.
 */
public abstract class Remover implements HitListener {
    private final Game game;
    private final Counter counter;

    /**
     * Constructs a new Remover with the given game and counter.
     * @param game A Game object
     * @param counter A Counter object
     */
    public Remover(Game game, Counter counter) {
        this.game = game;
        this.counter = counter;
    }

    /**
     * Returns this removers' game.
     * @return A Game object representing this removers' game
     */
    public Game getGame() {
        return this.game;
    }
    /**
     * Returns this removers' counter.
     * @return A Counter object representing this removers' counter
     */
    public Counter getCounter() {
        return this.counter;
    }
}
