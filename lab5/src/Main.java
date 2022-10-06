import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        Timer timer = new Timer();
        Thread thread = new Thread(new Print());

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                thread.start();
            }
        }, 0);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                thread.interrupt();
                timer.cancel();
            }
        }, 2000);
    }
}

class Print implements Runnable {
    @Override
    public void run(){
        while (!Thread.interrupted())
            System.out.print("Dd");
        System.out.println("\nThread interrupted");
    }
}