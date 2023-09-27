package thread;

public class ThreadMain {


    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();

            System.out.println(name + " -> Iniciate");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(name + " -> Finish");
        };


        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        /* Main thread execute */
        t1.run();

        /* New thread execute */
        t1.start();
        t2.start();
        t3.start();
    }
}
