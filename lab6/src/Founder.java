import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public final class Founder {
    private final List<Runnable> workers;
    private final CyclicBarrier cyclicBarrier;
    public Founder(final Company company) {
        this.workers = new ArrayList<>(company.getDepartmentsCount());
        cyclicBarrier = new CyclicBarrier(company.getDepartmentsCount(), company::showCollaborativeResult);

        for (int i = 0; i < company.getDepartmentsCount(); i++)
            workers.add(new Worker(company.getFreeDepartment(i)));

    }

    private class Worker implements Runnable {
        private final Department department;
        Worker (Department department){
            this.department = department;
        }

        @Override
        public void run(){
            department.performCalculations();
            System.out.println("№" + department.getIdentifier() + " calculated");
            try {
                cyclicBarrier.await();
            }
            catch (InterruptedException | BrokenBarrierException e){
                e.printStackTrace();
            }
        }
    }

    public void start() {
        for (final Runnable worker : workers) {
            new Thread(worker).start();
        }
    }
}