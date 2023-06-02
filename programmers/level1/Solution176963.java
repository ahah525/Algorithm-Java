package programmers.level1;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 추억 점수
 * [풀이시간] 6분 / 4분
 * [한줄평] map 을 사용하는 완전 기초 문제다. / 더 이상 풀어볼 필요도 없는 쉬운 문제다.
 * 1_v1. HashMap(성공)
 * 2_v1. HashMap(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/176963">문제</a>
 */
class Solution176963 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1
    /**
     * @param name 그리워하는 사람의 이름을 담은 문자열 배열
     * @param yearning 각 사람별 그리움 점수를 담은 정수 배열
     * @param photo 각 사진에 찍힌 인물의 이름을 담은 이차원 문자열 배열
     * @return 사진들의 추억 점수를 photo에 주어진 순서대로 배열에 담아 return
     */
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < name.length; i++) {
            map.put(name[i], yearning[i]);
        }
        for(int i = 0; i < photo.length; i++) {
            for(String s : photo[i]) {
                answer[i] += map.getOrDefault(s, 0);
            }
        }
        return answer;
    }
}