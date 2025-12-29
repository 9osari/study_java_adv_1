package thread.cas.increment;

import java.util.ArrayList;
import java.util.List;

import static util.ThreadUtils.sleep;

public class ImcrementThreadMain {
    public static final int THREAD_COUNT = 1000;
    public static void main(String[] args) throws InterruptedException {
        test(new BasicInterger());
        test(new VolatileInterger());
        test(new SyncInterger());
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInterger incrementInterger) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sleep(10);
                incrementInterger.increment();
            }
        };
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for(Thread thread : threads) {
            thread.join();
        }

        int result = incrementInterger.get();
        System.out.println(incrementInterger.getClass().getSimpleName() + " result = " + result);
    }
}
