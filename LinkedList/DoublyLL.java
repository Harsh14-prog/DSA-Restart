package LinkedList;

class Node {
    int data;
    Node next;
    Node prev;

    Node(int val) {
        this.data = val;
        this.next = null;
        this.prev = null;
    }
}

class DoublyLinkedList {

    Node head;
    Node tail;

    // 🔹 Insert at head
    public void insertAtHead(int val) {
        Node newNode = new Node(val);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        newNode.prev = null;
        head.prev = newNode;
        head = newNode;
    }

    // 🔹 Insert at tail
    public void insertAtTail(int val) {
        Node newNode = new Node(val);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        newNode.next = null;
        tail = newNode;
    }

    // 🔹 Delete head
    public void deleteHead() {
        if (head == null)
            return;

        if (head == tail) {
            head = tail = null;
            return;
        }

        Node forward = head.next;
        head.next = null;
        forward.prev = null;
        head = forward;
    }

    // 🔹 Delete tail
    public void deleteTail() {
        if (head == null)
            return;

        if (head == tail) {
            head = tail = null;
            return;
        }

        tail = tail.prev;
        tail.next = null;
    }

    // 🔹 Length
    public int getLength() {
        int len = 0;
        Node temp = head;

        while (temp != null) {
            len++;
            temp = temp.next;
        }

        return len;
    }

    // 🔹 Insert at position (0-based)
    public void insertAtPosition(int pos, int val) {

        if (pos < 0 || pos > getLength()) {
            System.out.println("Invalid position");
            return;
        }

        if (pos == 0) {
            insertAtHead(val);
            return;
        }

        if (pos == getLength()) {
            insertAtTail(val);
            return;
        }

        Node temp = head;

        for (int i = 0; i < pos - 1; i++) {
            temp = temp.next;
        }

        Node newNode = new Node(val);
        Node forward = temp.next;

        temp.next = newNode;
        newNode.prev = temp;

        newNode.next = forward;
        forward.prev = newNode;
    }

    // 🔹 Delete at position (0-based)
    public void deleteAtPosition(int pos) {

        int len = getLength();

        if (pos < 0 || pos >= len) {
            System.out.println("Invalid position");
            return;
        }

        if (pos == 0) {
            deleteHead();
            return;
        }

        if (pos == len - 1) {
            deleteTail();
            return;
        }

        Node temp = head;

        for (int i = 0; i < pos; i++) {
            temp = temp.next;
        }

        Node prevNode = temp.prev;
        Node nextNode = temp.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        temp.next = null;
        temp.prev = null;
    }

    // 🔹 Reverse DLL
    public void reverseDLL() {

        if (head == null)
            return;

        Node curr = head;
        Node temp = null;

        while (curr != null) {
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;

            curr = curr.prev;
        }

        temp = head;
        head = tail;
        tail = temp;
    }

    // 🔹 Display forward
    public void displayForward() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " ⇄ ");
            temp = temp.next;
        }

        System.out.println("null");
    }

    // 🔹 Display backward
    public void displayBackward() {
        Node temp = tail;

        while (temp != null) {
            System.out.print(temp.data + " ⇄ ");
            temp = temp.prev;
        }

        System.out.println("null");
    }
}

public class DoublyLL {
    public static void main(String[] args) {

        DoublyLinkedList list = new DoublyLinkedList();

        list.insertAtHead(10);
        list.insertAtHead(20);
        list.insertAtHead(30);

        list.insertAtTail(40);
        list.insertAtTail(50);

        list.insertAtPosition(2, 100);

        list.displayForward();
        list.displayBackward();

        list.deleteAtPosition(2);

        list.reverseDLL();

        list.displayForward();
        list.displayBackward();
    }
}