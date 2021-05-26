public class Data {

    private int x = 0;
    private int y = 0;
    private int turn;

    public Data(int x, int y) {
        this.x = x;
        this.y = y;
        turn = 1;
    }

    public synchronized int getDiff() {
        while (turn==1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        turn = 1;
        notifyAll();

        return (Math.abs(x - y));
    }

    public synchronized void update(int dx, int dy) {
        while (turn==0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        x = x + dx;
        y = y + dy;
        turn = 0;
        notifyAll();
    }

    public static void main(String[] args) {
        Data data = new Data(0,0);
        WriterThread writerThread = new WriterThread(data);
        ReaderThread readerThread = new ReaderThread(data);
        writerThread.start();
        readerThread.start();
    }

}