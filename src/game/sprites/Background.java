package game.sprites;

import game.Game;
import geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Represents a games' background.
 */
public class Background implements Sprite {
    private final Rectangle outline;
    private final Color color;

    /**
     * Constructs a new background with the given parameters.
     * @param x A double representing the backgrounds' outline upper left corner x-coordinate
     * @param y A double representing the backgrounds' outline upper left corner y-coordinate
     * @param width A double representing the backgrounds' width
     * @param height A double representing the backgrounds' height
     * @param color A Color object representing the backgrounds' color
     */
    public Background(double x, double y, double width, double height, Color color) {
        this.outline = new Rectangle(x, y, width, height);
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.outline.getUpperLeft().getX();
        int y = (int) this.outline.getUpperLeft().getY();
        int width = (int) this.outline.getWidth();
        int height = (int) this.outline.getHeight();
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
