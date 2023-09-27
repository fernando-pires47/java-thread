package semaphore;

public class SemaphoreMain {

    public static void main(String[] args) {
        SemaphoreRunnableImpl r1 = new SemaphoreRunnableImpl();
        SemaphoreRunnableImpl r2 = new SemaphoreRunnableImpl();
        SemaphoreRunnableImpl r3 = new SemaphoreRunnableImpl();
        Thread thread = new Thread(r1,"Red");
        Thread thread2 = new Thread(r2,"Green");
        Thread thread3 = new Thread(r3,"Yellow");
        thread.start();
        thread2.start();
        thread3.start();


        for(int i=0;i<10;i++){
            r2.suspend();
            r3.suspend();

            try {
                Thread.sleep(3999);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            r1.suspend();
            r2.resume();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            r2.suspend();
            r3.resume();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1.resume();
        }
    }
}
