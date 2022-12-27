package com.hank.practice.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hank.han
 * @date ：Created in 2022/8/31 17:19
 * @description：
 * @version: 1.0
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> st = new ArrayList<>();
        List<String> a = new ArrayList<>();

        if (s.length() == 0) {
            return st;
        }

        dfs(0, s, st, a);

        return st;
    }

    public boolean isPalindrome(String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            if (s.charAt(low) != s.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }

    public void dfs(int start, String s, List<List<String>> st, List<String> a) {
        if (start == s.length()) {        //实在找不到结束条件的时候就设个start
            st.add(new ArrayList<>(a));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String temp = s.substring(start, i + 1); //注意起始位置是start
            if (isPalindrome(temp)) {
                a.add(temp);
                dfs(i + 1, s, st, a);
                a.remove(a.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String s = "nitin";
        System.out.println(new PalindromePartitioning().partition(s));
    }
}
