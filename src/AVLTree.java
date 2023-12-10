public class AVLTree <T>{
    private T data;
    private AVLTree<T> left;
    private AVLTree<T> right;
    private int height;
    public AVLTree(T data, AVLTree<T> left, AVLTree<T> right, int height) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
    public T getData() {
        return data;
    }

    public AVLTree<T> getLeft() {
        return left;
    }

    public AVLTree<T> getRight() {
        return right;
    }

    public int getHeight() {
        return height;
    }
    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(AVLTree<T> left) {
        this.left = left;
    }

    public void setRight(AVLTree<T> right) {
        this.right = right;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    // Utility method to get the height of a node
    private int height(AVLTree<T> node) {
        return (node == null) ? 0 : node.getHeight();
    }

    // Utility method to find the maximum of two integers
    private int maxHeight(int a, int b) {
        return Math.max(a, b);
    }

    private  AVLTree<T> rightRotate(AVLTree<T> y) {
        // Perform the right rotation
        AVLTree<T> x = y.getLeft();
        AVLTree<T> temp = x.getRight();

        // Perform rotation
        x.setRight(y);
        y.setLeft(temp);

        // Update heights
        y.setHeight(maxHeight(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(maxHeight(height(x.getLeft()), height(x.getRight())) + 1);

        // Return new root
        return x;

    }
    private AVLTree<T> leftRotate(AVLTree<T> x) {
        // Perform the left rotation
        AVLTree<T> y = x.getRight();
        AVLTree<T> temp = y.getLeft();

        // Perform rotation
        y.setLeft(x);
        x.setRight(temp);

        // Update heights
        x.setHeight(maxHeight(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(maxHeight(height(y.getLeft()), height(y.getRight())) + 1);

        // Return new root
        return y;
    }
    private AVLTree<T> leftRightRotate(AVLTree<T> z) {
// Perform the left rotation
        AVLTree<T> y = z.getRight();
        AVLTree<T> temp1 = y.getLeft();

        // Perform rotation
        y.setLeft(temp1);
        z.setRight(y);

        // Update heights
        z.setHeight(maxHeight(height(z.getLeft()), height(z.getRight())) + 1);
        y.setHeight(maxHeight(height(y.getLeft()), height(y.getRight())) + 1);

        // Perform the right rotation
        AVLTree<T> x = z.getRight();
        AVLTree<T> temp2 = x.getLeft();

        // Perform rotation
        x.setLeft(y);
        z.setRight(temp2);

        // Update heights
        y.setHeight(maxHeight(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(maxHeight(height(x.getLeft()), height(x.getRight())) + 1);

        // Return new root
        return x;
    }

}
