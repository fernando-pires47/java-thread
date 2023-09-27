package cyclicBarrier;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CyclicBarrier2Main {
    private static ExecutorService executor;
    private static Runnable r1;
    private static Runnable r2;
    private static Runnable r3;

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        Runnable runnableEnd = () -> {
            System.out.println("Ending executions ...");
            if(count.get() == 5){
                executor.shutdown();
            }
            start();
        };
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, runnableEnd);

        executor = Executors.newFixedThreadPool(3);
        r1 = () -> {
            System.out.println("Executing r1 ...");
            executor.submit(r2);
            await(cyclicBarrier);
        };
        r2 = () -> {
            System.out.println("Executing r2 ...");
            executor.submit(r3);
            await(cyclicBarrier);
        };
        r3 = () -> {
            System.out.println("Executing r3 ...");
            await(cyclicBarrier);
        };

        start();
//    executor.shutdown();
    }

    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
    private static void start() {
        sleep();
        executor.submit(r1);
        count.getAndIncrement();
    }
    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
