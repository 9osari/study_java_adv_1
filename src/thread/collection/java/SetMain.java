package thread.collection.java;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {
    public static void main(String[] args) {
        //순서보장 X
        Set<Integer> copySet = new CopyOnWriteArraySet<>();
        copySet.add(1);
        copySet.add(3);
        copySet.add(2);
        System.out.println(copySet);

        //순서보장 O
        Set<Integer> skipSet = new ConcurrentSkipListSet<>();
        skipSet.add(1);
        skipSet.add(2);
        skipSet.add(3);
        System.out.println(skipSet);
    }
}
