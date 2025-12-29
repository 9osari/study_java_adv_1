package thread.cas.increment;

public class IncrementPerformanceMain {

    public static final long COUNT = 100_000_000;

    public static void main(String[] args) {
        test(new BasicInterger());
        test(new VolatileInterger());
        test(new SyncInterger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInterger incrementInterger) {
        long startMs = System.currentTimeMillis();

        for(long i = 0; i < COUNT; i++) {
            incrementInterger.increment();
        }

        long endMs = System.currentTimeMillis();
        System.out.println(incrementInterger.getClass().getSimpleName() + " :ms = " + (endMs - startMs));
    }

}
