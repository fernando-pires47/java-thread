package semaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreAcquireMain {

    private static final Semaphore semaphore = new Semaphore(3);

    private static Integer quantity = 0;


    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable runnable = () -> {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName());

            synchronized (quantity){
                quantity++;
            }
            sleep();
            semaphore.release();
            System.out.println(quantity);
        };

        for (int i = 0; i < 500; i++) {
            executor.execute(runnable);
        }
        executor.shutdown();
    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
