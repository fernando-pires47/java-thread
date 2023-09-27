package atomic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AtomicMain {
    public static void main(String[] args) throws InterruptedException {
        int threads = 45;

        List<Long> list = new CopyOnWriteArrayList<>();

       // Runnable runnable = new AtomicRunnableImpl(list);
        Runnable runnable = new PrimitiveObjectRunnableImpl(list);


        /*
        Using method join or sleep the behavior will be correct, else it will not.
        */
        for(int i = 0; i < threads; i++){
            Thread thread = new Thread(runnable,"#" + i);
            thread.start();
            //thread.join();
            //Thread.sleep(100);
        }
    }
}
