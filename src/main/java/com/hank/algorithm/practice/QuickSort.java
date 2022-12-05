package com.hank.algorithm.practice;

import java.util.Scanner;

/**
 * @author ：ranking.han
 * @date ：Created in 2022/8/22 17:10
 * @description：
 * @version: 1.0
 */
public class QuickSort {

    public void sort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int i = start, j = end, base = array[start];
        while (i < j) {

            while (i < j && array[j] >= base) {
                j--;
            }

            while (i < j && array[i] <= base) {
                i++;
            }

            swap(array, i, j);
        }
        swap(array, start, j);
        sort(array, start, i - 1);
        sort(array, i + 1, end);
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
            array[i++] = Integer.parseInt(String.valueOf(x));

        new QuickSort().sort(array, 0, array.length - 1);
        for (int j : array) {
            System.out.print(j);
            System.out.print(",");
        }
    }
}
