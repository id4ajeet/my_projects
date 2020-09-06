/*
 * (c) Copyright 2014 Brite:Bill Ltd.
 *
 * 23 Windsor Place, Dublin 2, Ireland
 * info@britebill.com
 * +353 1 661 9426
 */
package com.ajeet.others;

import org.junit.Test;

import java.util.*;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
public class TreeTest {

    @Test
    public void testOrder() {
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
