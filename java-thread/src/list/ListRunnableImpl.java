package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ListRunnableImpl implements Runnable{

    List<String> list;
    ListOperation listOperation;

    AtomicInteger count;

    ListRunnableImpl(List<String> list, ListOperation listOperation, AtomicInteger count){
        this.list = list;
        this.listOperation = listOperation;
        this.count = count;
    }

    @Override
    public void run() {
        try{
            if(this.listOperation.equals(ListOperation.ADD)){
                this.list.add("#" + count);
                this.count.getAndIncrement();
            }else if(this.listOperation.equals(ListOperation.REMOVE)) {
                this.list.remove(this.list.size() - 1);
            }

            Iterator<String> iterator = this.list.iterator();
            while (iterator.hasNext()) {
                String val = iterator.next();
                System.out.println(val);
            }
            System.out.println(this.list.toString());
            System.out.println("--------------------");
        }catch (ConcurrentModificationException e){
            throw new ConcurrentModificationException("ConcurrentModificationException is generate in common list because of concurrence");
        }

    }
}
