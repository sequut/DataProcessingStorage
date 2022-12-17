import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        Scanner scanner = new Scanner(System.in);
        String input;
        Thread sorter = new Thread(new Sorter(linkedList));
        sorter.start();

        while (true){
            input = scanner.nextLine();
            if (input.equals(""))
                linkedList.printList();
            else {
                if (input.length() > 80){
                    String helpString = input.replaceAll("(.{80})", "$1|");
                    String[] strings = helpString.split("\\|");
                    for (String i : strings)
                        linkedList.insertElement(i);
                }
                else
                    linkedList.insertElement(input);
            }
        }
    }
}