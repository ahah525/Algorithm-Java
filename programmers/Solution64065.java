package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution64065 {
    public static void main(String[] args) {
        List<Integer> answer = solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
        System.out.println(answer);
    }

    public static List<Integer> solution(String s) {
        List<Integer> answer = new ArrayList<>();

        s = s.substring(2, s.length() - 2).replace("},{", "/");
        // / 기준으로 분리
        String[] splits1 = s.split("/");
        // 문자열 길이 오름차순 정렬
        Arrays.sort(splits1, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        for(String s1 : splits1) {
            // , 기준으로 분리
            String[] splits2 = s1.split(",");
            for(String s2 : splits2) {
                if(!answer.contains(Integer.parseInt(s2))) {
                    answer.add(Integer.parseInt(s2));
                    break;
                }
            }
        }
        return answer;
    }
}