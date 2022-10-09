import java.util.ArrayList;
import java.util.concurrent.*;

public class Calculator {
    private final int threadsNumber;
    private final int iterations;
    public Calculator(int threadsNumber, int iterations){
        this.threadsNumber = threadsNumber;
        this.iterations = iterations;
    }

    public double Calculate() throws ExecutionException, InterruptedException {
        double answer = 1;
        int sign = -1;
        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);
        ArrayList<Future<Double>> answers = new ArrayList<>();
        for (int i = 0; i < threadsNumber; i++){
            answers.add(i, executorService.submit(new Counter(sign, threadsNumber * 2, 3 + i * 2, iterations)));
            sign *= -1;
        }

        executorService.shutdown();

        for (int i = 0; i < threadsNumber; i++)
            answer += answers.get(i).get();
        return answer;
    }

    class Counter implements Callable{
        private int sign;
        private final int delta;
        private final int starting;
        private final int iterations;

        public Counter(int sign, int delta, int starting, int iterations){
            this.sign = sign;
            this.delta = delta;
            this.starting = starting;
            this.iterations = iterations;
        }

        @Override
        public Double call(){
            long rem = starting;
            double answer = 0.0;
            for (int i = 0; i < iterations; i++){
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