package reentrantLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMain {
    private static int index = 0;

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            lock.lock();
            index++;
            System.out.println(Thread.currentThread().getName() + " -> " + index);
            lock.unlock();
        };

        for (int i = 0; i < 10; i++) {
            executor.execute(r1);
        }
        executor.shutdown();
    }
}
