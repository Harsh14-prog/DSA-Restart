package Trees;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class IsTreeBalanced {

    /*
     * This function does TWO things at once:
     * 1. Calculates height of the tree
     * 2. Checks if the tree is balanced
     *
     * Trick:
     * - Return HEIGHT if subtree(or current Node) is balanced , 
     * jr current Node i.e subtree ch balanced nahiye tr direct ethunach return -1
     * rather than traversing further nodes
     * - Return -1 if subtree is NOT balanced
     */
    public static int checkHeight(Node root) {

        // Base case: empty tree has height 0 and is balanced
        if (root == null) return 0;

        // Recursively get left subtree height
        int leftHeight = checkHeight(root.left);

        // If left subtree is already unbalanced, stop immediately
        if (leftHeight == -1) return -1;

        // Recursively get right subtree height
        int rightHeight = checkHeight(root.right);

        // If right subtree is already unbalanced, stop immediately
        if (rightHeight == -1) return -1;

        // Check current node balance condition
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;   // Not balanced
        }

        // If balanced → return height of current node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /*
     * Wrapper function:
     * If checkHeight returns -1 → tree is NOT balanced
     * Otherwise → tree is balanced
     */
    public static boolean isBalanced(Node root) {
        return checkHeight(root) != -1;
    }

    public static void main(String[] args) {

        /*
                Example Tree:

                    1
                   /
                  2
                 /
                3
               /
              4

            This tree is NOT balanced
        */

        Node root = new Node(1);

        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.left.left = new Node(4);

        // 🔹 Final check
        if (isBalanced(root)) {
            System.out.println("Tree is Balanced");
        } else {
            System.out.println("Tree is NOT Balanced");
        }
    }
}