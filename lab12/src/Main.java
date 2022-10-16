import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Object lock = new Object();

        Scanner scanner = new Scanner(System.in);
        String input;
        Thread sorter = new Thread(new Sorter(lock));
        sorter.start();

        while (true){
            input = scanner.nextLine();
            if (input.equals(""))
                System.out.println(Sorter.linkedList);
            else {
                if (input.length() > 80){
                    String helpString = input.replaceAll("(.{80})", "$1|");
                    String[] strings = helpString.split("\\|");
                    for (String i : strings)
                        Sorter.linkedList.addFirst(i);
                }
                else
                    Sorter.linkedList.addFirst(input);
            }
        }
    }
}