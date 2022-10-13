public class Printer implements Runnable {
    private final static Object lock = new Object();
    private final int iterations;

    public Printer(int iterations) {
        this.iterations = iterations;
    }

    public Object getLock(){
        return lock;
    }

    @Override
    public void run() {
        for(int i = 0; i < iterations; i++) {
            System.out.println(i + " from thread");
            synchronized (lock) {
                Main.parentOrder = false;
                lock.notifyAll();
                while (!Main.parentOrder && (i != (iterations - 1))){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}