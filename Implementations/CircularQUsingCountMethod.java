package Implementations;

public class CircularQUsingCountMethod {
    static class CircularQueue {

        // Array to store queue elements
        int[] arr;

        // Points to front element
        int front;

        // Points to rear element
        int rear;

        // Stores current number of elements
        int count;

        // Maximum capacity of queue
        int capacity;

        // =====================================================
        // CONSTRUCTOR
        // =====================================================
        CircularQueue(int size) {

            capacity = size;

            arr = new int[capacity];

            // Queue initially empty
            front = -1;
            rear = -1;

            // No elements present
            count = 0;
        }

        // =====================================================
        // PUSH / ENQUEUE
        // Insert element into circular queue
        // =====================================================
        public void push(int ele) {

            // CHECK QUEUE FULL
            if (isFull()) {

                System.out.println("Queue is Full");

                return;
            }

            // FIRST ELEMENT INSERTION
            if (front == -1) {

                front = 0;
                rear = 0;
            }

            // NORMAL INSERTION
            else {

                // Circular movement of rear
                rear = (rear + 1) % capacity;
            }

            // Insert element
            arr[rear] = ele;

            // Increase count
            count++;

            System.out.println(ele + " inserted");
        }

        // =====================================================
        // POP / DEQUEUE
        // Remove front element
        // =====================================================
        public int pop() {

            // CHECK QUEUE EMPTY
            if (isEmpty()) {

                System.out.println("Queue is Empty");

                return -1;
            }

            // Store removed element
            int removed = arr[front];

            // ONLY ONE ELEMENT PRESENT
            if (count == 1) {

                // Reset queue
                front = -1;
                rear = -1;
            }

            // NORMAL REMOVAL
            else {

                // Circular movement of front
                front = (front + 1) % capacity;
            }

            // Decrease count
            count--;

            return removed;
        }

        // =====================================================
        // PEEK
        // Return front element without removing
        // =====================================================
        public int peek() {

            // Empty queue
            if (isEmpty()) {

                System.out.println("Queue is Empty");

                return -1;
            }

            return arr[front];
        }

        // =====================================================
        // CHECK QUEUE EMPTY
        // =====================================================
        public boolean isEmpty() {

            return count == 0;
        }

        // =====================================================
        // CHECK QUEUE FULL
        // =====================================================
        public boolean isFull() {

            return count == capacity;
        }

        // =====================================================
        // RETURN CURRENT SIZE
        // =====================================================
        public int size() {

            return count;
        }

        // =====================================================
        // DISPLAY QUEUE
        // =====================================================
        public void display() {

            // Queue Empty
            if (isEmpty()) {

                System.out.println("Queue is Empty");

                return;
            }

            System.out.print("Queue Elements : ");

            // Start from front
            int i = front;

            // Traverse count number of elements
            for (int j = 0; j < count; j++) {

                System.out.print(arr[i] + " ");

                // Circular movement
                i = (i + 1) % capacity;
            }

            System.out.println();
        }

        // =====================================================
        // CLEAR COMPLETE QUEUE
        // =====================================================
        public void clear() {

            front = -1;
            rear = -1;
            count = 0;

            System.out.println("Queue Cleared");
        }
    }

    // =========================================================
    // MAIN METHOD
    // =========================================================
    public static void main(String[] args) {

        CircularQueue q = new CircularQueue(5);

        // ==========================
        // INSERT ELEMENTS
        // ==========================

        q.push(10);
        q.push(20);
        q.push(30);

        q.display();

        // ==========================
        // PEEK ELEMENT
        // ==========================

        System.out.println("Front Element : " + q.peek());

        // ==========================
        // REMOVE ELEMENT
        // ==========================

        System.out.println("Removed : " + q.pop());

        q.display();

        // ==========================
        // INSERT MORE ELEMENTS
        // ==========================

        q.push(40);
        q.push(50);
        q.push(60);

        q.display();

        // ==========================
        // CHECK SIZE
        // ==========================

        System.out.println("Current Size : " + q.size());

        // ==========================
        // CHECK FULL
        // ==========================

        System.out.println("Is Full : " + q.isFull());

        // ==========================
        // REMOVE ALL ELEMENTS
        // ==========================

        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.pop());

        // ==========================
        // CHECK EMPTY
        // ==========================

        System.out.println("Is Empty : " + q.isEmpty());

        // ==========================
        // DISPLAY
        // ==========================

        q.display();

        // ==========================
        // CLEAR QUEUE
        // ==========================

        q.clear();
    }
}
