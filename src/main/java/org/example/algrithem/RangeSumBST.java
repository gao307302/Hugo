package org.example.algrithem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
/*

Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].

 Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
 */
public class RangeSumBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null) {
            return 0;
        }
        if(root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        if(root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        return root.val + rangeSumBST(root.right, low, high) + rangeSumBST(root.left, low, high);
    }

    public static void main(String[] args) {

        while (true) {
        }


    }

}
