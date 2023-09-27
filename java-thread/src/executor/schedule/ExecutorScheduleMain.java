package executor.schedule;

import java.util.concurrent.*;

public class ExecutorScheduleMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        Runnable runnable = new ExecutorScheduleRunnableImpl();

        /* Realize one execution */
        //executor.schedule(runnable, 10, TimeUnit.SECONDS);

        /* Rate maintain the routine of execution */
        executor.scheduleAtFixedRate(runnable, 5, 5, TimeUnit.SECONDS);

        /* Delay increments the routine in each execution */
        //executor.scheduleWithFixedDelay(runnable, 5, 5, TimeUnit.SECONDS);

        Thread.sleep(10000);
        executor.shutdown();
    }
}
