package list;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueRunnableImpl implements Runnable{

    BlockingQueue<String> queue;

    ListOperation listOperation;

    AtomicInteger count;

    QueueRunnableImpl(BlockingQueue<String> queue, ListOperation listOperation, AtomicInteger count){
        this.queue = queue;
        this.listOperation = listOperation;
        this.count = count;
    }

    @Override
    public void run() {

        if(this.listOperation.equals(ListOperation.ADD)){
            try {
                this.queue.put("#" + count);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count.getAndIncrement();
        }else if(this.listOperation.equals(ListOperation.REMOVE)) {
            try {
                this.queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Iterator<String> iterator = this.queue.iterator();
        while (iterator.hasNext()) {
            String val = iterator.next();
            System.out.println(val);
        }
        System.out.println(this.queue.toString());
        System.out.println("--------------------");

    }
}
