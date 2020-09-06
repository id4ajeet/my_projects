
package com.ajeet.others;

import java.util.*;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private int value;

    public TreeNode(int value) {
        this.value = value;
    }

    public void insert(int v) {
        if (v <= value) {
            if (left == null) {
                left = new TreeNode(v);
            } else {
                left.insert(v);
            }
        } else {
            if (right == null) {
                right = new TreeNode(v);
            } else {
                right.insert(v);
            }
        }
    }

    public boolean contains(int v) {
        if (v == value) {
            return true;
        }

        if (v < value) {
            if (left == null) {
                return false;
            } else {
                return left.contains(v);
            }
        } else {
            if (right == null) {
                return false;

            } else {
                return right.contains(v);
            }
        }
    }

    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.print(value + " ");
        if (right != null) {
            right.printInOrder();
        }
    }

    public void addInOrder(Collection<Integer> data) {
        if (left != null) {
            left.addInOrder(data);
        }
        data.add(value);
        if (right != null) {
            right.addInOrder(data);
        }
    }

    public void printPreOrder() {
        System.out.print(value + " ");
        if (left != null) {
            left.printPreOrder();
        }
        if (right != null) {
            right.printPreOrder();
        }
    }

    public void printPostOrder() {
        if (left != null) {
            left.printPostOrder();
        }
        if (right != null) {
            right.printPostOrder();
        }
        System.out.print(value + " ");
    }

    public void printLevelOrder() {
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(this);

        while (!qu.isEmpty()) {
            TreeNode treeNode = qu.remove();
            if (treeNode.left != null) {
                qu.add(treeNode.left);
            }
            if (treeNode.right != null) {
                qu.add(treeNode.right);
            }

            System.out.print(treeNode.value + " ");
        }
    }

    public Collection<Integer> sortedList() {
        Collection<Integer> data = new ArrayList<>();

        addInOrder(data);

        return data;
    }

    public int height() {
        if (left == null && right == null) {
            return 1;
        }

        int lh = 0, rh = 0;
        if (left != null) {
            lh = left.height();
        }
        if (right != null) {
            rh = right.height();
        }

        if (lh > rh) {
            return lh + 1;
        } else {
            return rh + 1;
        }
    }

    public void printSpiralOrder() {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        s1.push(this);

        while (!s1.isEmpty() || !s2.isEmpty()) {
            while (!s1.isEmpty()) {
                TreeNode treeNode = s1.pop();
                if (treeNode.left != null) {
                    s2.push(treeNode.left);
                }
                if (treeNode.right != null) {
                    s2.push(treeNode.right);
                }
                System.out.print(treeNode.value + " ");
            }

            while (!s2.isEmpty()) {
                TreeNode treeNode = s2.pop();
                if (treeNode.right != null) {
                    s1.push(treeNode.right);
                }
                if (treeNode.left != null) {
                    s1.push(treeNode.left);
                }
                System.out.print(treeNode.value + " ");
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(80, 30, 60, 40, 70, 20, 10, 90, 100, 85);

        TreeNode root = new TreeNode(data.get(0));

        data.stream().skip(1).forEach(x -> root.insert(x));

        System.out.print("\nInOrder : ");
        root.printInOrder();
        System.out.print("\nPreOrder : ");
        root.printPreOrder();
        System.out.print("\nPostOrder : ");
        root.printPostOrder();

        System.out.print("\nSorted : " + root.sortedList());

        System.out.print("\nLevel Order : ");
        root.printLevelOrder();

        System.out.print("\nSpiral Order : ");
        root.printSpiralOrder();

        System.out.print("\nHeight : " + root.height());

        System.out.print("\nContains 20 : " + root.contains(20)+" Contains 25 : " + root.contains(25));
    }
}
