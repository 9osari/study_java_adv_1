package thread.control;

import static util.MyLogger.log;

public class ThreadStateMain {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable(), "myThread");
        log("myThread state: " + thread.getState());
        log("myThread start");
        thread.start();
        Thread.sleep(1000);
        log("myThread.status3 = " + thread.getState());
        Thread.sleep(4000);
        log("myThread.status5 = " + thread.getState());
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                log("start");
                log("myThread.status2 = " + Thread.currentThread().getState());
                log("sleep() start");
                Thread.sleep(3000);
                log("sleep() end");
                log("myThread.status4 = " + Thread.currentThread().getState());

                log("end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
