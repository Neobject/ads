package lab5;

class TreeNode<K, V> {
    private K key;
    private V value;
    private TreeNode<K, V> left;
    private TreeNode<K, V> right;

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public TreeNode<K, V> getLeft() {
        return left;
    }

    public TreeNode<K, V> getRight() {
        return right;
    }

    public void setLeft(TreeNode<K, V> left) {
        this.left = left;
    }

    public void setRight(TreeNode<K, V> right) {
        this.right = right;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public TreeNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }
}