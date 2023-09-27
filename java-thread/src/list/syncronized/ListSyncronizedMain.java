package list.syncronized;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSyncronizedMain {
    private static final List<String> list = Collections.synchronizedList(new ArrayList<>()) ;

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            list.add(Thread.currentThread().getName());
        };

        for(int i = 0; i < 5;i++){
            Thread thread = new Thread(runnable, "#" + i);
            thread.start();
        }
        Thread.sleep(500);
        System.out.println(list);
    }
}
