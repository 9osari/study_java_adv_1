package thread.executor.future;

import java.util.Random;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class RunnalbeMain {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "Thread -1");
        thread.start();
        thread.join();      // main 스레드가 Thread-1 끝날 때까지 멈춰 기다림
        int result = task.value;
        log("result = " + result);
    }

    static class MyRunnable implements Runnable {
        int value;

        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);
            value = new Random().nextInt(10);
            log("create value = " + value);
            log("Runnable 완료");
        }
    }
    /*
    작업 스레드는 간단히 값을 `return` 을 통해 반환하고, 요청 스레드는 그 반환 값을 바로 받을 수 있다면 코드가 훨씬 더 간결해질 것이다.
    이런 문제를 해결하기 위해 Executor 프레임워크는 `Callable` 과 `Future` 라는 인터페이스를 도입했다.
     */
}
