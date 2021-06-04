//322094111

/**
 * @author Adira Weiss.
 * @version 1.0.1
 * @since: 04/06/21
 * Class that creates a Counter.
 * <p>
 * Class that is in charge of counting anything. Its member is a counter. The value of counter can be accessed,
 * increased or decreased.
 **/
public class Counter {
    private int counter;

    /**
     * constructor.
     *
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number type int
     */
    void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number type int
     */
    void decrease(int number) {
        counter = counter - number;
    }

    /**
     * get current count.
     *
     * @return type int
     */
    public int getValue() {
        return this.counter;
    }
}