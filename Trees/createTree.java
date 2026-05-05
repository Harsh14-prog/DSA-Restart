package Trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class createTree {

    static Scanner sc = new Scanner(System.in);

    // Build Tree (Preorder input with -1 as NULL)
    public static Node buildTree(){

        int value = sc.nextInt();

        if (value == -1) {
            return null;
        }

        Node root = new Node(value);

        root.left = buildTree();
        root.right = buildTree();

        return root;
    }

    // 🔹 Preorder
    public static void preOrder(Node root) {
       // on each node do root -> left -> right
        if (root == null)
            return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);  // during backtrack
    }

    // 🔹 Inorder
    public static void inorder(Node root) {
        
        // try to follow left -> root -> right on each
        if (root == null)
            return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // 🔹 Postorder
    public static void postOrder(Node root) {
        if (root == null)
            return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    // 🔹 Level Order 
    public static void levelOrder(Node root) {

        if (root == null)
            return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {

            Node curr = q.poll();
            System.out.print(curr.data + " ");

            if (curr.left != null) {
                q.add(curr.left); 
            }

            if (curr.right != null) {
                q.add(curr.right); 
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Enter nodes (-1 for NULL):");

        Node root = buildTree();

        System.out.print("Preorder: ");
        preOrder(root);

        System.out.print("\nInorder: ");
        inorder(root);

        System.out.print("\nPostorder: ");
        postOrder(root);

        System.out.print("\nLevel Order: ");
        levelOrder(root);
    }
}
