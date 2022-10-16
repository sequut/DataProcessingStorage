import java.util.LinkedList;

public class Sorter implements Runnable{

    volatile static LinkedList<String> linkedList = new LinkedList<>();
    private final Object lock;

    public Sorter(Object lock){
        this.lock = lock;
    }

    private void sort(){
        synchronized (lock){
            for (int i = 0; i < linkedList.size() - 1; i++)
                for (int j = 0; j < linkedList.size() - i - 1; j++)
                    if (linkedList.get(j).compareTo(linkedList.get(j + 1)) > 0) {
                        String help = linkedList.get(j + 1);
                        linkedList.set(j + 1, linkedList.get(j));
                        linkedList.set(j, help);
                    }
        }
    }

    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sort();
        }
    }
}
