package executor.submit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorSubmitMain {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " -> Called");
        };

        executor.execute(runnable);
        executor.execute(runnable);
        Future<?> future = executor.submit(runnable);
        System.out.println(future.isDone());
        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();
        System.out.println(future.isDone());
    }
}
