package game.sprites;

import game.Game;
import game.listener.HitListener;
import game.listener.HitNotifier;
import game.collision.Collidable;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import auxiliary.Velocity;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle outline;
    private final Color color;
    private final List<HitListener> hitListeners;

    /**
     * Constructs a new Block object with the given outline and color.
     *
     * @param outline A Rectangle object representing the new blocks' outline
     * @param color A Color object representing the new blocks' color
     */
    public Block(Rectangle outline, Color color) {
        this.outline = outline;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructs a new Block object with the given parameters.
     *
     * @param x      A double representing the blocks' outline upper left corner x-coordinate
     * @param y      A double representing the blocks' outline upper left corner y-coordinate
     * @param width  A double representing the blocks' outline width
     * @param height A double representing the blocks' outline height
     * @param color A Color object representing the blocks' color
     */
    public Block(double x, double y, double width, double height, Color color) {
        this(new Rectangle(x, y, width, height), color);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.outline;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] sides = this.outline.getSides();
        double dx = currentVelocity.getDx();
        if (sides[0].contains(collisionPoint) || sides[2].contains(collisionPoint)) {
            dx = -dx;
        }
        double dy = currentVelocity.getDy();
        if (sides[1].contains(collisionPoint) || sides[3].contains(collisionPoint)) {
            dy = -dy;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.outline.getUpperLeft().getX();
        int y = (int) this.outline.getUpperLeft().getY();
        int width = (int) this.outline.getWidth();
        int height = (int) this.outline.getHeight();
        d.setColor(color);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Removes this block from the given game.
     * @param game A Game object to remove this block from
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all of this blocks' hit listeners the given ball hit this block.
     * @param hitter A Ball object representing the ball that hit this block
     */
    private void notifyHit(Ball hitter) {
        for (HitListener hl : this.hitListeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
