package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TopView {

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



    // Pair class used to store:
    // 1. Current node
    // 2. Horizontal Distance (HD)
    //
    // HD rules:
    //
    // Root node -> HD = 0
    // Left child -> HD - 1
    // Right child -> HD + 1
    static class Pair {

        Node node;
        int hd;

        Pair(Node node, int hd) {

            this.node = node;
            this.hd = hd;
        }
    }



    // Function to return Top View of Binary Tree
    public static List<Integer> topView(Node root) {

        // List used to store final answer
        List<Integer> ans = new ArrayList<>();


        // Edge case:
        // if tree is empty
        if (root == null) {

            return ans;
        }


        // TreeMap stores:
        // HD -> node value
        //
        // Why TreeMap?
        //
        // Because it automatically keeps
        // keys sorted from left to right
        //
        // Example:
        // -2 -> 4
        // -1 -> 2
        //  0 -> 1
        //  1 -> 3
        //
        // Output becomes:
        // 4 2 1 3
        Map<Integer, Integer> map = new TreeMap<>();


        // Queue used for BFS traversal
        Queue<Pair> q = new LinkedList<>();


        // Root node has HD = 0
        q.offer(new Pair(root, 0));


        // Run BFS traversal
        while (!q.isEmpty()) {

            // Remove front pair
            Pair curr = q.poll();

            Node node = curr.node;
            int hd = curr.hd;


            // Add node ONLY IF
            // this HD is encountered first time
            //
            // Why?
            //
            // First node at every HD vertical line
            // is visible from TOP
            //
            // Any later node at same HD
            // gets hidden behind top node
            if (!map.containsKey(hd)) {

                map.put(hd, node.data);
            }


            // Push LEFT child
            //
            // Horizontal distance decreases by 1
            if (node.left != null) {

                q.offer(new Pair(node.left, hd - 1));
            }


            // Push RIGHT child
            //
            // Horizontal distance increases by 1
            if (node.right != null) {

                q.offer(new Pair(node.right, hd + 1));
            }
        }


        // TreeMap automatically gives
        // values in sorted order of HD
        for (int value : map.values()) {

            ans.add(value);
        }

        return ans;
    }



    public static void main(String[] args) {

        /*
                        1
                      /   \
                     2     3
                    / \   / \
                   4   5 6   7


            Horizontal Distances:

                    1 -> 0

             2 -> -1     3 -> +1

        4 -> -2   5 -> 0   6 -> 0   7 -> +2


        Top View:
        4 2 1 3 7
        */


        // Creating Binary Tree
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);


        // Calling function
        List<Integer> ans = topView(root);


        // Printing answer
        System.out.println(ans);
    }
}