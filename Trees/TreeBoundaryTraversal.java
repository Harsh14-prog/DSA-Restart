package Trees;

import java.util.ArrayList;
import java.util.List;

public class TreeBoundaryTraversal {

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


    // Function to check whether node is leaf node
    //
    // Leaf node means:
    // node having NO left child and NO right child
    public static boolean isLeaf(Node root) {

        return root.left == null && root.right == null;
    }



    // Function to add LEFT boundary nodes
    //
    // IMPORTANT:
    // We do NOT add leaf nodes here
    // because leaf nodes will be added separately
    public static void addLeftBoundary(Node root,
                                       ArrayList<Integer> ans) {

        // Start traversal from left child of root
        Node curr = root.left;

        // Traverse boundary
        while (curr != null) {

            // Add only NON-LEAF nodes
            //
            // If leaf nodes added here,
            // they will get duplicated later
            if (!isLeaf(curr)) {

                ans.add(curr.data);
            }

            // Boundary traversal prefers LEFT child first
            if (curr.left != null) {

                curr = curr.left;
            }

            // If left child absent,
            // move to right child
            //
            // Example:
            //
            //      1
            //     /
            //    2
            //     \
            //      3
            //
            // Here 3 still lies on boundary path
            else {

                curr = curr.right;
            }
        }
    }



    // Function to add ALL leaf nodes
    //
    // We use DFS traversal
    public static void addLeafNodes(Node root,
                                    ArrayList<Integer> ans) {

        // Base case
        if (root == null) {

            return;
        }

        // If current node is leaf node,
        // directly add into answer
        if (isLeaf(root)) {

            ans.add(root.data);

            return;
        }

        // Traverse left subtree
        addLeafNodes(root.left, ans);

        // Traverse right subtree
        addLeafNodes(root.right, ans);
    }



    // Function to add RIGHT boundary nodes
    //
    // Right boundary must be printed
    // in BOTTOM-UP order
    public static void addRightBoundary(Node root,
                                        ArrayList<Integer> ans) {

        // Start from right child of root
        Node curr = root.right;

        // Temporary list used because
        // we need reverse order later
        List<Integer> temp = new ArrayList<>();


        while (curr != null) {

            // Add only NON-LEAF nodes
            //
            // Leaf nodes already handled separately
            if (!isLeaf(curr)) {

                temp.add(curr.data);
            }

            // Prefer RIGHT child first
            if (curr.right != null) {

                curr = curr.right;
            }

            // If right absent,
            // move to left child
            else {

                curr = curr.left;
            }
        }


        // Right boundary should come
        // from bottom to top
        //
        // Example:
        // temp = [3, 6]
        //
        // Required:
        // 6 3
        for (int i = temp.size() - 1; i >= 0; i--) {

            ans.add(temp.get(i));
        }
    }



    // Main boundary traversal function
    public static ArrayList<Integer> boundaryTraversal(Node root) {

        ArrayList<Integer> ans = new ArrayList<>();


        // Edge case:
        // empty tree
        if (root == null) {

            return ans;
        }


        // Add root node ONLY if root is not leaf
        //
        // Why?
        //
        // If tree contains single node:
        //
        //      1
        //
        // Then root is also leaf node
        //
        // If added here and later in leaf traversal,
        // duplication occurs
        if (!isLeaf(root)) {

            ans.add(root.data);
        }


        // Step 1:
        // Add left boundary
        addLeftBoundary(root, ans);


        // Step 2:
        // Add all leaf nodes
        addLeafNodes(root, ans);


        // Step 3:
        // Add right boundary in reverse order
        addRightBoundary(root, ans);


        return ans;
    }



    public static void main(String[] args) {

        /*
                    1
                  /   \
                 2     3
                / \     \
               4   5     6
                  / \
                 7   8

        Boundary Traversal:
        1 2 4 7 8 6 3
        */


        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.left.right.left = new Node(7);
        root.left.right.right = new Node(8);

        root.right.right = new Node(6);


        ArrayList<Integer> ans = boundaryTraversal(root);

        System.out.println(ans);
    }
}