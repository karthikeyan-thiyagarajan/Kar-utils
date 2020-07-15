package com.karthikeyan.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthikeyan on 13/07/20
 */

public class TextMatcher {
    private static List<String> preProcessing(String input) {
        List<String> list = new ArrayList<>();
        input = input.toLowerCase();
        int strlen = input.length();
        strlen = strlen - 3;
        for (int i = 0; i <= strlen; i++)
            list.add(input.substring(i, i + 3));
        return list;
    }

    private static int matchProcessing(List<String> source, List<String> target) {
        int matchCount = 0;
        int totalCount = Math.max(source.size(), target.size());
        for (String sourceText : source) {
            if (target.contains(sourceText)) {
                matchCount++;
            }
        }
        return (matchCount * 100) / totalCount;
    }
    public static int checkValues(String src,String target){
        return matchProcessing(preProcessing(src), preProcessing(target));
    }

}
