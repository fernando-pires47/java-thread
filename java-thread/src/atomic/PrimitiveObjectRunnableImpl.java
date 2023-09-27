package atomic;

import java.util.List;
import java.util.Optional;

public class PrimitiveObjectRunnableImpl implements Runnable{
    static Long defaultLong = 5L;
    static Boolean defaultBoolean = false;
    static Object defaultObject = null;

    List<Long> list;

    PrimitiveObjectRunnableImpl(List<Long> list){
        this.list = list;
    }

    @Override
    public void run() {
        defaultLong++;
        defaultBoolean = ! defaultBoolean;
        defaultObject = new Object();
        Optional<Long> value = this.list.stream().filter(p -> p.equals(defaultLong)).findFirst();
        if(value.isPresent()){
            throw new RuntimeException("Duplicate value in list -> " + value.get());
        }
        this.list.add(defaultLong);
        System.out.println(Thread.currentThread().getName() + " -> " + defaultLong);
        System.out.println(Thread.currentThread().getName() + " -> " + defaultBoolean);
        System.out.println(Thread.currentThread().getName() + " -> " + defaultObject);
    }
}
