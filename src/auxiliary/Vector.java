package auxiliary;

import geometry.Point;

/**
 * Representing a 2D vector.
 */
public class Vector {
    private final double x;
    private final double y;

    /**
     * Constructs a new Vector object with the given x and y components.
     *
     * @param x A double representing the x-component of the new vector
     * @param y A double representing the y-component of the new vector
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new Vector object representing the vector pointing from p to q.
     *
     * @param p A Point object representing a given point
     * @param q A Point object representing another given point
     */
    public Vector(Point p, Point q) {
        this(q.getX() - p.getX(), q.getY() - p.getY());
    }

    /**
     * Calculates the cross product of this vector and another given vector.
     *
     * @param v A Vector object representing a given vector
     * @return A double representing the cross product of this vector and the given vector
     */
    public double cross(Vector v) {
        return this.x * v.y - v.x * this.y;
    }

    /**
     * Returns true if this vector is linearly dependent on the specified vector.
     *
     * @param v A Vector object representing the vector to compare with this vector
     * @return true if this vector is linearly dependent on the specified vector, false otherwise
     */
    public boolean isLinearlyDependent(Vector v) {
        return Double.areEqual(this.cross(v), 0.0);
    }

    /**
     * Returns this vectors' angle relative to the positive x-axis.
     * @return A double representing the angle in radians between the positive x-axis and this vector when placed at
     * the origin
     */
    public double angle() {
        return Math.atan2(this.y, this.x);
    }
}