package com.hank.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompressUtility {

    public static void main(String[] args) {
        System.out.println("It's fine");
        CompressUtility cu = new CompressUtility();
        List<String> uncompressions = new ArrayList<>();
        uncompressions.add("00:00:06 00:00 109.068 109.094 109.081 FXDD");
        uncompressions.add("00:00:05 00:00 109.068 109.093 109.081 FXDD");
        uncompressions.add("00:00:05 00:00 109.068 109.093 109.081 FXDD");
        uncompressions.add("00:00:05 00:00 109.068 109.093 109.081 FXDD");
        uncompressions.add("00:00:05 00:00 109.068 109.093 109.081 FXDD");
        uncompressions.add("00:00:05 00:00 109.068 109.093 109.081 FXDD");
        uncompressions.add("00:00:05 00:00 109.068 109.093 109.081 FXDD");
        uncompressions.add("00:00:05 00:00 109.068 109.093 109.081 FXDD");
        List<String> compressions = cu.compress(uncompressions);
        for (String compression : compressions) {
            System.out.println(compression);
        }
        System.out.println("ending");
    }


    public List<String> compress(List<String> uncompressions) {
        //00:00:06 00:00 109.068 109.094 109.081 FXDD
        if (null == uncompressions) {
            return new ArrayList<>();
        }
        List<String> compressions = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Integer replace = 0;

        for (String uncompression : uncompressions) {
            StringBuilder sb = new StringBuilder();
            String[] firstSplits = uncompression.split(" ");
            for (String firstSplit : firstSplits) {
                if (firstSplit.contains(":")) {
                    replace = handler(firstSplit, sb, map, ":", replace);
                } else if (firstSplit.contains(".")) {
                    replace = handler(firstSplit, sb, map, "\\.", replace);
                } else {
                    if (map.containsKey(firstSplit)) {
                        Integer r = map.get(firstSplit);
                        sb.append(Integer.toHexString(r));
                    } else {
                        Integer r = replace++;
                        map.put(firstSplit, r);
                        sb.append(Integer.toHexString(r));
                    }
                }
            }
            compressions.add(sb.toString());
        }

        if (map.keySet().size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String key : map.keySet()) {
                sb.append(Integer.toHexString(map.get(key))).append(":").append(key).append(";");
            }
            compressions.add(sb.substring(0, sb.length() - 1));
        }
        return compressions;
    }


    public Integer handler(String firstSplit, StringBuilder sb, Map<String, Integer> map, String indecator, Integer index) {
        String[] secondSplits = firstSplit.split(indecator);
        for (int i = 0; i < secondSplits.length; i++) {
            String secondSplit = secondSplits[i];
            if (map.containsKey(secondSplit)) {
                Integer r = map.get(secondSplit);
                sb.append(Integer.toHexString(r));
            } else {
                Integer r = index++;
                map.put(secondSplit, r);
                sb.append(Integer.toHexString(r));
            }
            if (i != secondSplits.length - 1) {
                sb.append(indecator.replaceAll("\\\\", ""));
            }
        }
        sb.append(" ");
        return index;
    }

}