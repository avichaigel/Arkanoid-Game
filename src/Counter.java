/**
 * The type Counter.
 */
public class Counter {

    private int count;

    /**
     * Instantiates a new Counter.
     *
     * @param count the count
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * Increase count.
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decrease count.
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Gets value.
     * get current count.
     *
     * @return the value
     */
    public int getValue() {
        return this.count;
    }
}