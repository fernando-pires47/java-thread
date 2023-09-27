package syncronized;

public class SyncronizedRunnableImpl implements Runnable {

    private int index;

    SyncronizedRunnableImpl(int index){
        this.index = index;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            index++;
        }
        System.out.println(index);

    }
}
