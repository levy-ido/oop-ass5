package game.collision;

import geometry.Point;
import geometry.Rectangle;
import auxiliary.Velocity;
import game.sprites.Ball;

/**
 * Defines the behavior of collidable objects.
 */
public interface Collidable {
    /**
     * Returns a rectangle representing this collidables' outline.
     *
     * @return A Rectangle object representing this collidables' outline
     */
    Rectangle getCollisionRectangle();

    /**
     * Returns an updated velocity based on a collision point and a collision velocity.
     * If this collidable is a HitNotifier, notifies all listeners a hit occurred.
     *
     * @param hitter A Ball object representing the ball that hit this collidable
     * @param collisionPoint  A Point object representing the collision point
     * @param currentVelocity A Velocity object representing the collision velocity
     * @return A new Velocity object representing the updated velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}