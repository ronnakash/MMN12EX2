import java.util.Random;

public class ReaderThread extends Thread{

    private Data myData;

    public ReaderThread(Data data){
        myData = data;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            myData.getDiff();
            try {
                wait(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
