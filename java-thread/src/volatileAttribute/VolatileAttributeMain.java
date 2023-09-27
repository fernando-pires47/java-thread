package volatileAttribute;

import java.util.concurrent.CountDownLatch;

public class VolatileAttributeMain {
    private static  int number = 0;

    private static CountDownLatch latch = new CountDownLatch(1);


    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (number != 42) {
                throw new IllegalStateException("Volatile not work");
            }else{
                System.out.println(number);
            }
        };

        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(2000);
        number = 42;
        latch.countDown();
    }
}
