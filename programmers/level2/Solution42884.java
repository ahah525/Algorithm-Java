package programmers.level2;


import java.util.HashSet;
import java.util.Set;

/**
 * [문제명] 단속 카메라
 * [한줄평] 합의 중복을 거르기 위해 Set 을 사용하고, 효율성을 고려해 부분합을 이용해 풀어야겠다고 쉽게 풀이방법을 떠올린 간단한 문제였다.
 * v1. Set, 부분합 이용(v1. 성공)
 * - 아래 예시로 입력이 들어온다고 가정할 때, 시작값을 고정해놓고 길이를 하나씩 늘려가는 방식으로 부분합을 구함
 * (7), (7, 9), (7, 9, 1), (7, 9, 1, 1), (7, 9, 1, 1, 4)
 * (9), (9, 1), (9, 1, 1), (9, 1, 1, 4), (9, 1, 1, 4, 7)
 * ...
 * (4), (4, 7), (4, 7, 9), (4, 7, 9, 1), (4, 7, 9, 1, 1)
 */
class Solution42884 {

    public static void main(String[] args) {
        // 18
        int[] elements1 = {7, 9, 1, 1, 4};
        int answer1 = solution(elements1);
        System.out.println(answer1);
    }

    /**
     *
     * @param elements 어떤 자연수로 이루어진 원형 수열
     * 3 ≤ elements의 길이 ≤ 1,000
     * 1 ≤ elements의 원소 ≤ 1,000
     * @return 원형 수열의 연속하는 부분 수열의 합으로 만들 수 있는 수가 모두 몇 가지인지
     */
    public static int solution(int[] elements) {
        // 연속 부분 수열의 합(중복 제거)
        Set<Integer> set = new HashSet<>();
        int n = elements.length;
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = 0; j < n; j++) {
                sum += elements[(i + j) % n];
                set.add(sum);
            }
        }
        return set.size();
    }
}