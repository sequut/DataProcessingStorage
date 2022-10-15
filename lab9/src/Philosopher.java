class Philosopher implements Runnable {
    private final Lunch lunch;
    private final int right;
    private final int left;
    private final int number;
    public Philosopher(int left, int right, Lunch lunch, int number){
        this.left = left;
        this.right = right;
        this.lunch = lunch;
        this.number = number;
    }
    @Override
    public void run(){
        lunch.lunching(left, right, number);
    }
}