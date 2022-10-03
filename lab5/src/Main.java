public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Print());
        thread.start();

        Thread.sleep(2000);
        thread.interrupt();
    }
}

class Print implements Runnable {
    @Override
    public void run(){
        while (!Thread.interrupted())
            System.out.print("Dd");
        System.out.println("\nthread interrupted");
    }
}