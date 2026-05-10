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
// STACK USING LINKED LIST
// =========================================================
 class StackUsingLL {

    // Top pointer of stack
    Node top = null;

    // Stores number of elements
    int count = 0;

    // =====================================================
    // PUSH OPERATION
    // =====================================================
    public void push(int val) {

        // Create new node
        Node newNode = new Node(val);

        // If stack empty
        if (top == null) {

            top = newNode;
        }

        // Otherwise insert at beginning
        else {

            newNode.next = top;

            top = newNode;
        }

        count++;

        System.out.println(val + " pushed");
    }

    // =====================================================
    // POP OPERATION
    // =====================================================
    public int pop() {

        // Stack empty
        if (top == null) {

            System.out.println("Stack is Empty");

            return -1;
        }

        // Store removed value
        int removed = top.data;

        // Move top forward
        top = top.next;

        count--;

        return removed;
    }

    // =====================================================
    // PEEK OPERATION
    // =====================================================
    public int peek() {

        if (top == null) {

            System.out.println("Stack is Empty");

            return -1;
        }

        return top.data;
    }

    // =====================================================
    // CHECK EMPTY
    // =====================================================
    public boolean isEmpty() {

        return top == null;
    }

    // =====================================================
    // RETURN SIZE
    // =====================================================
    public int size() {

        return count;
    }

    // =====================================================
    // DISPLAY STACK
    // =====================================================
    public void display() {

        if (top == null) {

            System.out.println("Stack is Empty");

            return;
        }

        Node temp = top;

        System.out.println("Stack Elements:");

        while (temp != null) {

            System.out.println(temp.data);

            temp = temp.next;
        }
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {

        StackUsingLL st = new StackUsingLL();

        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);
        st.push(50);

        st.display();

        System.out.println();

        System.out.println("Top Element : " + st.peek());

        System.out.println("Removed : " + st.pop());

        System.out.println();

        st.display();

        System.out.println();

        System.out.println("Current Size : " + st.size());

        System.out.println("Is Empty : " + st.isEmpty());
    }
}