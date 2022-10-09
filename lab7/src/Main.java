import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
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
        Calculator calculator = new Calculator(threadsNumber, 20000);
        System.out.println(calculator.Calculate());
        System.out.println(Math.PI/4);
    }
}