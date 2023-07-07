package org.example.algrithem;

import java.util.Scanner;

public class Compress {

    public static int compress(char[] chars) {
        int indexAns = 0, index = 0;
        while(index < chars.length){
            char currentChar = chars[index];
            int count = 0;
            while(index < chars.length && chars[index] == currentChar){
                index++;
                count++;
            }
            chars[indexAns++] = currentChar;
            if(count != 1)
                for(char c : Integer.toString(count).toCharArray())
                    chars[indexAns++] = c;
        }
        return indexAns;
    }

    public static void main(String[] args) {
        while (true) {
            Scanner scan=new Scanner(System.in);
            String str = scan.nextLine().replace(" ","");
            char[] chars = str.toCharArray();
            System.out.println(compress(chars));
        }
    }

}
