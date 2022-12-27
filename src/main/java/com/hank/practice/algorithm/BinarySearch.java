package com.hank.practice.algorithm;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author ：hank.han
 * @date ：Created in 2022/9/28 15:48
 * @description：
 * @version: 1.0
 */
public class BinarySearch {
    public int search(Integer[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("please input two lines : ");
        String input1 = scanner.nextLine();
        String input2 = scanner.nextLine();
        System.out.print("binary search: " + input1);
        System.out.println(", target:  " + input2);

        String[] strings = input1.split(",");
        Integer[] nums = new Integer[strings.length];
        Arrays.stream(strings).map(Integer::parseInt).collect(Collectors.toList()).toArray(nums);
        int result = new BinarySearch().search(nums, Integer.parseInt(input2));

        System.out.println("binary result index: " + result);

    }
}
