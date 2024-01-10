package lab5;

import java.util.function.*;

public class BinaryTree<K extends Comparable<K>, V> {
    private TreeNode<K, V> root;

    public TreeNode<K, V> getRoot() {
        return root;
    }

    public BinaryTree() {
        this.root = null;
    }

    // Додавання елемента в бінарне дерево
    public void insert(K key, V value) {
        root = insertRec(root, key, value);
    }

    private TreeNode<K, V> insertRec(TreeNode<K, V> root, K key, V value) {
        if (root == null) {
            return new TreeNode<>(key, value);
        }

        if (key.compareTo(root.getKey()) < 0) {
            root.setLeft(insertRec(root.getLeft(), key, value));
        } else if (key.compareTo(root.getKey()) > 0) {
            root.setRight(insertRec(root.getRight(), key, value));
        }

        return root;
    }

    // Обхід вузлів в порядку "від вершини до листя"
    public void walkThrow() {
        walkThrow(root, 0);
    }

    // Видалення вузла за умовою Predicate
    public void remove(Predicate<TreeNode<K, V>> condition) {
        root = removeRec(root, condition);
    }

    private TreeNode<K, V> removeRec(TreeNode<K, V> root, Predicate<TreeNode<K, V>> condition) {
        if (root == null) {
            return null;
        }

        if (condition.test(root)) {
            return removeNode(root);
        }

        root.setLeft(removeRec(root.getLeft(), condition));
        root.setRight(removeRec(root.getRight(), condition));

        return root;
    }

    private TreeNode<K, V> removeNode(TreeNode<K, V> node) {
        if (node.getLeft() == null) {
            return node.getRight();
        } else if (node.getRight() == null) {
            return node.getLeft();
        }

        // Вузол з двома дітьми: знаходження мінімального значення у правому піддереві
        TreeNode<K, V> minRight = findMin(node.getRight());
        node.setKey(minRight.getKey());
        node.setValue(minRight.getValue());

        // Видалення мінімального значення
        node.setRight(removeRec(node.getRight(), n -> n == minRight));

        return node;
    }

    private TreeNode<K, V> findMin(TreeNode<K, V> root) {
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root;
    }

    public int where(Predicate<TreeNode<K, V>> condition) {
        return nodesWhereRec(root, condition);
    }

    private int nodesWhereRec(TreeNode<K, V> root, Predicate<TreeNode<K, V>> condition) {
        if (root == null) {
            return 0;
        }

        int count = 0;

        if (condition.test(root)) {
            count++;
        }

        count += nodesWhereRec(root.getLeft(), condition);
        count += nodesWhereRec(root.getRight(), condition);

        return count;
    }

    // Метод для виконання дії над кожним вузлом, що відповідає умові
    public void foreach(Predicate<TreeNode<K, V>> condition, Consumer<TreeNode<K, V>> action) {
        foreachRec(root, condition, action);
    }

    private void foreachRec(TreeNode<K, V> root, Predicate<TreeNode<K, V>> condition, Consumer<TreeNode<K, V>> action) {
        if (root != null) {
            if (condition.test(root)) {
                action.accept(root);
            }
            foreachRec(root.getLeft(), condition, action);
            foreachRec(root.getRight(), condition, action);
        }
    }

    private void walkThrow(TreeNode<K, V> root, int level) {
        if (root != null) {
            System.out.println(level + "| " + "\t".repeat(level) + root.getKey() + " | " + root.getValue());
            walkThrow(root.getLeft(), level + 1);
            walkThrow(root.getRight(), level + 1);
        }
    }
}