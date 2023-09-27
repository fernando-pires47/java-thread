package semaphore;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreTryAcquireMain {

    private static final Semaphore semaphore = new Semaphore(10);

    private static final AtomicInteger quantity = new AtomicInteger(100);

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(501);

        Runnable r1 = () -> {
            boolean allowExecute = false;
            while (!allowExecute) {
                try {
                    allowExecute = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            quantity.decrementAndGet();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            semaphore.release();
        };

        Runnable r2 = () -> {
            int auxExecutor = quantity.get();
            System.out.println("Still Remain -> " + auxExecutor);
            if(auxExecutor <= 0){
                executor.shutdownNow();
            }
        };

        for (int i = 0; i < 500; i++) {
            executor.execute(r1);
        }
        executor.scheduleAtFixedRate(r2, 0, 1000, TimeUnit.MILLISECONDS);
    }
}
