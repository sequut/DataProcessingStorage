import java.util.concurrent.Semaphore;

public class Printer implements Runnable {
    private static Semaphore semaphore1;
    private static Semaphore semaphore2;
    private final int iterations;

    public Printer(int iterations, Semaphore semaphore1, Semaphore semaphore2) {
        this.iterations = iterations;
        Printer.semaphore1 = semaphore1;
        Printer.semaphore2 = semaphore2;
    }

    @Override
    public void run() {
        for(int i = 0; i < iterations; i++) {
            try {
                semaphore2.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(i + " from thread");
            semaphore1.release();
        }
    }
}