package cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierMain {

    public static void main(String[] args) {
        Runnable runnableEnd = () -> {
            System.out.println("Ending executions ...");
        };

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,runnableEnd);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable runnable = () -> {
            printStart();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            awaitCyclicBarrier(cyclicBarrier);
            printEnd();
        };

        executor.submit(runnable);
        executor.submit(runnable);
        executor.submit(runnable);

        executor.shutdown();
    }

    private static void printEnd(){
        System.out.println("**" + Thread.currentThread().getName() + " Finish **");
    }

    private static void printStart(){
        System.out.println("**" + Thread.currentThread().getName() + " Start **");
    }

    private static void awaitCyclicBarrier(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
