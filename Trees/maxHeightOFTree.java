package Trees;

import Trees.Node;

public class maxHeightOFTree {

    public static int height(Node root) {

        // for each node we calculate max height below that nodes
        // then return it to upper node

        if (root == null) {
            return 0;   // no.of nodes returned
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {

        Node root = new Node(1);

        // Level 1
        root.left = new Node(2);
        root.right = new Node(3);

        // Level 2
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        // Level 3
        root.left.left.left = new Node(7);
        root.right.right = new Node(6);

        // Level 4
        root.right.right.left = new Node(8);

        System.out.println(height(root));
    }
}
