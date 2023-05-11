package programmers.level2;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 위장
 * [풀이시간] / 13분
 * [한줄평] map 을 이용해 경우의 수를 계산할 수 있으면 쉽게 풀 수 있었던 문제였다. 실제로 문자열을 저장할 필요는 없고 의상종류별 의상 개수면 저장하면 된다.
 * / 경우의 수를 계산할 수 있으면 쉽게 풀 수 있는 문제였다.
 * 1_v1. HashMap, 수학(성공)
 * [예제]
 * 의상 종류별 의상 개수 = 2 1 1
 * 0개 선택 경우의 수 = 1
 * 1개 선택 경우의 수 = (2 + 1 + 1) = 4
 * 2개 선택 경우의 수 = (2 * 1) + (2 * 1) + (1 * 1) = 5
 * 3개 선택 경우의 수 = (2 * 1 * 1) = 2
 * 정답 = 총 경우의 수(0~n개 선택) - 1(0개 선택) = 3 * 2 * 2 - 1 = 11
 * 2_v1. HashMap, 수학(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42578">문제</a>
 */
class Solution42578 {
    public static void main(String[] args) {
        // 5
        String[][] clothes1 = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println(solution(clothes1));

        // 3
        String[][] clothes2 = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
        System.out.println(solution(clothes2));
    }

    // 1_v1, 2_v1
    /**
     * @param clothes 스파이가 가진 의상들이 담긴 2차원 배열[의상의 이름, 의상의 종류]
     * - 스파이가 가진 의상의 수는 1개 이상 30개 이하
     * @return 서로 다른 옷의 조합의 수
     */
    public static int solution(String[][] clothes) {
        // 종류별 의상 개수 구하기
        Map<String, Integer> map = new HashMap<>(); // (의상 종류, 개수)
        for(String[] c : clothes) {
            map.put(c[1], map.getOrDefault(c[1], 0) + 1);
        }
        int answer = 1;
        for(int v : map.values()) {
            answer *= (v + 1);
        }
        return answer - 1;
    }
}