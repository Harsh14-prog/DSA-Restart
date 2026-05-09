package Implementations;

class CircularQueue {

    // Circular Queue Class
    static class CircularQ {

        // Total capacity of queue
        int capacity;

        // Array to store queue elements
        int[] nums;

        // Points to first element
        int front;

        // Points to last element
        int rear;

        // Constructor
        CircularQ(int size) {

            capacity = size;

            nums = new int[capacity];

            // Initially queue is empty
            front = -1;
            rear = -1;
        }

        // =========================================================
        // ADD ELEMENT INTO QUEUE
        // =========================================================
        public void add(int val) {

            // CONDITION FOR FULL QUEUE
            // Example:
            // front = 2
            // rear = 1
            //
            // rear+1 becomes front
            // means no space left

            if ((rear + 1) % capacity == front) {
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

                // Circular movement
                rear = (rear + 1) % capacity;
            }

            nums[rear] = val;

            System.out.println(val + " inserted");
        }

        // =========================================================
        // REMOVE ELEMENT FROM QUEUE
        // =========================================================
        public int poll() {

            // EMPTY QUEUE
            if (front == -1) {
                System.out.println("Queue is Empty");
                return -1;
            }

            // Store removed element
            int ele = nums[front];

            // ONLY ONE ELEMENT PRESENT
            if (front == rear) {

                // Reset queue
                front = -1;
                rear = -1;
            }

            // NORMAL REMOVAL
            else {

                // Circular increment
                front = (front + 1) % capacity;
            }

            return ele;
        }

        // =========================================================
        // GET FRONT ELEMENT
        // =========================================================
        public int peek() {

            if (front == -1) {
                System.out.println("Queue is Empty");
                return -1;
            }

            return nums[front];
        }

        // =========================================================
        // CHECK QUEUE IS EMPTY
        // =========================================================
        public boolean isEmpty() {

            return front == -1;
        }

        // =========================================================
        // CHECK QUEUE IS FULL
        // =========================================================
        public boolean isFull() {

            return (rear + 1) % capacity == front;
        }

        // =========================================================
        // DISPLAY QUEUE
        // =========================================================
        public void display() {

            if (front == -1) {
                System.out.println("Queue is Empty");
                return;
            }

            System.out.print("Queue Elements : ");

            int i = front;

            // Traverse till rear
            while (i != rear) {

                System.out.print(nums[i] + " ");

                // Circular movement
                i = (i + 1) % capacity;
            }

            // Print rear element separately
            System.out.print(nums[rear]);

            System.out.println();
        }

        // =========================================================
        // GET SIZE OF QUEUE
        // =========================================================
        public int size() {

            // Empty Queue
            if (front == -1) {
                return 0;
            }

            // If rear ahead of front
            if (rear >= front) {
                return rear - front + 1;
            }

            // Circular wrapped case
            return (capacity - front) + (rear + 1);
        }

        // =========================================================
        // CLEAR COMPLETE QUEUE
        // =========================================================
        public void clear() {

            front = -1;
            rear = -1;

            System.out.println("Queue Cleared");
        }
    }

    // =============================================================
    // MAIN METHOD
    // =============================================================
    public static void main(String[] args) {

        CircularQ cq = new CircularQ(5);

        // ============================
        // INSERT ELEMENTS
        // ============================

        cq.add(10);
        cq.add(20);
        cq.add(30);

        cq.display();

        // ============================
        // PEEK
        // ============================

        System.out.println("Front Element : " + cq.peek());

        // ============================
        // REMOVE ELEMENT
        // ============================

        System.out.println("Removed : " + cq.poll());

        cq.display();

        // ============================
        // INSERT MORE
        // ============================

        cq.add(40);
        cq.add(50);
        cq.add(60);

        cq.display();

        // ============================
        // CHECK SIZE
        // ============================

        System.out.println("Size : " + cq.size());

        // ============================
        // CHECK FULL
        // ============================

        System.out.println("Is Full : " + cq.isFull());

        // ============================
        // REMOVE ALL
        // ============================

        System.out.println(cq.poll());
        System.out.println(cq.poll());
        System.out.println(cq.poll());
        System.out.println(cq.poll());

        // ============================
        // CHECK EMPTY
        // ============================

        System.out.println("Is Empty : " + cq.isEmpty());

        // ============================
        // DISPLAY
        // ============================

        cq.display();

        // ============================
        // CLEAR QUEUE
        // ============================

        cq.clear();
    }
}