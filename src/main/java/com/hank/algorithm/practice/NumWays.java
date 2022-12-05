package com.hank.algorithm.practice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author ：ranking.han
 * @date ：Created in 2022/9/20 15:08
 * @description：
 * @version: 1.0
 */
public class NumWays {


    private int numWays(String input, int index, int[] memo) {
        if (index == input.length()) return 1;
        String first = input.substring(index, index + 1);
        if (Integer.parseInt(first) == 0) return 0;
        if (memo[index] != -1) return memo[index];
        int total = 0;
        total += numWays(input, index + 1, memo);
        if (index < input.length() - 1) {
            String second = input.substring(index, index + 2);
            if (Integer.parseInt(second) >= 10 || Integer.parseInt(second) <= 26) {
                total += numWays(input, index + 2, memo);
            }
        }
        memo[index] = total;
        return total;
    }

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            int[] memo = new int[input.length() + 1];
            Arrays.fill(memo, -1);
            System.out.println("input: " + input);
            int num = new NumWays().numWays(input, 0, memo);
            System.out.println("numWays: " + num);
        }
    }
}
