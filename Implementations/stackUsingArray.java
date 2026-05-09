package Implementations;

class StackUsingArray {

    // =========================================================
    // STACK CLASS
    // =========================================================
    static class Stack {

        // Array to store stack elements
        int[] arr;

        // Points to top element
        int top;

        // Maximum capacity of stack
        int capacity;

        // =====================================================
        // CONSTRUCTOR
        // =====================================================
        Stack(int size) {

            capacity = size;

            arr = new int[capacity];

            // Initially stack is empty
            top = -1;
        }

        // =====================================================
        // PUSH OPERATION
        // Insert element into stack
        // =====================================================
        public void push(int val) {

            // STACK OVERFLOW
            // No more space available
            if (top == capacity - 1) {

                System.out.println("Stack Overflow");

                return;
            }

            // Move top and insert element
            arr[++top] = val;

            System.out.println(val + " pushed into stack");
        }

        // =====================================================
        // POP OPERATION
        // Remove top element
        // =====================================================
        public int pop() {

            // STACK UNDERFLOW
            // Stack already empty
            if (top == -1) {

                System.out.println("Stack Underflow");

                return -1;
            }

            // Return top element then decrease top
            return arr[top--];
        }

        // =====================================================
        // PEEK OPERATION
        // Return top element without removing
        // =====================================================
        public int peek() {

            // Empty stack
            if (top == -1) {

                System.out.println("Stack is Empty");

                return -1;
            }

            return arr[top];
        }

        // =====================================================
        // CHECK STACK IS EMPTY
        // =====================================================
        public boolean isEmpty() {

            return top == -1;
        }

        // =====================================================
        // CHECK STACK IS FULL
        // =====================================================
        public boolean isFull() {

            return top == capacity - 1;
        }

        // =====================================================
        // GET SIZE OF STACK
        // =====================================================
        public int size() {

            return top + 1;
        }

        // =====================================================
        // DISPLAY STACK
        // =====================================================
        public void display() {

            // Empty stack
            if (top == -1) {

                System.out.println("Stack is Empty");

                return;
            }

            System.out.println("Stack Elements:");

            // Print from top to bottom
            for (int i = top; i >= 0; i--) {

                System.out.println(arr[i]);
            }
        }

        // =====================================================
        // CLEAR STACK
        // =====================================================
        public void clear() {

            top = -1;

            System.out.println("Stack Cleared");
        }
    }

    // =========================================================
    // MAIN METHOD
    // =========================================================
    public static void main(String[] args) {

        Stack st = new Stack(5);

        st.push(10);
        st.push(20);
        st.push(30);

        st.display();

        System.out.println("Top Element : " + st.peek());

        System.out.println("Removed : " + st.pop());

        st.display();

        System.out.println("Size : " + st.size());

        System.out.println("Is Empty : " + st.isEmpty());

        System.out.println("Is Full : " + st.isFull());

        st.clear();

        st.display();
    }
}