package executor.schedule;

import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorScheduleRunnableImpl implements Runnable{

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        count.getAndIncrement();
        System.out.println(Thread.currentThread().getName() + " -> " + count);
    }
}
