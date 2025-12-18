package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMain1 {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("작업중단 지시 runFlag = false");
        task.runFlag = false;
    }

    static class MyTask implements Runnable {

        //여러 스레드에서 공유하는 값에 사용하는 키워드
        volatile boolean runFlag = true;

        @Override
        public void run() {
            while (runFlag) {
                log("작업중");
                sleep(3000);
            }
            log("자원 정리");
            log("자원 종료");

        }
    }
}
