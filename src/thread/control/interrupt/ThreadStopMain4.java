package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMain4 {
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
            while (!Thread.interrupted()) { //스레드가 인터럽트 상태(true) 라면 true를 반환하고 해당 스레드의 인터럽트 상태를 false로 변경
                log("작업 중");
            }
            log("work 스레드 인터럽트 상태2 = " + Thread.currentThread().isInterrupted());
            log("자원 정리");
            log("자원 종료");

        }
    }
}
