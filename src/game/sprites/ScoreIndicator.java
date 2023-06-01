package game.sprites;

import auxiliary.Counter;
import biuoop.DrawSurface;
import game.Game;

/**
 * Displays the games' score.
 */
public class ScoreIndicator implements Sprite {
    private static final String TEXT = "Score: ";
    private static final int FONT_SIZE = 40;
    private static final int X = 390;
    private static final int Y = 60;
    private final Counter scoreCounter;

    /**
     * Constructs a new ScoreIndicator.
     * @param scoreCounter A Counter object representing the games' score
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(X, Y, TEXT + this.scoreCounter.getValue(), FONT_SIZE);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
