package Trees;

import Trees.Node;

public class IsTreeBalanced {

    public static int checkHeight(Node root) {

        if (root == null) return 0;

        int left = checkHeight(root.left);
        if (left == -1) return -1;

        int right = checkHeight(root.right);
        if (right == -1) return -1;

        // check balance condition
        if (Math.abs(left - right) > 1) return -1;

        return 1 + Math.max(left, right);
    }

    public static boolean isBalanced(Node root) {
        return checkHeight(root) != -1;
    }

    public static void main(String[] args) {

        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.left.left = new Node(5);

        if (isBalanced(root)) {
            System.out.println("Tree is balanced");
        } else {
            System.out.println("Tree is not balanced");
        }
    }
}