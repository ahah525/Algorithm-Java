package programmers.level1;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 성격 유형 검사하기
 * [풀이시간] 26분
 * [한줄평] 문제 설명대로 구현하기만 하면 되는 문제였으나 어떤 자료구조를 쓸지 고민하다가 시간이 좀 오래걸렸다.
 * 1_v1. HashMap(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/118666">문제</a>
 */
class Solution118666 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param survey 질문마다 판단하는 지표를 담은 1차원 문자열 배열
     * @param choices 검사자가 각 질문마다 선택한 선택지를 담은 1차원 정수 배열
     * @return 검사자의 성격 유형 검사 결과를 지표 번호 순서대로 return
     */
    public String solution(String[] survey, int[] choices) {
        int[] score = {0, 3, 2, 1, 0, 1, 2, 3};
        // 성격유형별 점수 0으로 초기화
        Map<Character, Integer> map = new HashMap<>();
        map.put('R', 0);
        map.put('T', 0);
        map.put('C', 0);
        map.put('F', 0);
        map.put('J', 0);
        map.put('M', 0);
        map.put('A', 0);
        map.put('N', 0);

        for(int i = 0; i < survey.length; i++) {
            if(choices[i] != 4) {
                // 어떤 유형에 얼마의 점수를 더해야할지 계산
                char c = choices[i] <= 3 ? survey[i].charAt(0) : survey[i].charAt(1);
                int n = score[choices[i]];
                map.put(c, map.get(c) + n);
            }
        }
        StringBuilder sb = new StringBuilder()
                .append(map.get('R') >= map.get('T') ? 'R' : 'T')
                .append(map.get('C') >= map.get('F') ? 'C' : 'F')
                .append(map.get('J') >= map.get('M') ? 'J' : 'M')
                .append(map.get('A') >= map.get('N') ? 'A' : 'N');
        return sb.toString();
    }
}