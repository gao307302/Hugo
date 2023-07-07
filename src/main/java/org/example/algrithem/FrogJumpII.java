package org.example.algrithem;

import java.util.Scanner;

public class FrogJumpII {

    public static int frogJump(int[] input) {
        int max = 0;
        int length = input.length;
        if(length == 2) {
            return input[1] - input[0];
        }
        for (int i = 0; i < length - 2; i = i + 2) {
            max = Math.max(max, input[i + 2] - input[i]);
        }
        for (int i = 1; i < length - 2; i = i + 2) {
            max = Math.max(max, input[i + 2] - input[i]);
        }
        return max;
    }

    public static void main(String[] args) {

        while (true) {

            Scanner scan=new Scanner(System.in);
            System.out.println(frogJump(scan.next().chars().toArray()));
        }


    }

}
