package thread.sync.test;

/*
MyTask의 run() 메서드는 두 스레드에서 동시에 실행한다.
실행결과를 예측해보자
localValue 지역 변수에 동시성 문제가 발생하는지 하지 않는지 생각해보자
 */
public class SyncTest2Main {
    public static void main(String[] args) throws InterruptedException {
        MyCounter myCounter = new MyCounter();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                myCounter.count();
            }
        };
        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        thread1.start();
        thread2.start();
    }
    static class MyCounter {
        public void count() {
            int localValue = 0; //지역 변수는 절대로 다른 스레드와 공유되지 않음.
            //지역 변수를 제외한 인스턴스의 맴버변수(필드), 클래스 변수 등은 공유 가능
            for (int i = 0; i < 1000; i++) {
                localValue = localValue + 1;
            }
            System.out.println("localValue:" + localValue);
        }
    }
}