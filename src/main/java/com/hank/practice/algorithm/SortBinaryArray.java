package com.hank.practice.algorithm;

import java.util.Arrays;

public class SortBinaryArray {
    public static void main(String[] args) {
        System.out.println(sort(new int[]{0, 1, 0, 1, 0, 1, 0}));
    }

    public static String sort(int[] array) {
        if (null == array || array.length == 0) {
            return Arrays.toString(array);
        }
        int[] sortArray = new int[array.length];
        int idx = array.length - 1;
        for (int i : array) {
            if (1 == i) {
                sortArray[idx] = i;
                idx--;
            }
        }
        return Arrays.toString(sortArray);
    }
}
