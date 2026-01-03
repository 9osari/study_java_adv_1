package thread.collection.simple.list;

import static util.MyLogger.log;

public class SimpleListMainV2 {
    /**
     1. `test()` 메서드에서 스레드를 만들고, 스레드에 있는 `run()` 에서 `list.add()` 를 호출한다. `SyncProxyList(x002)` 에 있는 `add()` 가 호출된다.
     2. 프록시인 `SyncProxyList` 는 `synchronized` 를 적용한다. 그리고 나서 `target` 에 있는 `add()` 를 호출한다.
     3. 원본 대상인 `BasicList(x001)` 의 `add()` 가 호출된다.
     4. 원본 대상의 호출이 끝나면 결과를 반환한다.
     5. SyncProxyList` 에 있는 `add()` 로 흐름이 돌아온다. 메서드를 반환하면서 `synchronized` 를 해제한다.
     6. `test()`로 흐름이 돌아온다.
     */

    public static void main(String[] args) throws InterruptedException {
        test(new SyncProxyList(new BasicList()));
    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        // A를 리스트에 저장하는 코드
        Runnable addA = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("Thread-1 : list.add(A)");
            }
        };

        // B를 리스트에 저장하는 코드
        Runnable addB = new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("Thread-2 : list.add(B)");
            }
        };

        Thread thread1 = new Thread(addA, "Thread-1");
        Thread thread2 = new Thread(addB, "Thread-2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        log(list);
    }
}
