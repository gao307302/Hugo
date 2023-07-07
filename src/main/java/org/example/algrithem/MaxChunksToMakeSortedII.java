package org.example.algrithem;

import java.util.*;
//      You are given an integer array arr.
//
//        We split arr into some number of chunks (i.e., partitions), and individually sort each chunk. After concatenating them, the result should equal the sorted array.
//
//        Return the largest number of chunks we can make to sort the array.


public class MaxChunksToMakeSortedII {

    public static int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] maxOfLeft = new int[n];
        int[] minOfRight = new int[n];

        maxOfLeft[0] = arr[0];
        for (int i = 1; i < n; i++) {
            maxOfLeft[i] = Math.max(maxOfLeft[i-1], arr[i]);
        }

        minOfRight[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minOfRight[i] = Math.min(minOfRight[i + 1], arr[i]);
        }

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (maxOfLeft[i] <= minOfRight[i + 1]) res++;
        }

        return res + 1;
    }

    public static void main(String[] args) {

        while (true) {

            Scanner scan=new Scanner(System.in);
            int[] ints = new int[scan.nextInt()];
            for (int i = 0; i < ints.length; i++) {
                ints[i] = scan.nextInt();
            }
            System.out.println(maxChunksToSorted(ints));
        }


    }

}
