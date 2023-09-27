package exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerMain {

    private static final Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable r1 = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(exchange(threadName + "** Send Message **"));
        };
        Runnable r2 = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(exchange(threadName + "** Recive Message **"));
        };

        executor.execute(r1);
        executor.execute(r2);
        executor.shutdown();
    }
    private static String exchange(String msg) {
        try {
            return exchanger.exchange(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
