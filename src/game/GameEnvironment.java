package game;

import game.collision.Collidable;
import game.collision.CollisionInfo;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the games' environment. Holds a collection of collidables.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Constructs a new GameEnvironment object.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds the given collidable to this game environment.
     *
     * @param c A Collidable object representing a given collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assuming an object is moving from trajectorys' start to trajectorys' end returns the closest point of collision
     * between the object and this game environments' collidables.
     *
     * @param trajectory A Line object representing the objects' trajectory
     * @return A CollisionInfo object holding information about the closest point of collision between the object and
     * this game environments' collidables. If there are no collision points on the given trajectory returns null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo minCollisionInfo = null;
        double minCollisionProximity = Double.POSITIVE_INFINITY;
        for (Collidable collidable : this.collidables) {
            Rectangle outline = collidable.getCollisionRectangle();
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(outline);
            if (collisionPoint == null) {
                continue;
            }
            double collisionProximity = trajectory.start().distance(collisionPoint);
            if (collisionProximity < minCollisionProximity) {
                minCollisionInfo = new CollisionInfo(collisionPoint, collidable);
                minCollisionProximity = collisionProximity;
            }
        }
        return minCollisionInfo;
    }

    /**
     * Returns this game environments' collidables.
     * @return A List of Collidables representing this game environments' collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
}
