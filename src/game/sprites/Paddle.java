package game.sprites;

import game.Game;
import game.collision.Collidable;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import auxiliary.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Represents a keyboard controlled paddle.
 */
public class Paddle implements Sprite, Collidable {
    private static final double MOVE_LEN = 10.0;
    private final Rectangle outline;
    private final Color color;
    private final biuoop.KeyboardSensor keyboard;

    /**
     * Constructs a new paddle with the given outline and color.
     * @param outline A Rectangle object representing this paddles' outline
     * @param color A Color object representing this paddles' color
     * @param keyboard A KeyboardSensor object essential for moving the paddle
     */
    public Paddle(Rectangle outline, Color color, KeyboardSensor keyboard) {
        this.outline = outline;
        this.color = color;
        this.keyboard = keyboard;
    }

    /**
     * Constructs a new paddle with the given parameters.
     * @param x A double representing the paddles' outline upper left corner x-coordinate
     * @param y A double representing the paddles' outline upper left corner y-coordinate
     * @param width A double representing the paddles' outline width
     * @param height A double representing the paddles' outline height
     * @param color A Color object representing the paddles' color
     * @param keyboardSensor A KeyboardSensor object essential for moving the paddle
     */
    public Paddle(double x, double y, double width, double height, Color color, KeyboardSensor keyboardSensor) {
        this(new Rectangle(x, y, width, height), color, keyboardSensor);
    }

    /**
     * Moves this paddle the specified distance horizontally.
     * @param moveLen A double representing the distance to move the paddle by
     */
    private void move(double moveLen) {
        Point upperLeft = this.outline.getUpperLeft();
        this.outline.setUpperLeft(new Point(upperLeft.getX() + moveLen, upperLeft.getY()));
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        this.move(-MOVE_LEN);
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        this.move(MOVE_LEN);
    }
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        new Block(this.outline, this.color).drawOn(d);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.outline;
    }

    /**
     * Divides this paddles' top side into 5 equally long segments.
     * @return A Line array representing this paddles' top side divided into 5 equally long segments.
     */
    private Line[] segment() {
        Point upperLeft = this.outline.getUpperLeft();
        Line[] segments = new Line[5];
        double segmentLength = this.outline.getWidth() / 5;
        for (int i = 0; i < 5; ++i) {
            double segmentStartX = upperLeft.getX() + i * segmentLength;
            Point segmentStart = new Point(segmentStartX, upperLeft.getY());
            Point segmentEnd = new Point(segmentStartX + segmentLength, upperLeft.getY());
            segments[i] = new Line(segmentStart, segmentEnd);
        }
        return segments;

    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] segments = this.segment();
        for (int i = 0; i < 5; ++i) {
            if (i == 2) {
                continue;
            }
            if (segments[i].contains(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(-60.0 + i * 30.0, currentVelocity.speed());
            }
        }
        return new Block(this.outline, this.color).hit(hitter, collisionPoint, currentVelocity);
    }
    @Override
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
