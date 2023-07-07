package org.example.algrithem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Rearrange {

    public static String rearrange(String text) {
        String[] input = text.split("\\s");
        List<String> str = Arrays.stream(input).sorted(Comparator.comparing(String::length)).map(String::toLowerCase).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.get(0).substring(0,1).toUpperCase()).append(str.get(0).substring(1));
        for (int i = 1; i < str.size(); i++) {
            stringBuilder.append(" ").append(str.get(i));
        }
        return  stringBuilder.toString();
    }

    public static void main(String[] args) {

        while (true) {

            Scanner scan=new Scanner(System.in);
            System.out.println(rearrange(scan.nextLine()));
        }


    }

}
