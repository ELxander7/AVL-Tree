package ru.itis.aisd304.avltree;

public class AVLTree {
    Node node;
    int insertOperations;
    int deleteOperations;
    int searchOperations;
    AVLTree(){
        this.node = null;
        this.insertOperations = 0;
        this.deleteOperations = 0;
        this.searchOperations = 0;
    }

    int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }


    public Node rightRotate(Node y) {
        Node x = y.left;
        Node z = x.right;

        x.right = y;
        y.left = z;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    public Node leftRotate(Node x) {
        Node y = x.right;
        Node z = y.left;

        y.left = x;
        x.right = z;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    public Node insertNode(Node node, int key) {
        if (node == null) {
            insertOperations++;
            return new Node(key);
        }
        if (key < node.key) {
            insertOperations++;
            node.left = insertNode(node.left, key);
        } else if (key > node.key) {
            insertOperations++;
            node.right = insertNode(node.right, key);
        } else {
            return node;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public Node minValueNode(Node node) {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    public Node deleteNode(Node node, int key) {
        if (node == null) {
            deleteOperations++;
            return null;
        }
        if (node.key > key) {
            deleteOperations++;
            node.left = deleteNode(node.left, key);
        } else if (node.key < key) {
            deleteOperations++;
            node.right = deleteNode(node.right, key);
        }else {
            if ((node.left == null) || (node.right == null)) {
                Node temp = null;
                deleteOperations++;
                if (node.left == null)
                    temp = node.right;
                else
                    temp = node.left;

                if (temp == null) {
                    temp = node;
                    node = null;
                } else
                    node = temp;
            } else {
                Node temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = deleteNode(node.right, temp.key);
            }
        }

        if (node == null) {
            deleteOperations++;
            return null;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public Node searchNode(Node node, int key) {
        if (node == null || node.key == key) {
            searchOperations++;
            return node;
        }
        searchOperations++;
        if (node.key < key)
            return searchNode(node.right, key);

        return searchNode(node.left, key);
    }
}
