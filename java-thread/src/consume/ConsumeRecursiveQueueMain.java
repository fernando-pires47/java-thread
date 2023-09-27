package consume;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumeRecursiveQueueMain {

    private static final Integer limit = 5;
    private static final BlockingQueue<Integer> queue =
            new LinkedBlockingDeque<>(limit);
    private static boolean producing = true;
    private static boolean consuming = true;
    private static final Lock lock = new ReentrantLock();

    private static boolean executing = true;


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);

        Thread produce = new Thread(() -> {
            while (executing) {
                sleep();
                if (producing) {
                    lock.lock();
                    System.out.println("Producing");
                    queue.add(new Random().nextInt(1000));
                    if (queue.size() == limit) {
                        System.out.println("Stop producer");
                        producing = false;
                    }
                    if (! consuming && ! queue.isEmpty()) {
                        System.out.println("Start consumer");
                        consuming = true;
                    }
                    lock.unlock();
                } else {
                    System.out.println("Producer waiting");
                }
            }
            latch.countDown();
        });

        Thread consume = new Thread(() -> {
            while (executing) {
                sleep();
                if (consuming) {
                    lock.lock();
                    System.out.println("Consuming");
                    queue.stream().findFirst().ifPresent(queue::remove);
                    if (queue.isEmpty()) {
                        System.out.println("Stop consumer");
                        consuming = false;
                    }
                    if (queue.size() == (limit - 1)) {
                        System.out.println("Start producer");
                        producing = true;
                    }
                    lock.unlock();
                } else {
                    System.out.println("Consumer waiting");
                }

            }

            latch.countDown();
        });

        produce.start();
        consume.start();

        Thread.sleep(5000);
        executing = false;
        latch.await();
        System.out.println("Quantity -> " + queue.size());
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
