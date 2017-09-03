package study.algrithm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class BinaryTree<K extends Comparable<K>, V> {

    private Node<K, V> root;

    public BinaryTree() {

    }

    public BinaryTree(Node<K, V> root) {
        this.root = root;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node<K, V> node, K key) {
        if(null == node) return null;
        int compareTo = key.compareTo(node.getKey());
        if(0 == compareTo) {
            return node.getValue();
        } else if(compareTo > 0) {
            return get(node.getRight(), key);
        } else if(compareTo < 0) {
            return get(node.getLeft(), key);
        }
        return null;
    }

    public Node<K, V> put(K key, V value) {
        return put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if(null == node) {
            return new Node<K, V>(key, value);
        }
        int compareTo = key.compareTo(node.getKey());
        if(0 == compareTo) {
            node.setValue(value);
        } else if(compareTo > 0) {
            node.setRight(put(node.getRight(), key, value));
        } else if(compareTo < 0) {
            node.setLeft(put(node.getLeft(), key, value));
        }
        return node;
    }

    static class Node<K, V> {
        private Node<K, V> left;
        private Node<K, V> right;

        private K key;
        private V value;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node<K, V> getLeft() {
            return left;
        }

        public void setLeft(Node<K, V> left) {
            this.left = left;
        }

        public Node<K, V> getRight() {
            return right;
        }

        public void setRight(Node<K, V> right) {
            this.right = right;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "{\""+ key + "\": \""+value+"\", \"left\":"+(null == left ? "\"\"" : left.toString())+", \"right\":"+(null == right ? "\"\"" : right.toString())+"}";
        }
    }

    public static void main(String[] args) {
        Node<Integer, Integer> root = new Node<Integer, Integer>(12, 12);
        BinaryTree<Integer, Integer> binaryTree = new BinaryTree<Integer, Integer>(root);
        binaryTree.put(8, 8);
        binaryTree.put(5, 5);
        binaryTree.put(6, 6);
        binaryTree.put(19, 19);
        binaryTree.put(15, 15);
        binaryTree.put(13, 13);
        binaryTree.put(21, 21);
        binaryTree.put(20, 20);
        binaryTree.put(28, 28);
        System.out.println(binaryTree.root.toString());

        System.out.println(binaryTree.get(8));
    }

}
