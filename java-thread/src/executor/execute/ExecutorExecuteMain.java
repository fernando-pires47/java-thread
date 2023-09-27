package executor.execute;

import java.util.concurrent.*;

public class ExecutorExecuteMain {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName() + " -> Called";
            }
        };

        executor.submit(callable);
        Future<String> future = executor.submit(callable);
        System.out.println(future.isDone());
        System.out.println(future.get());
        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();
        System.out.println(future.isDone());
    }
}
