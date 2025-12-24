package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static util.MyLogger.log;

public class BoundedQueueV6_2 implements BoundedQueue {

    private final BlockingQueue<String> queue;

    public BoundedQueueV6_2(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean result = queue.offer(data); // 성공하면 true반환, 버퍼가 가득 차면 즉시 false 반환
        log("저장 시도 결과 = " + result);
    }

    @Override
    public String take() {
        return queue.poll();    //버퍼에 데이터가 없으면 즉시 null 반환
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
