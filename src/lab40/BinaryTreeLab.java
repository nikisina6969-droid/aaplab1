package lab40;

import java.util.*;

public class BinaryTreeLab {
    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        Node root;

        // 1. Базовые методы обхода

        // Прямой порядок (preOrder)
        void preOrder(Node node) {
            if (node == null) return;
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }

        // Центрированный порядок (inOrder)
        void inOrder(Node node) {
            if (node == null) return;
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }

        // Обратный порядок (postOrder)
        void postOrder(Node node) {
            if (node == null) return;
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }

        // Поуровневый (levelOrder)
        void levelOrder() {
            if (root == null) return;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node current = queue.poll();
                System.out.print(current.data + " ");
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }
        }
        int height(Node node) {
            if (node == null) return 0;
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
        int getHeight() {
            return height(root);
        }

        // 2. Полное бинарное дерево

        void buildFullTree(int[] values) {
            if (values.length == 0) return;
            root = new Node(values[0]);
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            int index = 1;
            while (index < values.length) {
                Node current = queue.poll();
                if (index < values.length) {
                    current.left = new Node(values[index++]);
                    queue.add(current.left);
                }
                if (index < values.length) {
                    current.right = new Node(values[index++]);
                    queue.add(current.right);
                }
            }
        }
        boolean isFull(Node node) {
            if (node == null) return true;
            if (node.left == null && node.right == null) return true;
            if (node.left != null && node.right != null)
                return isFull(node.left) && isFull(node.right);
            return false;
        }

        boolean isFull() {
            return isFull(root);
        }

        // 3. Бинарное дерево поиска (BST)

        void insertBST(int data) {
            root = insertBSTRec(root, data);
        }

        Node insertBSTRec(Node node, int data) {
            if (node == null) {
                return new Node(data);
            }
            if (data < node.data) {
                node.left = insertBSTRec(node.left, data);
            } else if (data > node.data) {
                node.right = insertBSTRec(node.right, data);
            }
            return node;
        }
        void buildBSTFromSortedArray(int[] arr) {
            if (arr.length == 0) return;
            root = buildBSTFromSortedArrayRec(arr, 0, arr.length - 1);
        }

        Node buildBSTFromSortedArrayRec(int[] arr, int start, int end) {
            if (start > end) return null;
            int mid = (start + end) / 2;
            Node node = new Node(arr[mid]);
            node.left = buildBSTFromSortedArrayRec(arr, start, mid - 1);
            node.right = buildBSTFromSortedArrayRec(arr, mid + 1, end);
            return node;
        }

        // 4. Сбалансированное дерево (ручная балансировка)

        void buildBalancedTree(int[] arr) {
            buildBSTFromSortedArray(arr); }

        boolean isBalanced(Node node) {
            if (node == null) return true;
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            if (Math.abs(leftHeight - rightHeight) > 1) return false;
            return isBalanced(node.left) && isBalanced(node.right);
        }

        // 5. Реализация базовых операций (вставка по уровню, удаление)

        void insert(int data) {
            if (root == null) {
                root = new Node(data);
                return;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node current = queue.poll();
                if (current.left == null) {
                    current.left = new Node(data);
                    return;
                } else {
                    queue.add(current.left);
                }
                if (current.right == null) {
                    current.right = new Node(data);
                    return;
                } else {
                    queue.add(current.right);
                }
            }
        }

        void delete(int key) {
            if (root == null) return;
            if (root.data == key && root.left == null && root.right == null) {
                root = null;
                return;
            }

            Node target = findNode(root, key);
            if (target == null) return;

            Node deepestRight = findDeepestRight(root);

            target.data = deepestRight.data;

            removeDeepestRight(root, deepestRight);
        }

        Node findNode(Node node, int key) {
            if (node == null) return null;
            if (node.data == key) return node;
            Node found = findNode(node.left, key);
            if (found != null) return found;
            return findNode(node.right, key);
        }

        Node findDeepestRight(Node node) {
            if (node == null) return null;
            Node deepest = node;
            Queue<Node> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                deepest = queue.poll();
                if (deepest.right != null) queue.add(deepest.right);
                if (deepest.left != null) queue.add(deepest.left);
            }
            return deepest;
        }

        void removeDeepestRight(Node node, Node deepest) {
            if (node == null) return;
            if (node.left == deepest) {
                node.left = null;
                return;
            }
            if (node.right == deepest) {
                node.right = null;
                return;
            }
            removeDeepestRight(node.left, deepest);
            removeDeepestRight(node.right, deepest);
        }
    }
    
}

