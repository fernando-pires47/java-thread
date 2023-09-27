package countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchMain {

    private static volatile int index = 0;
    private static CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {
        Executors.newScheduledThreadPool(3)
        .scheduleAtFixedRate(() -> {
            System.out.println(index);
            latch.countDown();
        }, 0, 1, TimeUnit.SECONDS);

        while (true) {
            latch.await();
            System.out.println("** Restarting **");
            index = new Random().nextInt(50);
            latch = new CountDownLatch(3);
        }
    }
}
