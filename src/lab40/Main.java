package lab40;
import lab40.BinaryTreeLab.BinaryTree;
import lab40.BinaryTreeLab.Node;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лабораторная работа №4: Бинарные деревья");
        System.out.println();

        // Тест 1: Базовые методы обхода
        System.out.println("=== Тест 1: Базовые методы обхода ===");
        BinaryTree tree1 = new BinaryTree();
        tree1.root = new Node(1);
        tree1.root.left = new Node(2);
        tree1.root.right = new Node(3);
        tree1.root.left.left = new Node(4);
        tree1.root.left.right = new Node(5);

        System.out.print("PreOrder: ");
        tree1.preOrder(tree1.root);
        System.out.println();

        System.out.print("InOrder: ");
        tree1.inOrder(tree1.root);
        System.out.println();

        System.out.print("PostOrder: ");
        tree1.postOrder(tree1.root);
        System.out.println();

        System.out.print("LevelOrder: ");
        tree1.levelOrder();
        System.out.println();

        System.out.println("Высота дерева: " + tree1.getHeight());
        System.out.println();

        // Тест 2: Полное бинарное дерево
        System.out.println("=== Тест 2: Полное бинарное дерево ===");
        BinaryTree tree2 = new BinaryTree();
        int[] fullValues = {1, 2, 3, 4, 5, 6, 7};
        tree2.buildFullTree(fullValues);
        System.out.print("Полное дерево (LevelOrder): ");
        tree2.levelOrder();
        System.out.println();
        System.out.println("Дерево полное? " + tree2.isFull());
        System.out.println();

        // Тест 3: BST
        System.out.println("=== Тест 3: Бинарное дерево поиска ===");
        BinaryTree tree3 = new BinaryTree();
        int[] bstValues = {5, 3, 7, 2, 4, 6, 8};
        for (int val : bstValues) {
            tree3.insertBST(val);
        }
        System.out.print("BST (InOrder): ");
        tree3.inOrder(tree3.root);
        System.out.println();
        System.out.println();

        // Тест 4: Сбалансированное дерево
        System.out.println("=== Тест 4: Сбалансированное дерево ===");
        BinaryTree tree4 = new BinaryTree();
        int[] sortedArr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        tree4.buildBalancedTree(sortedArr);
        System.out.print("Сбалансированное дерево (LevelOrder): ");
        tree4.levelOrder();
        System.out.println();
        System.out.println("Дерево сбалансировано? " + tree4.isBalanced(tree4.root));
        System.out.println();

        // Тест 5: Вставка и удаление
        System.out.println("=== Тест 5: Вставка и удаление ===");
        BinaryTree tree5 = new BinaryTree();
        tree5.insert(10);
        tree5.insert(20);
        tree5.insert(5);
        tree5.insert(15);
        System.out.print("Дерево после вставки (LevelOrder): ");
        tree5.levelOrder();
        System.out.println();

        tree5.delete(20);
        System.out.print("Дерево после удаления 20 (LevelOrder): ");
        tree5.levelOrder();
        System.out.println();
        
    }
}
