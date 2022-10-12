import java.util.concurrent.Semaphore;

public class Main {
    private static final int iterations = 20;
    private static final Semaphore semaphore1 = new Semaphore(1);
    private static final Semaphore semaphore2 = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer(iterations, semaphore1, semaphore2);
        Thread thread = new Thread(printer);
        thread.start();
        for (int i = 0; i < iterations; i++){
            semaphore1.acquire();
            System.out.println(i + " from main ##");
            semaphore2.release();
        }
    }
}