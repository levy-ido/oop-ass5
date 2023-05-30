package game.collision;

import geometry.Point;

/**
 * Holds information about a collision.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collidedWith;

    /**
     * Constructs a new CollisionInfo object with the given collision point and collidable.
     *
     * @param collisionPoint A Point object representing the point of collision
     * @param collidedWith   A Collidable representing the object the collision occurred with
     */
    public CollisionInfo(Point collisionPoint, Collidable collidedWith) {
        this.collisionPoint = collisionPoint;
        this.collidedWith = collidedWith;
    }

    /**
     * Returns the point of collision.
     *
     * @return A Point object representing the point of collision
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable involved in the collision.
     *
     * @return A Collidable representing the collidable involved in the collision
     */
    public Collidable collisionObject() {
        return this.collidedWith;
    }
}
