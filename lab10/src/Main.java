public class Main {
    private static final int iterations = 20;
    public static void main(String[] args) {
        Printer printer = new Printer(iterations);

        Thread thread = new Thread(printer);
        Object lock = printer.getLock();

        thread.start();
        for (int i = 0; i < iterations; i++){
            System.out.println(i + " from main ##");
            synchronized (lock){
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}