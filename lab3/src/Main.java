public class Main implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[4];
        String[] toPrint = new String[]{"aa", "bb", "cc", "dd"};

        for (int i = 0; i < 4; i++){
            threads[i] = new Thread(new Main(toPrint[i]));
            threads[i].start();
        }
        for (int i = 0; i < 4; i++)
            threads[i].join();
    }

    private String text;

    public Main (String text){
        this.text = text;
    }

    public static void myPrint(String text){
        System.out.println(text);
    }

    @Override
    public void run(){
        myPrint(text);
    }
}