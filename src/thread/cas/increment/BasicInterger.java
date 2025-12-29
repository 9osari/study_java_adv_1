package thread.cas.increment;

public class BasicInterger implements IncrementInterger{

    private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
