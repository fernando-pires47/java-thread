package reentrantLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockReadWriteMain {
    private static int i = -1;
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            Lock writeLock = lock.writeLock();
            writeLock.lock();
            String name = Thread.currentThread().getName();
            System.out.println(name + " | Old Write -> " + i);
            i++;
            System.out.println(name + " | New Write -> " + i);
            writeLock.unlock();
        };
        Runnable r2 = () -> {
            Lock readLock = lock.readLock();
            readLock.lock();
            String name = Thread.currentThread().getName();
            System.out.println(name + " | Read -> " + i);
            readLock.unlock();
        };

        for (int i = 0; i < 10; i++) {
            executor.execute(r1);
            executor.execute(r2);
        }

        executor.shutdown();
    }
}
