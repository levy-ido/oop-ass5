package geometry;

import auxiliary.Double;

/**
 * Represents a point in a two-dimensional coordinate system.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Constructs a new Point object with the given x and y coordinates.
     *
     * @param x A double representing the x-coordinate of the new point
     * @param y A double representing the y-coordinate of the new point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and a given other point.
     *
     * @param other A Point object representing the point to calculate the distance to
     * @return A double representing the distance from this point to the other point
     */
    public double distance(Point other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns the x-coordinate of this point.
     * @return A double representing the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     * @return A double representing the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Compares this point with a given other point for equality. Two points are considered equal if their x and y
     * coordinates are exactly the same, up to a small difference.
     *
     * @param obj An Object representing the other point to compare to
     * @return true if the two points are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Point other = (Point) obj;
        return Double.areEqual(this.x, other.x) && Double.areEqual(this.y, other.y);
    }
}