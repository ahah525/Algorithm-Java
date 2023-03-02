package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [문제명] 튜플
 * [풀이시간] / 35분
 * [한줄평] / 치환까지는 쉽게 했는데, 그 이후에 숫자 개수 오름차순 정렬을 어떻게 해야할지 고민하다가 결국 힌트를 조금 보고 다시 풀었다.
 * 1_v1. (성공)
 * 2_v1. (성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/64065">문제</a>
 */
class Solution64065 {
    public static void main(String[] args) {
        System.out.println(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"));
    }

    /**
     * @param s 특정 튜플을 표현하는 집합이 담긴 문자열
     * @return s가 표현하는 튜플을 배열에 담아 return
     */
    public static List<Integer> solution(String s) {
        List<Integer> answer = new ArrayList<>();   // 튜플
        // 1. },{ -> / 치환  {{, }} -> 삭제
        s = s.replace("},{", "/")
                .replaceAll("\\{\\{|\\}\\}", "");
        // 2. / 기준으로 분리
        String[] splits1 = s.split("/");
        // 3. 문자열 길이 오름차순 정렬
        Arrays.sort(splits1, (o1, o2) -> o1.length() - o2.length());
        // 4. 각 문자열에서 튜플에 속하지 않은 숫자 찾기
        for(String s1 : splits1) {
            // , 기준으로 분리
            String[] splits2 = s1.split(",");
            for(String s2 : splits2) {
                int n = Integer.parseInt(s2);
                if(!answer.contains(n)) {
                    answer.add(n);
                    break;
                }
            }
        }
        return answer;
    }
}