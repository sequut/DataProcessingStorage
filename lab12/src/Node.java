public class Node {
    Node nextNode;
    String value;

    public Node(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
