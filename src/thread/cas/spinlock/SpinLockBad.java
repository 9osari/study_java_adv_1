package thread.cas.spinlock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SpinLockBad {
    //스핀락: 스레드를 대시기키지 않는 락
    private volatile boolean lock = false;

    public void lock() {
        log("락 획득 시도");
        while(true) {
            /**
             * 1. 락의 사용 여부 확인
             * 2. 락의 값 변경이 원자적이지 않다
             * CAS 연상을 사용해 두 연산을 하나로 묶어 하나의 원자적인 연산으로 처리할 수 있다.
             */
            if(!lock) { //1. 락 사용 여부 확인
                sleep(100); //문제 상황 확인용, 스레드 대기
                lock = true;    // 2. 락의 값 변경
                break;  //while 탈출
            } else {
                // 락을 획들할 때 까지 스핀 대기(바쁜 대기)
                log("락 획득 실패 - 스핀 대기");
            }
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock = false;
        log("락 반납 완료");
    }
}
