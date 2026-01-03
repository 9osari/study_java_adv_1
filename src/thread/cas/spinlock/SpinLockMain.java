package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockMain {
    public static void main(String[] args) {
        /*SpinLockBad spinLock = new SpinLockBad();*/
        SpinLock spinLock = new SpinLock();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                spinLock.lock();
                try {
                    //임계 영역
                    log("비즈니스 로직 실행");
                    sleep(1);   //오래 걸리는 로직에서 스핀 락 사용하면 안됌
                    //안전한 임계 영역이 필요하지만 연산이 매우 짧게 끝날 때 사용
                } finally {
                    spinLock.unlock();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
