package org.example.algrithem;

import java.util.Arrays;
import java.util.Scanner;

/*

A bus has n stops numbered from 0 to n - 1 that form a circle.

We know the distance between all pairs of neighboring stops where distance[i] is the distance between the stops number i and (i + 1) % n.

The bus goes along both directions i.e. clockwise and counterclockwise.

Return the shortest distance between the given start and destination stops.
 */
public class DistanceBetweenBusStops {

    public static int distanceBetweenBusStops(int[] distance, int start, int destination) {
        if(start > destination) {
            int temp = destination;
            destination = start;
            start = temp;
        }
        int sum = Arrays.stream(distance).sum();
        int min = Arrays.stream(Arrays.copyOfRange(distance, start, destination)).sum();
        return Math.min(min, sum - min);
    }

    public static void main(String[] args) {
    }

}
