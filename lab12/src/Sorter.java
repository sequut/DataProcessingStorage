public class Sorter implements Runnable{
    LinkedList linkedList;

    public Sorter(LinkedList linkedList){
        this.linkedList = linkedList;
    }

    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            linkedList.sort();
        }
    }
}