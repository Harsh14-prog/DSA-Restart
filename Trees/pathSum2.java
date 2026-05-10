package Trees;

import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class PathSum2 {

    /*
     * DFS + Backtracking
     */
    public static void findPaths(
            Node root,
            int targetSum,
            List<Integer> currentPath,
            List<List<Integer>> ans
    ) {

        // 🔹 Base case
        if (root == null) {
            return;
        }

        // 🔹 Add current node to path
        currentPath.add(root.data);

        // 🔹 Check leaf node
        if (root.left == null && root.right == null) {

            // valid path found
            if (targetSum == root.data) {

                // add COPY of path to answer
                ans.add(new ArrayList<>(currentPath));
            }
        }

        /*
         * Continue DFS with reduced target
         */
        findPaths(root.left,
                targetSum - root.data,
                currentPath,
                ans);

        findPaths(root.right,
                targetSum - root.data,
                currentPath,
                ans);

        /*
         * BACKTRACK
         * Remove current node before returning
         */
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {

        /*
                    5
                   / \
                  4   8
                 /   / \
                11  13  4
               / \      / \
              7   2    5   1
        */

        Node root = new Node(5);

        root.left = new Node(4);
        root.right = new Node(8);

        root.left.left = new Node(11);

        root.left.left.left = new Node(7);
        root.left.left.right = new Node(2);

        root.right.left = new Node(13);
        root.right.right = new Node(4);

        root.right.right.left = new Node(5);
        root.right.right.right = new Node(1);

        List<List<Integer>> ans = new ArrayList<>();

        findPaths(root,
                22,
                new ArrayList<>(),
                ans);

        System.out.println(ans);
    }
}