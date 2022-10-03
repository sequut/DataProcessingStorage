public class Main {
    public static void main(String[] args) throws InterruptedException {
        Threads[] threads = new Threads[4];
        String[] toPrint = new String[]{"aa", "bb", "cc", "dd"};

        for (int i = 0; i < 4; i++){
            threads[i] = new Threads(toPrint[i]);
            threads[i].start();
        }
        for (int i = 0; i < 4; i++)
            threads[i].join();
    }

    public static class Threads extends Thread {
        private final String pechat;
        Threads(String pechat){
            this.pechat = pechat;
        }

        @Override
        public synchronized void run(){
            System.out.println(pechat);
        }
    }
}