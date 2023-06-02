package programmers.level1;


import java.util.Arrays;

/**
 * [문제명] 문자열 내 마음대로 정렬하기
 * [풀이시간] / 2분
 * [한줄평] 정렬 기초 문제였다.
 * 1_v1. 정렬(성공)
 * 2_v1. 정렬(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12915">문제</a>
 */
class Solution12915 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, (o1, o2) -> {
            // 2. 단어 사전순 오름차순 정렬
            if(o1.charAt(n) == o2.charAt(n)) return o1.compareTo(o2);
            // 1. n번째 문자 오름차순 정렬
            return o1.charAt(n) - o2.charAt(n);
        });
        return strings;
    }
}