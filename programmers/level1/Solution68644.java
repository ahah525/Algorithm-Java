package programmers.level1;


import java.util.*;

/**
 * [문제명] 두 개 뽑아서 더하기
 * [풀이시간] 3분 / 4분
 * [한줄평] 조합으로 풀 수 있는 대표적인 문제였고 너무 쉬워서 금방 풀 수 있었다. / 더 이상 풀어볼 필요없는 쉬운 문제였다.
 * 1_v1. 완전탐색(성공)
 * 2_v1. 완전탐색(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/68644">문제</a>
 */
class Solution68644 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1
    /**
     * @param numbers 정수 배열
     * @return 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를 배열에 오름차순으로 담아 return
     */
    public int[] solution(int[] numbers) {
        // 1. 서로 다른 두 수의 합 집합 구하기
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                set.add(numbers[i] + numbers[j]);
            }
        }
        // 2. 집합을 배열로 변환, 오름차순 정렬
        int[] answer = new int[set.size()];
        int i = 0;
        for(int n : set) {
            answer[i++]  = n;
        }
        Arrays.sort(answer);
        return answer;
    }
}