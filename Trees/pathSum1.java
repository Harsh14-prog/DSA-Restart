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

class PathSum1 {

    /*
     * Function checks whether a root-to-leaf
     * path exists with given target sum
     */
    public static boolean hasPathSum(Node root, int targetSum) {

        // 🔹 Base case
        if (root == null) {
            return false;
        }

        // 🔹 Check if current node is LEAF
        // and remaining target becomes equal
        if (root.left == null && root.right == null) {

            return targetSum == root.data;
        }

        /*
         * Reduce target by current node value
         */
        int remainingSum = targetSum - root.data;

        /*
         * Recursively check left OR right subtree
         */
        return hasPathSum(root.left, remainingSum) ||
               hasPathSum(root.right, remainingSum);
    }

    public static void main(String[] args) {

        /*
                    5
                   / \
                  4   8
                 /   / \
                11  13  4
               / \        \
              7   2        1
        */

        Node root = new Node(5);

        root.left = new Node(4);
        root.right = new Node(8);

        root.left.left = new Node(11);

        root.left.left.left = new Node(7);
        root.left.left.right = new Node(2);

        root.right.left = new Node(13);
        root.right.right = new Node(4);

        root.right.right.right = new Node(1);

        int target = 22;

        if (hasPathSum(root, target)) {
            System.out.println("Path Exists");
        } else {
            System.out.println("Path Does NOT Exist");
        }
    }
}