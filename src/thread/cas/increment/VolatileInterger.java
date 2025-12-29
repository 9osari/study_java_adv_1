package thread.cas.increment;

public class VolatileInterger implements IncrementInterger{

    private volatile int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
