package game.sprites;

import biuoop.DrawSurface;
import game.Game;

/**
 * Represents a "living" game object.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given draw surface.
     * @param d A DrawSurface object used in drawing the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * This method is responsible for bringing the sprite to life.
     */
    void timePassed();

    /**
     * Adds this sprite to the given game.
     * @param g A Game object representing a game
     */
    void addToGame(Game g);
}