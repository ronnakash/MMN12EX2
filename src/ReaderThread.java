import java.util.Random;

public class ReaderThread extends Thread{

    private Data myData;

    public ReaderThread(Data data){
        myData = data;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("current diff: " + myData.getDiff());
        }
    }

}
