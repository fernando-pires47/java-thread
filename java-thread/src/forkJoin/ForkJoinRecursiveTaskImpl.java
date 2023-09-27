package forkJoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class ForkJoinRecursiveTaskImpl extends RecursiveTask<Integer> {

    private Integer maxValue = 100;

    private Integer value;

    private Integer valueMultiply;

    private AtomicInteger quantityWorks;

    public ForkJoinRecursiveTaskImpl(Integer valueMultiply, Integer value, AtomicInteger quantityWorks) {
        this.valueMultiply = valueMultiply;
        this.quantityWorks = quantityWorks;
        this.value = value;
    }

    @Override
    protected Integer compute() {
        if(maxValue <= value){
            return value;
        }else{
            quantityWorks.getAndIncrement();
            Integer newVal = valueMultiply * value;
            ForkJoinRecursiveTaskImpl task = new ForkJoinRecursiveTaskImpl(valueMultiply,newVal,quantityWorks);
            ForkJoinTask<Integer> forkJoinTask = task.fork();
            return forkJoinTask.join();
        }
    }
}
