package com.hank.practice.algorithm;

import java.util.Scanner;

/**
 * @author ：hank.han
 * @date ：Created in 2022/9/20 14:10
 * @description：
 * @version: 1.0
 */
public class RevereEachWord {

    public char[] revereWords(char[] sentence) {
        int start = 0;
        for (int index = 0; index < sentence.length; index++) {
            if (sentence[index] == ' ') {
                swap(sentence, start, index - 1);
                start = index + 1;
            }
            if (index == sentence.length - 1 && start < index) {
                swap(sentence, start, index);
            }
        }
        return sentence;
    }

    public void swap(char[] sentence, int start, int end) {
        while (start < end) {
            char temp = sentence[start];
            sentence[start] = sentence[end];
            sentence[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        System.out.println("input: " + input);
        char[] revereWords = new RevereEachWord().revereWords(input.toCharArray());
        System.out.println("output: " + new String(revereWords));
    }
}
