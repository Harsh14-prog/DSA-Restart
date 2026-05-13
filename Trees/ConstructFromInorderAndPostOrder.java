
package Trees;

import java.util.HashMap;
import java.util.Map;

public class ConstructFromInorderAndPostOrder {

    // Must be global
    static int postIndex;

    public static Node constructTree(
            int[] postorder,
            int[] inorder,
            int start,
            int end,
            Map<Integer, Integer> mp) {

        // Base case
        if (start > end) {
            return null;
        }

        // preorder element becomes root
        Node root = new Node(postorder[postIndex--]);

        // find root in inorder
        int idx = mp.get(root.data);

        // build right subtree
        root.right = constructTree(
                postorder,
                inorder,
                idx + 1,
                end,
                mp);

        // build left subtree
        root.left = constructTree(
                postorder,
                inorder,
                start,
                idx - 1,
                mp);

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
        int[] inorder = { 4, 2, 5, 1, 3 };
        int[] postOrder = { 4, 5, 2, 3, 1 };

        int idx = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int ele : inorder) {
            mp.put(ele, idx++);
        }

        postIndex = postOrder.length - 1;

        Node root = constructTree(
                postOrder,
                inorder,
                0,
                inorder.length - 1,
                mp);

        inorderPrint(root);

    }
}