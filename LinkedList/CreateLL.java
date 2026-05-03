package LinkedList;

class Node {
    int value;
    Node next;

    Node(int data) {
        this.value = data;
        this.next = null;
    }
}

class SinglyLinkedList {

    Node head;
    Node tail;

    // 🔹 Add at end
    public void addLast(int val) {
        Node newNode = new Node(val);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;
        tail = newNode;
    }

    // 🔹 Add at beginning
    public void addFirst(int val) {
        Node newNode = new Node(val);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }

    // 🔹 Insert at position (0-based)
    public void insertAtPosition(int pos, int val) {

        if (pos < 0 || pos > getLength()) {
            System.out.println("Invalid position");
            return;
        }

        if (pos == 0) {
            addFirst(val);
            return;
        }

        if (pos == getLength()) {
            addLast(val);
            return;
        }

        Node temp = head;

        for (int i = 0; i < pos - 1; i++) {
            temp = temp.next;
        }

        Node newNode = new Node(val);
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // 🔹 Delete first
    public void deleteFirst() {
        if (head == null) return;

        if (head == tail) {
            head = tail = null;
            return;
        }

        head = head.next;
    }

    // 🔹 Delete last
    public void deleteLast() {
        if (head == null) return;

        if (head == tail) {
            head = tail = null;
            return;
        }

        Node temp = head;

        while (temp.next != tail) {
            temp = temp.next;
        }

        tail = temp;
        tail.next = null;
    }

    // 🔹 Delete at position (0-based)
    public void deleteFromPosition(int pos) {

        int len = getLength();

        if (pos < 0 || pos >= len) {
            System.out.println("Invalid position");
            return;
        }

        if (pos == 0) {
            deleteFirst();
            return;
        }

        if (pos == len - 1) {
            deleteLast();
            return;
        }

        Node temp = head;

        for (int i = 0; i < pos - 1; i++) {
            temp = temp.next;
        }

        Node nodeToDelete = temp.next;
        temp.next = nodeToDelete.next;
    }

    // 🔹 Delete middle
    public void deleteMiddle() {

        if (head == null) return;

        if (head.next == null) {
            head = tail = null;
            return;
        }

        Node slow = head;
        Node fast = head;
        Node prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = slow.next;

        if (slow == tail) {
            tail = prev;
        }
    }

    // 🔹 Get middle
    public Node getMiddle() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 🔹 Search
    public boolean search(int target) {
        Node temp = head;

        while (temp != null) {
            if (temp.value == target) return true;
            temp = temp.next;
        }

        return false;
    }

    // 🔹 Reverse (FIXED)
    public void reverseLL() {

        Node prev = null;
        Node curr = head;
        Node oldHead = head;

        while (curr != null) {
            Node forward = curr.next;
            curr.next = prev;

            prev = curr;
            curr = forward;
        }

        head = prev;
        tail = oldHead;
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

    // 🔹 Display
    public void display() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }

        System.out.println("null");
    }
}

public class CreateLL {
    public static void main(String[] args) {

        SinglyLinkedList list = new SinglyLinkedList();

        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);

        list.addFirst(60);
        list.addFirst(70);
        list.addFirst(80);

        list.insertAtPosition(3, 100);

        list.deleteFirst();
        list.deleteLast();

        list.deleteFromPosition(3);

        list.deleteMiddle();

        list.reverseLL();

        list.display();
    }
}