package executor.invoke;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorInvokeMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorService executor = Executors.newCachedThreadPool();

        try {
            List<Callable<String>> listCallable = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                listCallable.add(new ExecutorInvokeCallableImpl());
            }

            System.out.println("Execute One Operation");
            System.out.println(" ---------- ");
            System.out.println(executor.invokeAny(listCallable));
            System.out.println("\n********* *************\n");
            System.out.println("Execute All Operations");
            System.out.println(" ---------- ");

            List<Future<String>> list = executor.invokeAll(listCallable);

            for (Future<String> future : list) {
                System.out.println(future.get());
            }
        } finally {
            executor.shutdown();
        }
    }
}
