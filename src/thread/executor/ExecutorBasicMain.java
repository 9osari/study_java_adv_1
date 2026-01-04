package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ExecutorBasicMain {
    public static void main(String[] args) {
        /**
         * ExecutorService` 의 가장 대표적인 구현체는 `ThreadPoolExecutor` 이다.
         * `corePoolSize` : 스레드 풀에서 관리되는 기본 스레드의 수
         * `maximumPoolSize` : 스레드 풀에서 관리되는 최대 스레드 수
         * `keepAliveTime` , `TimeUnit unit` : 기본 스레드 수를 초과해서 만들어진 스레드가 생존할 수 있는 대기 시
         * 간이다. 이 시간 동안 처리할 작업이 없다면 초과 스레드는 제거된다.
         * `BlockingQueue workQueue` : 작업을 보관할 블로킹 큐
         */
        ExecutorService es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        log("== 초기 상태 ==");
        printState(es);
        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("taskD"));
        log("== 작업 수행 중 ==");
        printState(es);

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(es);

        es.close();
        log("== shutdown ==");
        printState(es);
    }
}
