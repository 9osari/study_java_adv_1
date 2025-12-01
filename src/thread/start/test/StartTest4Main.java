package thread.start.test;

import static util.MyLogger.log;

public class StartTest4Main {

    public static void main(String[] args) {
        //내가 씀
        HelloThread helloThread = new HelloThread();
        Thread thread = new Thread(helloThread, "Thread-A");
        Thread thread2 = new Thread(helloThread, "Thread-B");
        /*thread.start();
        thread2.start();*/


        //선생님이 씀
        Thread thread3 = new Thread(new PrintWork("A", 1000));
        Thread thread4 = new Thread(new PrintWork("B", 500));
        thread3.start();
        thread4.start();

    }

    //내가 쓴거
    static class HelloThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                if(Thread.currentThread().getName().equals("Thread-A")) {
                    try {
                        Thread.sleep(1000);
                        log("A");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log("B");
            }
        }
    }
    //선생님이 쓴거
    static class PrintWork implements Runnable {
        private String content;
        private int sleepMs;

        public PrintWork(String content, int sleepMs) {
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            while (true) {
                log(content);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

