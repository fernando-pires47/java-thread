package consume;

import java.util.Random;
import java.util.concurrent.*;

public class ConsumeQueueMain {
    private static final Integer limit = 5;
    private static final BlockingQueue<Integer> FILA = new LinkedBlockingDeque<>(limit);

    public static void main(String[] args) throws InterruptedException {

        Runnable produce = () -> {
            sleep();
            System.out.println("Producing");
            try {
                FILA.put(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable consume = () -> {
            sleep();
            try {
                System.out.println("Consuming -> " + FILA.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleWithFixedDelay(produce, 0, 100, TimeUnit.MILLISECONDS);
        executor.scheduleWithFixedDelay(consume, 0, 100, TimeUnit.MILLISECONDS);

        Thread.sleep(10000);
        executor.shutdown();
    }

    private static void sleep() {
        int tempo = new Random().nextInt(50);
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
