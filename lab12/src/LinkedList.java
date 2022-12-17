public class LinkedList {
    Node firstNode;
    int countNodes;
    public LinkedList(){
        this.firstNode = null;
        countNodes = 0;
    }

    public void insertElement(String value){
        Node node = new Node(value);
        if (firstNode == null)
            firstNode = node;
        else {
            synchronized (firstNode){
                node.nextNode = firstNode;
                firstNode = node;
            }
        }
        countNodes += 1;
    }

    public void printList(){
        if (firstNode == null){
            System.out.println("nothing to print");
            return;
        }

        Node currentNode;
        synchronized (firstNode){
            System.out.print("[");
            currentNode = firstNode;
            while (currentNode != null){
                System.out.print(currentNode);
                currentNode = currentNode.nextNode;
                if (currentNode != null)
                    System.out.print(", ");
                else
                    System.out.println("]");
            }
        }
    }

    public void sort() {
        Node currentNode;
        if (firstNode == null) {
            System.out.println("nothing to sort");
            return;
        }
        synchronized (firstNode) {
            currentNode = firstNode;
            for (int i = countNodes - 1; i >= 1; i--) {
                for (int j = 0; j < i; j++) {
                    if (currentNode.value.compareTo(currentNode.nextNode.value) > 0) {
                        String help = currentNode.value;
                        currentNode.value = currentNode.nextNode.value;
                        currentNode.nextNode.value = help;
                    }
                    currentNode = currentNode.nextNode;
                }
                currentNode = firstNode;
            }
        }
    }
}
