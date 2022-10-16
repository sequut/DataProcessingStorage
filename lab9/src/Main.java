import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int philosophersNumber = 5;
        Lunch lunch = new Lunch(philosophersNumber);

        ExecutorService executorService = Executors.newFixedThreadPool(philosophersNumber);
        for (int i = 0; i < philosophersNumber; i++)
            executorService.submit(new Philosopher(i, (i + 1) % philosophersNumber, lunch, i));

        Thread.sleep(500);

        lunch.stopEating();
        executorService.shutdown();
    }
}