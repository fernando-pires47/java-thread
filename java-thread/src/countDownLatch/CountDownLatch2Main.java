package countDownLatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountDownLatch2Main {
    private static volatile int i = 0;
    private static CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

        Runnable r1 = () -> {
            System.out.println(i);
            latch.countDown();
        };
        Runnable r2 = () -> {
            latchAwait();
            i = new Random().nextInt(100);
        };
        Runnable r3 = () -> {
            latchAwait();
            latch = new CountDownLatch(3);
        };
        Runnable r4 = () -> {
            latchAwait();
            System.out.println("** Restarting **");
        };

        List<Runnable> runnables = new ArrayList<>(List.of(new Runnable[]{r1, r2, r3, r4}));

        for( Runnable runnable : runnables){
            executor.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
        }
    }

    private static void latchAwait() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
