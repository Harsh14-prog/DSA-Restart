package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeftView {

    // Node class for Binary Tree
    static class Node {

        int data;
        Node left;
        Node right;

        Node(int data) {

            this.data = data;
            this.left = null;
            this.right = null;
        }
    }



    // Function to return Left View of Binary Tree
    //
    // Left View means:
    // nodes visible when tree is viewed from LEFT side
    //
    // Logic:
    // First node of every level belongs to left view
    public static List<Integer> leftView(Node root) {

        // List used to store final answer
        List<Integer> ans = new ArrayList<>();


        // Edge case:
        // if tree is empty
        if (root == null) {

            return ans;
        }


        // Queue used for Level Order Traversal (BFS)
        Queue<Node> q = new LinkedList<>();


        // Push root node into queue
        q.offer(root);


        // Run BFS until queue becomes empty
        while (!q.isEmpty()) {


            // Number of nodes present
            // at current level
            int size = q.size();


            // Traverse all nodes of current level
            for (int i = 0; i < size; i++) {


                // Remove node from queue
                Node curr = q.poll();


                // FIRST node of every level
                // belongs to left view
                //
                // Example:
                //
                // Level 0 -> first node = 1
                // Level 1 -> first node = 2
                // Level 2 -> first node = 4
                if (i == 0) {

                    ans.add(curr.data);
                }


                // Push left child into queue
                if (curr.left != null) {

                    q.offer(curr.left);
                }


                // Push right child into queue
                if (curr.right != null) {

                    q.offer(curr.right);
                }
            }
        }

        return ans;
    }



    public static void main(String[] args) {

        /*
                    1
                  /   \
                 2     3
                / \     \
               4   5     6
                  /
                 7


        Left View:
        1 2 4 7
        */


        // Creating tree
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.right = new Node(6);

        root.left.right.left = new Node(7);


        // Calling function
        List<Integer> ans = leftView(root);


        // Printing answer
        System.out.println(ans);
    }
}