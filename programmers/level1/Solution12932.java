package programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [문제명] 숫자를 뒤집고 각 자리수 구하기
 * [풀이시간] / 6분
 * [한줄평] / 정수의 각 자리수를 구하는 쉬운 문제였다.
 * 1_v1. (성공) -> 빠름
 * [풀이] 10으로 나눈 나머지 구하기 -> 10으로 나누기 반복
 * 1_v2. (성공)
 * [풀이] 숫자를 String 변환하여 reverse() -> 각 자리수 구하기
 * 2_v1. (성공)
 */
class Solution12932 {
    public static void main(String[] args) {
        // [5,4,3,2,1]
        System.out.println(Arrays.toString(solution(12345)));
    }

    // 1_v1, 2_v1
    /**
     * @param n 10,000,000,000이하인 자연수
     * @return 자연수 n을 뒤집어 각 자리 숫자를 원소로 가지는 배열
     */
    public static int[] solution(long n) {
        List<Integer> list = new ArrayList<>();
        // 1. 10으로 나눈 나머지 구하기
        while(n != 0) {
            list.add((int) (n % 10));
            n /= 10;
        }
        // 2. 리스트를 배열로 변환
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}