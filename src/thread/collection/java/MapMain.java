package thread.collection.java;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MapMain {
    public static void main(String[] args) {
        //순서보장 X
        Map<Integer, String> map1 = new ConcurrentHashMap<>();
        map1.put(1, "data1");
        map1.put(2, "data2");
        map1.put(3, "data3");
        System.out.println(map1);

        //순서보장 O
        Map<Integer, String> map2 = new ConcurrentSkipListMap<>();
        map2.put(2, "data2");
        map2.put(3, "data3");
        map2.put(1, "data1");
        System.out.println(map2);
    }
}
