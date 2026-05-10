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

public class LowestCommonAncestor {

    /*
     * Function to find LCA of two nodes
     */
    public static Node lca(Node root, int p, int q) {

        // 🔹 Base case
        if (root == null) {
            return null;
        }

        // 🔹 If current node matches p or q
        // return current node upward
        if (root.data == p || root.data == q) {
            return root;
        }

        // 🔹 Search in left subtree
        Node left = lca(root.left, p, q);

        // 🔹 Search in right subtree
        Node right = lca(root.right, p, q);

        /*
         * CASE 1:
         * If both left and right are non-null
         * → p found on one side
         * → q found on other side
         * So current node is LCA
         */
        if (left != null && right != null) {
            return root;
        }

        /*
         * CASE 2:
         * If only left side returned something
         * → both nodes are in left subtree
         */
        if (left != null) {
            return left;
        }

        /*
         * CASE 3:
         * If only right side returned something
         * → both nodes are in right subtree
         */
        return right;
    }

    public static void main(String[] args) {

        /*
                    1
                   / \
                  2   3
                 / \
                4   5
        */

        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        Node ans = lca(root, 4, 5);

        System.out.println("LCA: " + ans.data);
    }
}