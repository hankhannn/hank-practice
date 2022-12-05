package com.hank.algorithm.practice;

import java.util.Scanner;

/**
 * @author ：ranking.han
 * @date ：Created in 2022/8/22 17:10
 * @description：
 * @version: 1.0
 */
public class MergeSort {

    public void sort(int[] array, int start, int end) {

    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int[] array = new int[s.length()];
        int i = 0;
        for (char x : s.toCharArray())
            array[i++] = Integer.parseInt(s.toLowerCase());

        new MergeSort().sort(array, 0, array.length - 1);
        for (int j : array) {
            System.out.print(j);
            System.out.print(",");
        }
    }
}
