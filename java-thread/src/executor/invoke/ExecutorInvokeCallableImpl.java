package executor.invoke;

import java.util.concurrent.Callable;

public class ExecutorInvokeCallableImpl implements Callable<String> {
    @Override
    public String call() throws Exception {
        return ". " + Thread.currentThread().getName() + " Used";
    }
}
