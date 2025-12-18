package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMain3 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(10);
        log("작업중단 지시 thread.interrupt()");
        thread.interrupt();
        log("thread.isInterrupted() = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        //여러 스레드에서 공유하는 값에 사용하는 키워드
        volatile boolean runFlag = true;

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) { //인터럽트 상태 변경 X
                log("작업 중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
            log("자원 정리");
            log("자원 종료");

        }
    }
}
