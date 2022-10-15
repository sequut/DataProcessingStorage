import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Lunch {
    private final ReentrantLock locker;
    private final int philosophersNumber;
    private boolean eating = true;
    private final ArrayList<Integer> forks;

    public Lunch(int philosophersNumber){
        this.philosophersNumber = philosophersNumber;
        locker = new ReentrantLock();
        forks = new ArrayList<>();
        for (int i = 0; i < philosophersNumber; i++)
            forks.add(0);
    }

    public void stopEating(){
        eating = false;
    }

    public void addForks() {
        for (int i = 0; i < philosophersNumber; i++)
            forks.set(i, 1);
    }
    public void lunching(int left, int right, int number) {
        boolean philosopherTakeForks = false;
        while (eating){
            try {
                System.out.println("philosopher " + number + " thinking");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            locker.lock();
            if (forks.get(left) == 1 && forks.get(right) == 1) {
                forks.set(left, 0);
                forks.set(right, 0);
                System.out.println("philosopher " + number + " take forks");
                philosopherTakeForks = true;
            }
            locker.unlock();

            if (philosopherTakeForks){
                try {
                    System.out.println("philosopher " + number + " eating");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("philosopher " + number + " finished eating");

                forks.set(left, 1);
                forks.set(right, 1);
                philosopherTakeForks = false;
            }
        }
    }
}