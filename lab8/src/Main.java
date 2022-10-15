import sun.misc.Signal;

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

        Calculator calculator = new Calculator(threadsNumber);

        Signal.handle(new Signal("INT"), sig -> {
            calculator.stopWorking();
            try {
                System.out.println("getting Answer");
                double answer = calculator.getAnswer();
                System.out.println("answer           " + answer);
                System.out.println("true value       " + Math.PI/4);
                System.out.println("value difference " + (Math.PI / 4 - answer));
                calculator.shutDown();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        calculator.Calculate();
    }
}