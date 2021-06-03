public class Counter {
    private int counter;

    public Counter() {
        this.counter = 0;
    }

    // add number to current count.
    void increase(int number) {
        counter += number;
    }

    // subtract number from current count.
    void decrease(int number) {
        counter -= number;
    }

    // get current count.
    public int getValue() {
        return this.counter;
    }
}