package auxiliary;

/**
 * Why not just use int?
 */
public class Counter {
    private int value;

    /**
     * Constructs a new Counter with the given value.
     * @param value An integer representing the new counters' value
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Increases this counters' value by number.
     * @param number An integer representing the number to increase this counters' value by
     */
    public void increase(int number) {
        this.value += number;
    }
    /**
     * Decreases this counters' value by number.
     * @param number An integer representing the number to Decrease this counters' value by
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Returns this counters' value.
     * @return An integer representing this counters' value
     */
    public int getValue() {
        return this.value;
    }
}
