package com.hank.practice.algorithm;

import graphql.com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author ：hank.han
 * @date ：Created in 2022/9/21 10:30
 * @description：
 * @version: 1.0
 */
public class NumWaysOfStairs {

    public int numWays(int totalSteps, List<Integer> steps) {
        int[] memo = new int[totalSteps + 1];
        memo[0] = 1;
        memo[1] = 1;
        for (int i = 2; i <= totalSteps; i++) {
            for (int step : steps) {
                if (i - step >= 0) {
                    memo[i] += memo[i - step];
                }
            }
        }
        return memo[totalSteps];
    }

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            System.out.println("input totalSteps: " + input);
            String input2 = scanner.nextLine();
            List<Integer> steps = Arrays.stream(input2.split("")).map(Integer::valueOf).collect(Collectors.toList());
            System.out.println("input totalSteps: " + Joiner.on(",").join(steps));
            int numWays = new NumWaysOfStairs().numWays(Integer.parseInt(input), steps);
            System.out.println("numWays : " + numWays);
        }
    }
}
