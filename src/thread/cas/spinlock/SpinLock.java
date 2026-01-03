package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyLogger.log;

public class SpinLock {
    /**
     * 락을 획득할 때 매우 중요한 부분이 있다. 바로 다음 두 연산을 하나로 만들어야 한다는 점이다.
     * **1. 락 사용 여부 확인**
     * **2. 락의 값 변경**
     */
    //스핀락: 스레드를 대시기키지 않는 락
    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("락 획득 시도");
        while(!lock.compareAndSet(false, true)) {
            // 락을 획들할 때 까지 스핀 대기(바쁜 대기)
            log("락 획득 실패");
        }
        log("락 획득 완료");
    }

    /**
     * CAS 연산은 이 두 연산을 하나의 원자적인 연산으로 만들어준다.
     * `lock.compareAndSet(false, true)`
     * **1. 락 사용 여부 확인**: `lock` 의 값이 `false` 이면
     * **2. 락의 값 변경**: `lock` 의 값을 `true`로 변경해라.
     */

    public void unlock() {
        lock.set(false);
        log("락 반납 완료");
    }
}
