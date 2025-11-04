package thread.sync.test;


/*
다음 코드의 결과는 20000이 되어야 한다.
문제점을 찾아 해결하라
이 코드에서 다른 부분은 변경하면 안되고 Counter 클래스 내부만 수정 가능
 */
public class SyncTest1BadMain {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("결과: " + counter.getCount());
    }

    static class Counter {

        private int count = 0;

        public synchronized void increment() {  //동기화 처리
            count = count +1;
        }

        public int getCount() {
            return count;
        }
    }
}

/*
count는 여러 스레드가 함께 사용하는 공유 자원
increment 는 3단계로 나뉜다
1. count를 읽음
2. 읽은 count의 값 +1
3. 더한 결과를 count에 저장

여러 스레드가 공유 자원에 함께 접근한다. 즉 2개의 스레드가 동시에 increment 메서드를 호출
스레드 2개가 increment를 호출하기 때문에 기댓값은 2
둘이 동시에 실행되어 둘다 count의 값을 0으로 읽음..

따라서 synchronized 키워드를 사용해 한 번에 하나의 스레드만 실행할 있도록
안전한 임계영역을 만들어야 한다...
 */
