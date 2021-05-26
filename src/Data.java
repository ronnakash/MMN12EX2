public class Data {

    private int x = 0;
    private int y = 0;

    public Data(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int getDiff() {
        try {
            wait(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (Math.abs(x - y));
    }

    public synchronized void update(int dx, int dy) {
        x = x + dx;
        y = y + dy;
        try {
            wait(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Data data = new Data(0,0);
        WriterThread writerThread = new WriterThread(data);
        ReaderThread readerThread = new ReaderThread(data);
        writerThread.start();
        readerThread.start();
    }

}