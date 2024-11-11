class Node {
    Object data;
    Node next;

    public Node(Object data) {
        this.data = data;
        this.next = null; // By default, the next node is null
    }
}

class DoublyNode {
    Object data;
    DoublyNode next;
    DoublyNode prev;

    public DoublyNode(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}