package com.hank.practice.algorithm;

import graphql.com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author ：hank.han
 * @date ：Created in 2022/12/27 17:18
 * @description：
 * @version: 1.0
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> record = new ArrayList<>();
        combinationSum(candidates, target, 0, record, results);
        return results;
    }

    private void combinationSum(int[] candidates, int target, int i, List<Integer> record, List<List<Integer>> results) {
        if (target == 0) {
            results.add(new ArrayList<>(record));
            return;
        }
        while (i < candidates.length) {
            if (target - candidates[i] >= 0) {
                record.add(candidates[i]);
                combinationSum(candidates, target - candidates[i], i, record, results);
                record.remove(record.size() - 1);
            }
            i++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String target = scanner.nextLine();
        int[] numbers = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            numbers[i] = Integer.parseInt(Character.valueOf(input.charAt(i)).toString());
        }
        List<List<Integer>> listList = new CombinationSum().combinationSum(numbers, Integer.parseInt(target));
        System.out.println("CombinationSum: " + Joiner.on(";").join(listList.stream().map(one -> Joiner.on(",").join(one)).collect(Collectors.toList())));
    }
}
