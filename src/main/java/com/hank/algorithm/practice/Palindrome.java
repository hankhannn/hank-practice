package com.hank.algorithm.practice;

import java.util.Scanner;

/**
 * @author ：ranking.han
 * @date ：Created in 2022/9/28 9:51
 * @description：
 * @version: 1.0
 */
public class Palindrome {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        for (int i = 0, j = s.length() - 1; i < j; ) {
            if (!isCharacter(s.charAt(i))) {
                i++;
            }
            if (!isCharacter(s.charAt(j))) {
                j--;
            }
            if (isCharacter(s.charAt(i)) && isCharacter(s.charAt(j))) {
                if (s.charAt(i) != s.charAt(j)) return false;
                i++;
                j--;
            }
        }
        return true;
    }

    public boolean isCharacter(char c) {
        if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("please input message: ");
        String input = scanner.nextLine();
        System.out.println("input message: " + input);
        boolean palindrome = new Palindrome().isPalindrome(input);

        System.out.println("is it palindrome? answer: " + palindrome);

    }
}
