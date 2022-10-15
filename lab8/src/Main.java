import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args){
        int threadsNumber;

        if (args.length < 1){
            System.out.println("enter the number of threads");
            return;
        }
        try {
            threadsNumber = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e){
            System.out.println("invalid input parameter");
            return;
        }

        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadsNumber);
        Calculator calculator = new Calculator(threadsNumber, cyclicBarrier);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("getting Answer");
            double answer;
            try {
                calculator.stopWorking();
                answer = calculator.getAnswer();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("answer           " + answer);
            System.out.println("true value       " + Math.PI / 4);
            System.out.println("value difference " + (Math.PI / 4 - answer));
            calculator.shutDown();
        }));

        calculator.Calculate();
    }
}