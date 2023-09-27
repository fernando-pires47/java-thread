package completableFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureMain {

    public static void main(String[] args) {
        CompletableFuture<String> processe = CompletableFuture.supplyAsync(() -> {
            sleep(2000);
            return "Start -> ";
        });

        /* Get result of operation, in this case used as a middleware */
        CompletableFuture<String> thenApply = processe.thenApply(s -> {
            var test = s + "Middle -> ";
            System.out.println(test);
            return test;
        });

        /* Complete async execution with value inputted in param*/
        //thenApply.complete("dasdas");

        /* Cancel execution */
        //thenApply.cancel(true);

        /* Using void to end flux  */
        thenApply.thenAccept(s -> {
            System.out.println(s + "End");
        });

        /* Without the procedure below, the result not be called because finished execution of Main Thread*/
        sleep(6000);
    }

    private static void sleep(int timeSleep) {
        try {
            Thread.sleep(timeSleep);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
