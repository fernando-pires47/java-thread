package consume;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ConsumeRecursiveMain {

    private static final Integer limit = 5;

    private static final List<Integer> list = new ArrayList<>(limit);

    private static boolean producing = true;
    private static boolean consuming = true;

    private static boolean executing = true;



    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(2);

        Thread produce = new Thread(() -> {
            while (executing) {
                sleep();
                if (producing) {
                    System.out.println("Producing");
                    list.add(new Random().nextInt(100));
                    if (list.size() == limit) {
                        System.out.println("Stop producer");
                        producing = false;
                    }
                    if (! consuming && ! list.isEmpty()) {
                        System.out.println("Start consumer");
                        consuming = true;
                    }
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
                    System.out.println("Consuming");
                    list.stream().findFirst().ifPresent(list::remove);
                    if (list.isEmpty()) {
                        System.out.println("Stop consumer");
                        consuming = false;
                    }
                    if (! producing && list.size() != limit) {
                        System.out.println("Start producer");
                        producing = true;
                    }
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
        System.out.println("Quantity -> " + list.size());
    }

    private static void sleep() {
        try {
            Thread.sleep(new Random().nextInt(50));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
