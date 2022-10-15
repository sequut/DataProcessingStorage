import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class Calculator {
    private final int threadsNumber;
    private ExecutorService executorService;
    private ArrayList<Future<Double>> answers;
    private volatile static boolean working = true;
    private double answer = 1.0;

    public Calculator(int threadsNumber){
        this.threadsNumber = threadsNumber;
    }

    public void Calculate(){
        int sign = -1;
        executorService = Executors.newFixedThreadPool(threadsNumber);
        answers = new ArrayList<>();
        for (int i = 0; i < threadsNumber; i++){
            answers.add(i, executorService.submit(new Counter(sign, threadsNumber * 2, 3 + i * 2)));
            sign *= -1;
        }

    }

    public double getAnswer() throws ExecutionException, InterruptedException {
        for (int i = 0; i < threadsNumber; i++)
            answer += answers.get(i).get();
        return answer;
    }

    public void shutDown(){
        executorService.shutdown();
    }

    public void stopWorking(){
        working = false;
    }

    class Counter implements Callable<Double>{
        private int sign;
        private final int delta;
        private final int starting;
        long iterations = Long.MIN_VALUE;
        static final AtomicLong maximumIterations = new AtomicLong(Long.MIN_VALUE);

        public Counter(int sign, int delta, int starting){
            this.sign = sign;
            this.delta = delta;
            this.starting = starting;
        }

        @Override
        public Double call(){
            long rem = starting;
            double answer = 0.0;
            while (working){
                iterations += 1;
                if (sign == 1)
                    answer += (double) 1 / rem;
                else
                    answer -= (double) 1 / rem;
                rem += delta;

                if (threadsNumber % 2 == 1)
                    sign *= -1;

                if (iterations == Long.MAX_VALUE){
                    maximumIterations.set(iterations);
                    working = false;
                    break;
                }
            }

            synchronized (maximumIterations) {
                if (iterations > maximumIterations.get())
                    maximumIterations.set(iterations);
            }

            while (iterations < maximumIterations.get()){
                iterations += 1;
                if (sign == 1)
                    answer += (double) 1 / rem;
                else
                    answer -= (double) 1 / rem;
                rem += delta;

                if (threadsNumber % 2 == 1)
                    sign *= -1;
            }
            return answer;
        }
    }
}