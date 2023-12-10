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

}
