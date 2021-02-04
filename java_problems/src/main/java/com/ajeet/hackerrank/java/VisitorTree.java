package com.ajeet.hackerrank.java;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */

import java.util.*;

public class VisitorTree {
    /**
     * <pre>
     * Sample Input
     * 5
     * 4 7 2 5 12
     * 0 1 0 0 1
     * 1 2
     * 1 3
     * 3 4
     * 3 5
     *
     * Output
     * 24
     * 40
     * 15
     *
     *
     * </pre>
     */
    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }

    public static Tree solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] values = new int[n];
        for (int index = 0; index < n; index++) {
            values[index] = in.nextInt();
        }

        Color[] colors = new Color[n];
        for (int index = 0; index < n; index++) {
            colors[index] = in.nextInt() == 0 ? Color.RED : Color.GREEN;
        }

        if (n == 1) {
            return new TreeLeaf(values[0], colors[0], 0);
        } else {
            Map<Integer, Set<Integer>> nodes = new HashMap<>();
            for (int i = 0; i < n - 1; i++) {
                int s = in.nextInt();
                int t = in.nextInt();

                store(s, t, nodes);
                store(t, s, nodes);
            }

            Tree root = new TreeNode(values[0], colors[0], 0);
            int parentId = 1;

            Set<Integer> edges = nodes.get(parentId);
            for (int nodeId : edges) {
                nodes.get(nodeId).remove(parentId);
                createEdge(root, nodeId, nodes, values, colors);
            }
            return root;
        }
    }

    private static void createEdge(Tree parent, int nodeId, Map<Integer, Set<Integer>> nodes, int[] values, Color[] colors) {

        Set<Integer> edges = nodes.get(nodeId);
        if (edges != null && !edges.isEmpty()) {
            TreeNode node = new TreeNode(values[nodeId - 1], colors[nodeId - 1], parent.getDepth() + 1);
            ((TreeNode) parent).addChild(node);

            for (int id : edges) {
                nodes.get(id).remove(nodeId);
                createEdge(node, id, nodes, values, colors);
            }
        } else {
            TreeLeaf leaf = new TreeLeaf(values[nodeId - 1], colors[nodeId - 1], parent.getDepth() + 1);
            ((TreeNode) parent).addChild(leaf);
        }
    }

    private static void store(int s1, int s2, Map<Integer, Set<Integer>> nodes) {
        Set<Integer> sourceEdges = nodes.get(s1);
        if (sourceEdges == null) {
            sourceEdges = new HashSet<>();
        }
        sourceEdges.add(s2);
        nodes.put(s1, sourceEdges);
    }


}

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {
    public abstract int getResult();

    public abstract void visitNode(TreeNode node);

    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int sum = 0;

    public int getResult() {
        return sum;
    }

    public void visitNode(TreeNode node) {
        /* do nothing */
    }

    public void visitLeaf(TreeLeaf leaf) {
        sum += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private long value = 1;

    public int getResult() {
        return (int) value;
    }

    public void visitNode(TreeNode node) {
        calculate(node.getValue(), node.getColor());
    }

    public void visitLeaf(TreeLeaf leaf) {
        calculate(leaf.getValue(), leaf.getColor());
    }

    private void calculate(int val, Color c) {
        if (c.equals(Color.RED)) {
            value = (value * val) % 1000000007;
        }
    }
}

class FancyVisitor extends TreeVis {
    private int depth = 0;
    private int sum = 0;

    public int getResult() {
        return Math.abs(depth - sum);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            depth = depth + node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor().equals(Color.GREEN)) {
            sum = sum + leaf.getValue();
        }
    }
}
