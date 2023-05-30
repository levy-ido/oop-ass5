package geometry;

import auxiliary.Double;
import game.collision.PointComparatorByRelativeDistance;
import auxiliary.Vector;

import java.util.List;

/**
 * Represents a line segment between two points.
 */
public class Line {
    private static final double COLLINEAR = -1.0;
    private static final double NO_INTERSECTION = -2.0;
    private final Point start;
    private final Point end;

    /**
     * Constructs a new Line object with a given start and end points.
     *
     * @param start A Point object representing the start of the new line segment
     * @param end   A Point object representing the end of the new line segment
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Line object with the specified start and end points.
     *
     * @param x1 A double representing the x-coordinate of the start point of the new line segment
     * @param y1 A double representing the y-coordinate of the start point of the new line segment
     * @param x2 A double representing the x-coordinate of the end point of the new line segment
     * @param y2 A double representing the y-coordinate of the end point of the new line segment
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Computes the value of t1 for the intersection point between this line segment and
     * the specified line segment.
     * Refer to this video for more details:
     * <a href="https://www.youtube.com/watch?v=5FkOO1Wwb8w">Line Segment Intersection</a>
     *
     * @param other A Line object representing the line segment to compute intersection with
     * @return A double representing the value of t1 for the intersection point, or COLLINEAR if the line segments are
     * collinear, or NO_INTERSECTION if the line segments do not intersect
     */
    private double computeT1(Line other) {
        Vector ab = new Vector(this.start, this.end);
        Vector cd = new Vector(other.start, other.end);
        if (ab.isLinearlyDependent(cd)) {
            return COLLINEAR;
        }
        Vector ac = new Vector(this.start, other.start);
        double t1 = ac.cross(cd) / ab.cross(cd);
        double t2 = -(ab.cross(ac) / ab.cross(cd));
        if (t1 < 0.0 || t1 > 1.0 || t2 < 0.0 || t2 > 1.0) {
            return NO_INTERSECTION;
        }
        return t1;
    }

    /**
     * An auxiliary method of intersectionWith. Returns the single point of intersection between this line segment and
     * a given line segment assuming these line segments are collinear.
     *
     * @param other A Line object representing the other line segment to check for intersection with
     * @return A Point object representing the two line segments single point of intersection if it exists, otherwise
     * null
     */
    private Point collinearIntersectionWith(Line other) {
        Vector u = new Vector(this.start, this.end);
        Vector v = new Vector(other.start, other.end);
        if (Double.areEqual(u.angle(), v.angle())) {
            if (this.end.equals(other.start)) {
                return this.end;
            }
            if (this.start.equals(other.end)) {
                return this.start;
            }
        } else {
            if (this.start.equals(other.start)) {
                return this.start;
            }
            if (this.end.equals(other.end)) {
                return this.end;
            }
        }
        return null;
    }

    /**
     * Returns the point of intersection between this line segment and another line segment.
     *
     * @param other A Line object representing the other line segment to compute intersection point with
     * @return A Point object representing the single point of intersection if it exists, otherwise returns null
     */
    public Point intersectionWith(Line other) {
        double t1 = this.computeT1(other);
        if (t1 == NO_INTERSECTION) {
            return null;
        }
        if (t1 != COLLINEAR) {
            double x = this.start.getX() + t1 * (this.end.getX() - this.start.getX());
            double y = this.start.getY() + t1 * (this.end.getY() - this.start.getY());
            return new Point(x, y);
        }
        return this.collinearIntersectionWith(other);
    }

    /**
     * Returns the intersection point between this line segment and a given rectangle closest to the start of this line
     * segment.
     *
     * @param rect A Rectangle object representing a given rectangle
     * @return A Point object representing the intersection point between this line segment and a given rectangle
     * closest to the start of this line segment if this line segment intersects the given rectangle, null otherwise
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        PointComparatorByRelativeDistance cmp = new PointComparatorByRelativeDistance(this.start);
        return intersectionPoints.stream().min(cmp).orElse(null);
    }

    /**
     * Returns this line segments' start.
     * @return A Point object representing the starting point of this line segment
     */
    public Point start() {
        return this.start;
    }

    /**
     * Checks whether this line segment is vertical.
     * @return true if this line segment is vertical, false otherwise
     */
    public boolean isVertical() {
        return Double.areEqual(this.start.getX(), this.end.getX());
    }

    /**
     * Returns this line segments' minimum y-coordinate.
     * @return A double representing this line segments' minimum y-coordinate
     */
    public double minY() {
        return Math.min(this.start.getY(), this.end.getY());
    }

    /**
     * Return this line segments' maximum y-coordinate.
     * @return A double representing this line segments' maximum y-coordinate
     */
    public double maxY() {
        return Math.max(this.start.getY(), this.end.getY());
    }

    /**
     * Returns this line segments' slope.
     * @return A double representing the slope of this line segment
     */
    public double slope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * Returns the y-intercept of this line segment.
     * @return A double representing the y-intercept of this line segment
     */
    public double intercept() {
        return this.start.getY() - this.slope() * this.start.getX();
    }

    /**
     * Returns this line segments' minimum x-coordinate.
     * @return A double representing this line segments' minimum x-coordinate
     */
    public double minX() {
        return Math.min(this.start.getX(), this.end.getX());
    }

    /**
     * Returns this line segments' maximum x-coordinate.
     * @return A double representing this line segments' maximum x-coordinate
     */
    public double maxX() {
        return Math.max(this.start.getX(), this.end.getX());
    }

    /**
     * Checks whether this line segment contains a given point.
     * @param p A Point object representing the given point
     * @return true if this line segment contains the given point, false otherwise
     */
    public boolean contains(Point p) {
        boolean doesInfiniteLineContainP, doesLineSegmentContainP;
        if (this.isVertical()) {
            doesInfiniteLineContainP = Double.areEqual(this.start.getX(), p.getX());
            doesLineSegmentContainP = !(p.getY() < this.minY() || p.getY() > this.maxY());
        } else {
            doesInfiniteLineContainP = Double.areEqual(this.slope() * p.getX() + this.intercept(), p.getY());
            doesLineSegmentContainP = !(p.getX() < this.minX() || p.getX() > this.maxX());
        }
        return doesInfiniteLineContainP && doesLineSegmentContainP;
    }
}