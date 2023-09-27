package forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class ForkJoinMain {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        AtomicInteger quantityWorks = new AtomicInteger(0);
        Integer valueMultiply = 3;


        RecursiveTask<Integer> task = new ForkJoinRecursiveTaskImpl(valueMultiply,1,quantityWorks);
        Integer result = forkJoinPool.invoke(task);
        System.out.println("Stop with -> " + result);
        System.out.println("Power -> " + valueMultiply + "^"+ quantityWorks );
    }

}
