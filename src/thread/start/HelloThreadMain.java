package thread.start;

public class HelloThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + "main() start 호출 전");
        helloThread.start(); //너 알아서해! 던지고 자기할 일함
        System.out.println(Thread.currentThread().getName() + "main() start 호출 후");

        System.out.println(Thread.currentThread().getName() + "main() end");
    }
}
