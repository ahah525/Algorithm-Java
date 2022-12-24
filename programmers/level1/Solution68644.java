package programmers.level1;


import java.util.*;

/**
 * [문제명] 두 개 뽑아서 더하기
 * [풀이시간] 3분
 * [한줄평] 조합으로 풀 수 있는 대표적인 문제였고 너무 쉬워서 금방 풀 수 있었다.
 * v1. 조합(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/68644">문제</a>
 */
class Solution68644 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param numbers 정수 배열
     * @return 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를 배열에 오름차순으로 담아 return
     */
    public static List<Integer> solution(int[] numbers) {
        // 1. n 개에서 2개 뽑은 수의 합들의 모든 경우의 수 구하기
        Set<Integer> set = new HashSet<>();
        int n = numbers.length;
        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }
        // set 을 list 로 변환하고 오름차순 정렬 후 반환
        List<Integer> answer = new ArrayList<>(set);
        Collections.sort(answer);
        return answer;
    }
}