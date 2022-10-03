public class Main {
    public static void main(String[] args) throws InterruptedException {
        Print thread = new Print();
        thread.start();
        thread.join();
        for (int i = 0; i < 10; i++)
            System.out.println(i + " from main");
    }
}

class Print extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            System.out.println(i + " from thread");
        }
    }
}