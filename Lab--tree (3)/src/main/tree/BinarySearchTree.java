package tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private Node root;

    private class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    public BinarySearchTree() {
        root = null;
    }

    public void insereElemento(int data) {
        root = insereRec(root, data);
    }

    private Node insereRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data)
            root.left = insereRec(root.left, data);
        else if (data > root.data)
            root.right = insereRec(root.right, data);

        return root;
    }

    public boolean buscaElemento(int data) {
        return buscaRec(root, data);
    }

    private boolean buscaRec(Node root, int data) {
        if (root == null)
            return false;
        if (root.data == data)
            return true;
        if (data < root.data)
            return buscaRec(root.left, data);
        return buscaRec(root.right, data);
    }

    public int[] emOrdem() {
        List<Integer> nodes = new ArrayList<>();
        emOrdemRec(root, nodes);
        return nodes.stream().mapToInt(Integer::intValue).toArray();
    }

    private void emOrdemRec(Node root, List<Integer> nodes) {
        if (root != null) {
            emOrdemRec(root.left, nodes);
            nodes.add(root.data);
            emOrdemRec(root.right, nodes);
        }
    }

    public int[] preOrdem() {
        List<Integer> nodes = new ArrayList<>();
        preOrdemRec(root, nodes);
        return nodes.stream().mapToInt(Integer::intValue).toArray();
    }

    private void preOrdemRec(Node root, List<Integer> nodes) {
        if (root != null) {
            nodes.add(root.data);
            preOrdemRec(root.left, nodes);
            preOrdemRec(root.right, nodes);
        }
    }

    public int[] posOrdem() {
        List<Integer> nodes = new ArrayList<>();
        posOrdemRec(root, nodes);
        return nodes.stream().mapToInt(Integer::intValue).toArray();
    }

    private void posOrdemRec(Node root, List<Integer> nodes) {
        if (root != null) {
            posOrdemRec(root.left, nodes);
            posOrdemRec(root.right, nodes);
            nodes.add(root.data);
        }
    }

    public void remove(int data) {
        root = removeRec(root, data);
    }

    private Node removeRec(Node root, int data) {
        if (root == null)
            return root;

        if (data < root.data)
            root.left = removeRec(root.left, data);
        else if (data > root.data)
            root.right = removeRec(root.right, data);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);

            root.right = removeRec(root.right, root.data);
        }

        return root;
    }

    private int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }
}
