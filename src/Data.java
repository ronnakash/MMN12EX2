public class Data {

    private int x;
    private int y;
    private int reading;
    private boolean writing;

    public Data(int x, int y) {
        this.x = x;
        this.y = y;
        reading = 0;
        writing = false;
    }

    public int getDiff() {
        int diff;
        incReading();
        System.out.println("Calculating difference");
        synchronized (this) {
            try {
                while (writing)
                    wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        diff = Math.abs(x - y);
        System.out.println("Calculated difference: " + diff);
        decReading();
        wakeup();
        return diff;
    }

    public synchronized void update(int dx, int dy) {
        try {
            while (reading>0)
                wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writing = true;
        System.out.println("Writing: " + dx + " " + dy);
        x = x + dx;
        y = y + dy;
        writing = false;
        System.out.println("Done writing: " + x + " " + y);
        wakeup();
    }


    public static void main(String[] args) {
        Data data = new Data(0,0);
        WriterThread[] writerThreads = new WriterThread[] {new WriterThread(data),
                new WriterThread(data),
                new WriterThread(data),
                new WriterThread(data)};
        ReaderThread[] readerThreads = new ReaderThread[] {new ReaderThread(data),
                new ReaderThread(data),
                new ReaderThread(data),
                new ReaderThread(data)};
        for (ReaderThread readerThread : readerThreads)
            readerThread.start();
        for (WriterThread writerThread : writerThreads)
            writerThread.start();
//        writerThreads[0].start();
//        readerThreads[0].start();
//        writerThreads[1].start();
//        readerThreads[1].start();
//        writerThreads[2].start();
//        readerThreads[2].start();
//        writerThreads[3].start();
//        readerThreads[3].start();
    }

    private synchronized void incReading(){reading++;}

    private synchronized void decReading(){reading--;}

    private synchronized void wakeup(){notifyAll();}

}