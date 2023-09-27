package list;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MapRunnableImpl implements Runnable{

     Map<Integer, String> map;

    ListOperation listOperation;

    AtomicInteger count;


    MapRunnableImpl(Map<Integer, String> map, ListOperation listOperation, AtomicInteger count){
        this.map = map;
        this.listOperation = listOperation;
        this.count = count;
    }

    @Override
    public void run() {

        if(this.listOperation.equals(ListOperation.ADD)){
            this.map.put(count.get(),"#" + count);
            count.getAndIncrement();
        }else if(this.listOperation.equals(ListOperation.REMOVE)) {
            this.map.remove(this.map.size());
        }

        Iterator<String> iterator = this.map.values().iterator();
        while (iterator.hasNext()) {
            String val = iterator.next();
            System.out.println(val);
        }
        System.out.println(this.map.toString());
        System.out.println("--------------------");

    }
}
