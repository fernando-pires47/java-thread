package thread;

public class ThreadDeadLockMain {

    public static void main(String[] args) {

        String resource1 = "Resource #1";
        String resource2 = "Resource #2";

        boolean r1 = false;
        boolean r2 = false;

        Thread t1 = new Thread(){
            public void run(){
                synchronized (resource1){
                    System.out.println("Thread #1: Blocked resource 1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread #1: Try access resource 2");
                    synchronized (resource2){
                        System.out.println("Thread #1: Blocked resource 2");
                    }
                }

            }};

        Thread t2=new Thread(){
            public void run(){
                synchronized (resource2){
                    System.out.println("Thread #2: Blocked resource 2");

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread #2: Try access resource 1");
                    synchronized (resource1){
                        System.out.println("Thread #2: Blocked resource 1");
                    }
                }
            }};

        t1.start();
        t2.start();
    }
}
