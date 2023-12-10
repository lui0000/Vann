class AVLTree <T>{
    static class Node <T> {

        private T data;
        private Node<T> left;
        private Node<T> right;
        private int height;

        public Node(T data, Node<T> left, Node<T> right, int height) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
        }

        public T getData() {
            return data;
        }

        public Node<T> getLeft() {
            return left;
        }

        public Node<T> getRight() {
            return right;
        }

        public int getHeight() {
            return height;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        // Utility method to get the height of a node
        private int height(Node<T> node) {
            return (node == null) ? 0 : node.getHeight();
        }

        // Utility method to find the maximum of two integers
        private int maxHeight(int a, int b) {
            return Math.max(a, b);
        }

        private Node<T> rightRotate(Node<T> y) {
            // Perform the right rotation
            Node<T> x = y.getLeft();
            Node<T> temp = x.getRight();

            // Perform rotation
            x.setRight(y);
            y.setLeft(temp);

            // Update heights
            y.setHeight(maxHeight(height(y.getLeft()), height(y.getRight())) + 1);
            x.setHeight(maxHeight(height(x.getLeft()), height(x.getRight())) + 1);

            // Return new root
            return x;

        }

        private Node<T> leftRotate(Node<T> x) {
            // Perform the left rotation
            Node<T> y = x.getRight();
            Node<T> temp = y.getLeft();

            // Perform rotation
            y.setLeft(x);
            x.setRight(temp);

            // Update heights
            x.setHeight(maxHeight(height(x.getLeft()), height(x.getRight())) + 1);
            y.setHeight(maxHeight(height(y.getLeft()), height(y.getRight())) + 1);

            // Return new root
            return y;
        }

        private Node<T> leftRightRotate(Node<T> z) {
            // Perform the left rotation
            Node<T> y = z.getRight();
            Node<T> temp1 = y.getLeft();

            // Perform rotation
            y.setLeft(temp1);
            z.setRight(y);

            // Update heights
            z.setHeight(maxHeight(height(z.getLeft()), height(z.getRight())) + 1);
            y.setHeight(maxHeight(height(y.getLeft()), height(y.getRight())) + 1);

            // Perform the right rotation
            Node<T> x = z.getRight();
            Node<T> temp2 = x.getLeft();

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

}
