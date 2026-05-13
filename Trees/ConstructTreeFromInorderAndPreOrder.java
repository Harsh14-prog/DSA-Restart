package Trees;

import java.util.HashMap;
import java.util.Map;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}

public class ConstructTreeFromInorderAndPreOrder {

    // Must be global
    static int preIndex = 0;

    public static Node constructTree(
            int[] preorder,
            int[] inorder,
            int start,
            int end,
            Map<Integer , Integer> mp
        ) {

        // Base case
        if (start > end) {
            return null;
        }

        // preorder element becomes root
        Node root = new Node(preorder[preIndex++]);

        // find root in inorder
        int idx = mp.get(root.data);

        // build left subtree
        root.left = constructTree(
                preorder,
                inorder,
                start,
                idx - 1,
                mp
            );

        // build right subtree
        root.right = constructTree(
                preorder,
                inorder,
                idx + 1,
                end,
                mp
            );

        return root;
    }

    // inorder traversal for checking
    public static void inorderPrint(Node root) {

        if (root == null)
            return;

        inorderPrint(root.left);
        System.out.print(root.data + " ");
        inorderPrint(root.right);
    }

    public static void main(String[] args) {

        int[] preorder = { 1, 2, 4, 5, 3 };
        int[] inorder = { 4, 2, 5, 1, 3 };

        int idx = 0 ;
        Map <Integer , Integer> mp = new HashMap<>();
        for(int ele : inorder){
            mp.put(ele , idx++);
        }

        Node root = constructTree(
                preorder,
                inorder,
                0,
                inorder.length - 1,
                mp
            );

        inorderPrint(root);
    }
}