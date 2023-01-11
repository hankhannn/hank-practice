package com.hank.practice.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：ranking.han
 * @date ：Created in 2023/1/11 16:02
 * @description：
 * @version: 1.0
 */
public class SortFootballTeam {


    public List<String> sortTeam(String[] results) {
        Map<String, Integer> scoreMap = new HashMap<>();

        for (String result : results) {
            String[] teams = result.split(":");
            String[] team1 = teams[0].split(" ");
            String[] team2 = teams[1].split(" ");
            Integer score1 = getScore(team1[1], team2[1], "F");
            Integer score2 = getScore(team1[1], team2[1], "S");
            if (scoreMap.containsKey(team1[0])) {
                Integer t1 = scoreMap.get(team1[0]);
                scoreMap.put(team1[0], t1 + score1);
            } else {
                scoreMap.put(team1[0], score1);
            }
            if (scoreMap.containsKey(team2[0])) {
                Integer t2 = scoreMap.get(team2[0]);
                scoreMap.put(team2[0], t2 + score2);
            } else {
                scoreMap.put(team2[0], score2);
            }
        }
        List<String> teamLists = new ArrayList<>();
        for (String key : scoreMap.keySet()) {
            teamLists.add(key + " " + scoreMap.get(key));
        }
        return teamLists.stream().sorted((o1, o2) -> Integer.parseInt(o2.split(" ")[1]) - Integer.parseInt(o1.split(" ")[1])).collect(Collectors.toList());
    }

    private Integer getScore(String score1, String score2, String flag) {
        int s1 = Integer.parseInt(score1);
        int s2 = Integer.parseInt(score2);
        if (s1 == s2) {
            return 1;
        } else if (s1 > s2) {
            if (flag.equals("F")) {
                return 3;
            } else {
                return 0;
            }
        } else {
            if (flag.equals("F")) {
                return 0;
            } else {
                return 3;
            }
        }
    }

    public static void main(String[] args) {
        SortFootballTeam sortFootballTeam = new SortFootballTeam();
        List<String> strings = sortFootballTeam.sortTeam("A 3:B 2;B 1:C 0;A 1:C 1".split(";"));
        strings.forEach(System.out::println);
    }
}
