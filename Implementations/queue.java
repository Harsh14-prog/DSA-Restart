package Implementations;

public class queue {

    int front;
    int rear;
    int[] nums;
    int capacity;

    queue(int size) {
        front = -1;
        rear = -1;
        nums = new int[size];
        capacity = size;
    }

    public void add(int val) {

        if (rear == capacity - 1) {
            System.out.println("overflow");
            return;
        }

        if (front == -1) {
            front = 0;
        }

        rear++;
        nums[rear] = val;
    }

    public int poll() {

        if (front == -1 || front > rear) {
            System.out.println("underflow");
            return -1;
        }

        int item = nums[front];
        front++;

        return item;
    }
    
    // front always point to 1st inserted ele
    // if it is still -1 means queue is empty not any ele inserted yet
    
    public boolean isEmpty() {
        return (front == -1 || front > rear);
    }

    public void display(){
        if(front == -1 || front > capacity) {
            System.out.println("queue is empty");
            return ;
        }

        for(int i = front ; i <= rear ; i++){
            System.out.print(nums[i] + " ");
        }
    }

    public static void main(String[] args) {

        queue q = new queue(7);

        q.add(10);
        q.add(20);
        q.add(30);
        q.add(40);
        q.add(50);
        q.add(60);
        q.add(70);

        System.out.println(q.poll());
        System.out.println(q.poll());

        System.out.println(q.isEmpty());

        q.display();
    }
}