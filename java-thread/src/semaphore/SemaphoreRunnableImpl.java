package semaphore;

public class SemaphoreRunnableImpl implements Runnable{
        boolean suspense = false;
        int time = 4000;

        @Override
        public void run() {
            for(int i=0; i<10; i++){
                System.out.println("Street traffic light is: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this){
                    while(suspense){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }}
            System.out.println("End!");
        }
        void suspend(){
            this.suspense = true;
        }

        synchronized void resume(){
            this.suspense = false;
            notify();
        }
}
