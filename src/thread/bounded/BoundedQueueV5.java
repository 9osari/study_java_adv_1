package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;

public class BoundedQueueV5 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();  //내부에 락과 락 획득을 대기하는 스레드를 관리하는 대기 큐가 있음
    private final Condition producerCond = lock.newCondition();    //스레드 대기집합
    private final Condition cunsumerCond = lock.newCondition();    //스레드 대기집합

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV5(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();
        try {
            while (queue.size() == max) {
                log("[put] 큐가 가득 참, 생산자 대기");
                try {
                    producerCond.await();
                    log("[put] 생산자 꺠어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.offer(data);
            log("[put] 생산자 데이터 저장, signal() 호출");
            producerCond.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                log("[take] 큐가 데이터가 없음. 소비자 대기");
                try {
                    cunsumerCond.await();   // 소비자 대기소에서 대기
                    log("[take] 소비자 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String data = queue.poll();
            log("[take] 소비자 데이터 획득, signal() 호출");
            producerCond.signal();  //생산자야 큐에 공간이 비었어
            return data;
        } finally {
            lock.unlock();
        }
        // 생산자는 소비자를 꺠우고, 소비자는 생산자를 꺠운다는 점
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
