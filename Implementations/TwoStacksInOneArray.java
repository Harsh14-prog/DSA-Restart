package Implementations;

public class TwoStacksInOneArray {

    // =========================================================
    // TWO STACK CLASS
    // =========================================================
    static class TwoStacks {

        // Array to store both stacks
        int[] arr;

        // Top pointer for stack 1
        int top1;

        // Top pointer for stack 2
        int top2;

        // Maximum capacity
        int capacity;

        // =====================================================
        // CONSTRUCTOR
        // =====================================================
        TwoStacks(int size) {

            capacity = size;

            arr = new int[capacity];

            // Stack 1 starts from left
            top1 = -1;

            // Stack 2 starts from right
            top2 = capacity;
        }

        // =====================================================
        // PUSH INTO STACK 1
        // =====================================================
        public void push1(int val) {

            // OVERFLOW CONDITION
            // No space left between stacks
            if (top1 + 1 == top2) {

                System.out.println("Stack Overflow");

                return;
            }

            // Move top1 and insert element
            arr[++top1] = val;

            System.out.println(val + " pushed into Stack 1");
        }

        // =====================================================
        // PUSH INTO STACK 2
        // =====================================================
        public void push2(int val) {

            // OVERFLOW CONDITION
            if (top1 + 1 == top2) {

                System.out.println("Stack Overflow");

                return;
            }

            // Move top2 backward and insert element
            arr[--top2] = val;

            System.out.println(val + " pushed into Stack 2");
        }

        // =====================================================
        // POP FROM STACK 1
        // =====================================================
        public int pop1() {

            // UNDERFLOW
            if (top1 == -1) {

                System.out.println("Stack 1 is Empty");

                return -1;
            }

            // Return top element then decrease top1
            return arr[top1--];
        }

        // =====================================================
        // POP FROM STACK 2
        // =====================================================
        public int pop2() {

            // UNDERFLOW
            if (top2 == capacity) {

                System.out.println("Stack 2 is Empty");

                return -1;
            }

            // Return top element then increase top2
            return arr[top2++];
        }

        // =====================================================
        // PEEK STACK 1
        // =====================================================
        public int peek1() {

            if (top1 == -1) {

                System.out.println("Stack 1 is Empty");

                return -1;
            }

            return arr[top1];
        }

        // =====================================================
        // PEEK STACK 2
        // =====================================================
        public int peek2() {

            if (top2 == capacity) {

                System.out.println("Stack 2 is Empty");

                return -1;
            }

            return arr[top2];
        }

        // =====================================================
        // DISPLAY STACK 1
        // =====================================================
        public void display1() {

            if (top1 == -1) {

                System.out.println("Stack 1 is Empty");

                return;
            }

            System.out.println("Stack 1 Elements:");

            for (int i = top1; i >= 0; i--) {

                System.out.println(arr[i]);
            }
        }

        // =====================================================
        // DISPLAY STACK 2
        // =====================================================
        public void display2() {

            if (top2 == capacity) {

                System.out.println("Stack 2 is Empty");

                return;
            }

            System.out.println("Stack 2 Elements:");

            for (int i = top2; i < capacity; i++) {

                System.out.println(arr[i]);
            }
        }

        // =====================================================
        // DISPLAY COMPLETE ARRAY
        // =====================================================
        public void displayArray() {

            System.out.println("Complete Array:");

            for (int i = 0; i < capacity; i++) {

                System.out.print(arr[i] + " ");
            }

            System.out.println();
        }
    }

    // =========================================================
    // MAIN METHOD
    // =========================================================
    public static void main(String[] args) {

        TwoStacks ts = new TwoStacks(10);

        // ==========================
        // PUSH INTO STACK 1
        // ==========================

        ts.push1(10);
        ts.push1(20);
        ts.push1(30);

        // ==========================
        // PUSH INTO STACK 2
        // ==========================

        ts.push2(100);
        ts.push2(200);
        ts.push2(300);

        // ==========================
        // DISPLAY BOTH STACKS
        // ==========================

        ts.display1();

        ts.display2();

        // ==========================
        // PEEK
        // ==========================

        System.out.println("Top of Stack1 : " + ts.peek1());

        System.out.println("Top of Stack2 : " + ts.peek2());

        // ==========================
        // POP
        // ==========================

        System.out.println("Removed from Stack1 : " + ts.pop1());

        System.out.println("Removed from Stack2 : " + ts.pop2());

        // ==========================
        // DISPLAY COMPLETE ARRAY
        // ==========================

        ts.displayArray();
    }
}