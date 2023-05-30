package game.collision;

import geometry.Point;

import java.util.Comparator;

/**
 * Compares two points by their distance from a specific point.
 */
public class PointComparatorByRelativeDistance implements Comparator<Point> {
    private final Point p;
    /**
     * Constructs a new PointComparatorByRelativeDistance object with the given point.
     * @param p A Point object representing the point to compute distance to
     */
    public PointComparatorByRelativeDistance(Point p) {
        this.p = p;
    }
    @Override
    public int compare(Point o1, Point o2) {
        return java.lang.Double.compare(o1.distance(this.p), o2.distance(this.p));
    }
}
