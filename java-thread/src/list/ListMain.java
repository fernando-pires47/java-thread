package list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ListMain {

    //static List<String> list = new ArrayList<>();
    static List<String> list = new CopyOnWriteArrayList<>();

    static Map<Integer, String> map = new ConcurrentHashMap<>();
    static BlockingQueue<String> queue = new LinkedBlockingQueue<>();



    public static void main(String[] args) throws InterruptedException {
        int threads = 25;
        AtomicInteger count = new AtomicInteger(1);

        Runnable runnableAdd = new ListRunnableImpl(list,ListOperation.ADD,count);
        Runnable runnableRemove = new ListRunnableImpl(list,ListOperation.REMOVE,count);

        //Runnable runnableAdd = new MapRunnableImpl(map,ListOperation.ADD,count);
        //Runnable runnableRemove = new MapRunnableImpl(map,ListOperation.REMOVE,count);

        //Runnable runnableAdd = new QueueRunnableImpl(queue,ListOperation.ADD,count);
        //Runnable runnableRemove = new QueueRunnableImpl(queue,ListOperation.REMOVE,count);

        for(int i = 0; i < threads; i++){
            Thread thread;
            if(i%2 == 0 || i < 3){
                thread = new Thread(runnableAdd,"#" + i);
            }else{
                thread = new Thread(runnableRemove,"#" + i);
            }
            thread.start();
            //thread.join();
            //Thread.sleep(100);
        }

        System.out.println("Queue -> " + queue);
        System.out.println("Map -> " + map);
        System.out.println("List -> " + list);
    }


}
