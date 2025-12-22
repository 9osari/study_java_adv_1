package thread.bounded;

import static util.MyLogger.log;

public class CunsumerTask implements Runnable{
    private BoundedQueue queue;

    public CunsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        log("[소비 시도]     ? <= " + queue);
        String data = queue.take();
        log("[소비 완료] " + data + " <= " + queue);
    }
}
