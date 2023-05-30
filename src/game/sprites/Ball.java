package game.sprites;

import java.awt.Color;

import auxiliary.Vector;
import auxiliary.Velocity;
import game.Game;
import game.GameEnvironment;
import game.collision.Collidable;
import game.collision.CollisionInfo;
import geometry.Line;
import geometry.Point;
import biuoop.DrawSurface;

/**
 * Represents a ball.
 */
public class Ball implements Sprite {
    private final int radius;
    private final Color color;
    private Point center;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a new Ball object with the given center, radius and color.
     *
     * @param center A Point object representing the center of the ball
     * @param radius An integer representing the radius of the ball
     * @param color  A Color object representing the color of the ball
     */
    public Ball(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    /**
     * Constructs a new Ball object with the given center coordinates, radius and color.
     *
     * @param x      A double representing the x-coordinate of the balls' center
     * @param y      A double representing the y-coordinate of the balls' center
     * @param radius An integer representing the radius of the ball
     * @param color  A Color object representing the color of the ball
     */
    public Ball(double x, double y, int radius, Color color) {
        this(new Point(x, y), radius, color);
    }

    /**
     * Sets this balls' velocity to a given velocity.
     *
     * @param dx A double representing the balls' horizontal speed
     * @param dy A double representing the balls' vertical speed
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns this balls' current trajectory.
     *
     * @return A Line object representing this balls' current trajectory
     */
    public Line trajectory() {
        Vector trajectoryVector = new Vector(this.velocity.getDx(), this.velocity.getDy());
        double trajectoryAngle = trajectoryVector.angle();
        double trajectoryEndX = this.center.getX() + Math.cos(trajectoryAngle) * this.radius;
        double trajectoryEndY = this.center.getY() + Math.sin(trajectoryAngle) * this.radius;
        return new Line(this.center.getX(), this.center.getY(), trajectoryEndX, trajectoryEndY);
    }

    /**
     * If the ball hits a Collidable, its velocity is reversed in the corresponding direction.
     * Moves the ball one step according to its velocity
     */
    public void moveOneStep() {
        Line trajectory = this.trajectory();
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            Point collisionPoint = collisionInfo.collisionPoint();
            Collidable collidedWith = collisionInfo.collisionObject();
            this.velocity = collidedWith.hit(this, collisionPoint, this.velocity);
        }
        this.center = this.velocity.applyToPoint(this.center);
    }

    /**
     * Sets this balls' game environment to the given game environment.
     *
     * @param gameEnvironment A GameEnvironment object representing a given game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle(this.getX(), this.getY(), this.radius);
        d.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Returns the x-coordinate of the balls' center.
     * @return An integer representing the x-coordinate of the balls' center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y-coordinate of the balls' center.
     * @return An integer representing the y-coordinate of the balls' center
     */
    public int getY() {
        return (int) this.center.getY();
    }
    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Removes this ball from the game.
     * @param g A Game object
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}