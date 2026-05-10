package Implementations;

// =========================================================
// NODE CLASS
// =========================================================
class Node {

    int data;
    Node next;

    Node(int val) {

        this.data = val;
        this.next = null;
    }
}

// =========================================================
// QUEUE USING LINKED LIST
// =========================================================
class queueUsingLL {

    // Front pointer
    Node front;

    // Rear pointer
    Node rear;

    // Stores number of elements
    int count;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================
    queueUsingLL() {

        front = null;

        rear = null;

        count = 0;
    }

    // =====================================================
    // ENQUEUE / ADD
    // Insert element at rear
    // =====================================================
    public void add(int val) {

        // Create new node
        Node newNode = new Node(val);

        // Queue empty
        if (front == null) {

            front = newNode;

            rear = newNode;
        }

        // Normal insertion
        else {

            // Rear points to new node
            rear.next = newNode;

            // Move rear forward
            rear = newNode;
        }

        count++;

        System.out.println(val + " inserted");
    }

    // =====================================================
    // DEQUEUE / REMOVE
    // Remove from front
    // =====================================================
    public int poll() {

        // Queue empty
        if (front == null) {

            System.out.println("Queue is Empty");

            return -1;
        }

        // Store removed value
        int removed = front.data;

        // Only one element present
        if (front == rear) {

            front = null;

            rear = null;
        }

        // Normal removal
        else {

            front = front.next;
        }

        count--;

        return removed;
    }

    // =====================================================
    // PEEK FRONT ELEMENT
    // =====================================================
    public int peek() {

        if (front == null) {

            System.out.println("Queue is Empty");

            return -1;
        }

        return front.data;
    }

    // =====================================================
    // CHECK EMPTY
    // =====================================================
    public boolean isEmpty() {

        return front == null;
    }

    // =====================================================
    // RETURN SIZE
    // =====================================================
    public int size() {

        return count;
    }

    // =====================================================
    // DISPLAY QUEUE
    // =====================================================
    public void display() {

        // Empty queue
        if (front == null) {

            System.out.println("Queue is Empty");

            return;
        }

        Node temp = front;

        System.out.println("Queue Elements:");

        while (temp != null) {

            System.out.print(temp.data + " ");

            temp = temp.next;
        }

        System.out.println();
    }

    // =====================================================
    // CLEAR QUEUE
    // =====================================================
    public void clear() {

        front = null;

        rear = null;

        count = 0;

        System.out.println("Queue Cleared");
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {

        queueUsingLL q = new queueUsingLL();

        // ==========================
        // INSERT ELEMENTS
        // ==========================

        q.add(10);
        q.add(20);
        q.add(30);
        q.add(40);

        // ==========================
        // DISPLAY
        // ==========================

        q.display();

        // ==========================
        // PEEK
        // ==========================

        System.out.println("Front Element : " + q.peek());

        // ==========================
        // REMOVE
        // ==========================

        System.out.println("Removed : " + q.poll());

        // ==========================
        // DISPLAY AGAIN
        // ==========================

        q.display();

        // ==========================
        // SIZE
        // ==========================

        System.out.println("Size : " + q.size());

        // ==========================
        // EMPTY CHECK
        // ==========================

        System.out.println("Is Empty : " + q.isEmpty());

        // ==========================
        // CLEAR QUEUE
        // ==========================

        q.clear();

        q.display();
    }
}