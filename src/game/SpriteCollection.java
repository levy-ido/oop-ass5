package game;

import biuoop.DrawSurface;
import game.sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the games' sprites.
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Constructs a new SpriteCollection object.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds the given sprite to this sprite collection.
     * @param s A Sprite representing the sprite to add to the collection
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * Animates all the sprites in this sprite collection.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : new ArrayList<>(this.sprites)) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all the sprites in this sprite collection on the given draw surface.
     * @param d A DrawSurface object used in drawing the sprites of this sprite collection
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * Returns this sprite collections' sprites.
     * @return A List of Sprites representing this sprite collections' sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
}