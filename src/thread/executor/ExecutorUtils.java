package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {
/*
ExecutorService` 인터페이스는 `getPoolSize()` , `getActiveCount()` 같은 자세한 기능은 제공하지 않는다.
 이 기능은 `ExecutorService` 의 대표 구현체인 `ThreadPoolExecutor` 를 사용해야 한다.
 */
    public static void printState(ExecutorService executorService) {
        if(executorService instanceof ThreadPoolExecutor poolExecutor) {    //캐스팅
            // 스레드 풀에서 관리되는 스레드의 숫자
            int pool = poolExecutor.getPoolSize();

            // 작업을 수행하는 스레드의 숫자
            int active = poolExecutor.getActiveCount();

            // 큐에 대기중인 작업의 숫자
            int queuedTasks = poolExecutor.getQueue().size();

            // 완료된 작업의 숫자
            long completedTask = poolExecutor.getCompletedTaskCount();

            log("[pool=" + pool + ", active=" + active + ", queuedTasks=" +
                    queuedTasks + ", completedTasks=" + completedTask + "]");

        } else {
            log(executorService);
        }
    }
}
