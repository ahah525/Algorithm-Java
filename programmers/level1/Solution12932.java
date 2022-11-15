package programmers.level1;

import java.util.ArrayList;
import java.util.List;

/**
 * 숫자를 뒤집고 각 자리수 구하기
 * v1. 10으로 나눈 나머지 구하기 -> 10으로 나누기 반복(추천)
 * v2. 숫자를 String 변환하여 reverse() -> 각 자리수 구하기
 */
class Solution12932 {
    public static void main(String[] args) {
        int n1 = 12345;
        // [5,4,3,2,1]
        List<Integer> answer1 = solution(n1);
        System.out.println(answer1);
    }

    /**
     * @param n 10,000,000,000이하인 자연수
     * @return 자연수 n을 뒤집어 각 자리 숫자를 원소로 가지는 배열
     */
    public static List<Integer> solution(long n) {
        List<Integer> answer = new ArrayList<>();

        while(n != 0) {
            answer.add((int) (n % 10));
            n /= 10;
        }

        return answer;
    }
}