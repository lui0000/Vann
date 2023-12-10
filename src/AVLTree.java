class AVLTree <T extends Comparable<T>>{
    static class Node<T extends Comparable<T>>{
        private T data;
        private Node<T> left;
        private Node<T> right;
        private int height;

        public Node(T data, Node<T> left, Node<T> right, int height) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1 + Math.max(height(left), height(right));;
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
        private Node<T> rightLeftRotate(Node<T> z) {
            // Perform the right rotation
            Node<T> x = z.getLeft();
            Node<T> temp = x.getRight();

            // Perform rotation
            x.setRight(z);
            z.setLeft(temp);

            // Update heights
            z.setHeight(maxHeight(height(z.getLeft()), height(z.getRight())) + 1);
            x.setHeight(maxHeight(height(x.getLeft()), height(x.getRight())) + 1);

            // Perform the left rotation
            Node<T> y = z.getRight();
            Node<T> temp1 = y.getLeft();

            // Perform rotation
            y.setLeft(temp1);
            z.setRight(y);

            // Update heights
            z.setHeight(maxHeight(height(z.getLeft()), height(z.getRight())) + 1);
            y.setHeight(maxHeight(height(y.getLeft()), height(y.getRight())) + 1);

            // Return new root
            return z;
        }
        //пиздец нахуй блять, почему на плюсах эта функция занимала всего 2 строчки, а тут....
        private Node<T> balanceFactor(Node<T> x, Node<T> y) {
            Node<T> rightChildX = x.getRight();
            Node<T> leftChildY = y.getLeft();

            int rightHeightX = (rightChildX == null) ? 0 : rightChildX.getHeight();
            int leftHeightY = (leftChildY == null) ? 0 : leftChildY.getHeight();

            int balanceFactorX = leftHeightY - rightHeightX;

            if (balanceFactorX > 1) {
                // Left subtree is taller than the right, check for left-left or left-right case
                Node<T> leftChildYLeft = (leftChildY != null) ? leftChildY.getLeft() : null;
                Node<T> leftChildYRight = (leftChildY != null) ? leftChildY.getRight() : null;

                int leftChildYLeftHeight = (leftChildYLeft != null) ? leftChildYLeft.getHeight() : 0;
                int leftChildYRightHeight = (leftChildYRight != null) ? leftChildYRight.getHeight() : 0;

                if (leftChildYLeftHeight >= leftChildYRightHeight) {
                    // Left-left case, perform right rotation
                    return rightRotate(y);
                } else {
                    // Left-right case, perform left rotation on x and then right rotation on y
                    x.setLeft(leftRotate(leftChildY));
                    return rightRotate(y);
                }
            } else if (balanceFactorX < -1) {
                // Right subtree is taller than the left, check for right-right or right-left case
                Node<T> rightChildXLeft = (rightChildX != null) ? rightChildX.getLeft() : null;
                Node<T> rightChildXRight = (rightChildX != null) ? rightChildX.getRight() : null;

                int rightChildXLeftHeight = (rightChildXLeft != null) ? rightChildXLeft.getHeight() : 0;
                int rightChildXRightHeight = (rightChildXRight != null) ? rightChildXRight.getHeight() : 0;

                if (rightChildXRightHeight >= rightChildXLeftHeight) {
                    // Right-right case, perform left rotation
                    return leftRotate(x);
                } else {
                    // Right-left case, perform right rotation on y and then left rotation on x
                    y.setRight(rightRotate(rightChildX));
                    return leftRotate(x);
                }
            }

            // Tree is balanced, no rotation needed
            return y;
        }
        public Node<T> search(T key) {
            return search(this, key);
        }

        private Node<T> search(Node<T> node, T key) {
            if (node == null || key.compareTo(node.data) == 0) {
                return node;
            }

            if (key.compareTo(node.data) < 0) {
                return search(node.left, key);
            } else {
                return search(node.right, key);
            }
        }


    }
    public static void main(String[] args) {

    }

}
