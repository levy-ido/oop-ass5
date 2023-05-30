package auxiliary;

/**
 * This class provides a method for checking the equality of two double values
 * within a certain tolerance.
 */
public class Double {
    /**
     * The tolerance value used to determine if two double values are equal.
     */
    private static final double TOLERANCE = 1E-5;

    /**
     * Returns true if the absolute difference between the two specified double
     * values is less than the tolerance value.
     *
     * @param a The first double value to compare
     * @param b The second double value to compare
     * @return true if the absolute difference between a and b is less than the tolerance value, false otherwise
     */
    public static boolean areEqual(double a, double b) {
        return Math.abs(a - b) < TOLERANCE;
    }
}