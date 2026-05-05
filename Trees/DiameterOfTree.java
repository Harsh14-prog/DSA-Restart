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

public class DiameterOfTree {

    static int ans = 0;   // stores maximum diameter

    // Function to calculate height + update diameter
    public static int height(Node root) {

        if (root == null) return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // at each node we are calculate diameter(no.of nodes) passing through that node
        // and stored no.of nodes in that diameter in ans

        // update diameter (max path through current node)
        ans = Math.max(ans, leftHeight + rightHeight + 1);  // stored no.of nodes in diameter

        // return height of current node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {

        // Manual Tree
        /*
                1
               / \
              2   3
             / \    
            4   5   
           /     \
          7       6
                 /
                8
        */

        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.left.left.left = new Node(7);

        root.right.right = new Node(6);
        root.right.right.left = new Node(8);

        ans = 0;           
        height(root);      

        System.out.println("Diameter: " + ans);
    }
}