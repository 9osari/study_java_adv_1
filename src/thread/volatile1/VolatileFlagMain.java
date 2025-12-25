package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        thread.start();


        sleep(1000);
        log("runFlag를 false로 변경 시도");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);
        log("main 종료");
    }

    static class MyTask implements Runnable {

        /*boolean runFlag = true;*/
        volatile boolean runFlag = true;    //캐시메모리를 사용하지 않고 값을 읽거나 쓸 때 메인 메모리에 직접 접근! (단 성능 캐시메모리 > 메인메모리)
        @Override
        public void run() {
            log("task 시작");
            while (runFlag) {
                //runFlag가 false로 변하면 탈출
                //컨텍스트 스위칭이 잃어나면 나감.. sleep이나 sout 같은
            }
            log("task 종료");
        }
    }
}
