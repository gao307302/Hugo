package org.example.algrithem;

import java.util.Scanner;

public class palindromic {

    public static String getPalindromicString(String input) {
        if(input == null || input.equals("")) {
            return "";
        }
        if(input.length() == 1) {
            return input;
        }
        int length = input.length();
        char[] chars = input.toCharArray();
        int left, right;
        int tempMax = 0;
        int tempLeft = 0;
        int tempRight = 0;
        for (int i = 0; i < length - 2; i++) {
            int max = 2;
            left = i;
            right = i + 1;
            if(chars[left] != chars[right]) {
                right --;
                max = 1;
            }
            while(left >= 0 && right <= length - 1 && chars[left] == chars[right]) {
                if(tempMax < max) {
                    tempLeft = left;
                    tempRight = right;
                    tempMax = max;
                }
                left --;
                right ++;
                max += 2;
            }
        }
        return input.substring(tempLeft, tempRight + 1);
    }

    public static void main(String[] args) {

        while (true) {

            Scanner scan=new Scanner(System.in);
            System.out.println(getPalindromicString(scan.nextLine()));
        }


    }

}
