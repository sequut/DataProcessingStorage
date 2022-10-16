import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Lunch {
    private boolean eating = true;
    private final ArrayList<ReentrantLock> forks = new ArrayList<>();

    public Lunch(int philosophersNumber){
        for (int i = 0; i < philosophersNumber; i++)
            forks.add(new ReentrantLock());
    }

    public void stopEating(){
        eating = false;
    }

    public void lunching(int left, int right, int number) {
        int maxFork = Integer.max(left, right);
        int minFork = Integer.min(left, right);
        while (eating){
            try {
                System.out.println("philosopher " + number + " thinking");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            forks.get(minFork).lock();
            forks.get(maxFork).lock();

            System.out.println("philosopher " + number + " take forks");

            try {
                System.out.println("philosopher " + number + " eating");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("philosopher " + number + " finished eating");

            forks.get(maxFork).unlock();
            forks.get(minFork).unlock();
        }
    }
}