import java.util.Random;

public class WriterThread extends Thread{

    private Data myData;
    private Random random;

    public WriterThread(Data data){
        myData = data;
        random = new Random();
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            myData.update(random.nextInt(100)-50,random.nextInt(100)-50);
            try {
                wait(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
