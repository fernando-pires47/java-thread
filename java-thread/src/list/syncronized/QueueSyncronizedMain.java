package list.syncronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class QueueSyncronizedMain {
    private static final SynchronousQueue<String> queue = new SynchronousQueue<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            try {
                queue.put(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable r2 = () -> {
            try {
                System.out.println("Read: " + queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        executor.execute(r1);
        executor.execute(r2);
        executor.shutdown();
    }
}
