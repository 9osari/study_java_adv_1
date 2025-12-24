package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class BoundedQueueV6_4 implements BoundedQueue {

    private final BlockingQueue<String> queue;

    public BoundedQueueV6_4(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        //add 메서드는 버퍼가 가득 찬 경우 예외 발생
        queue.add(data);    // java.lang.IllegalStateException: Queue full
    }

    @Override
    public String take() {
        //remove 메서드는 버퍼가 빈 경우 예외 발생
        return queue.remove();  // java.util.NoSuchElementException
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
