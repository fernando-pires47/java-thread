package atomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicRunnableImpl implements Runnable {
    static volatile AtomicLong atomicLong = new AtomicLong(5);
    static volatile AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    static volatile AtomicReference<Object> atomicReferenceObject = new AtomicReference<>(new Object());

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " -> " + atomicLong.getAndIncrement());
        //atomicBoolean.set(! atomicBoolean.get());
        //System.out.println(Thread.currentThread().getName() + " -> " + atomicBoolean.get());
        //System.out.println(Thread.currentThread().getName() + " -> " + atomicReferenceObject.getAndSet(new Object()));
    }
}
