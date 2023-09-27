package syncronized;

public class SyncronizedMain {
    private static int index = 0;

    public static void main(String[] args) {

        Runnable runnable = new SyncronizedRunnableImpl(index);

        for(int i = 0; i<5; i++){
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
