package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] 최솟값 만들기
 * [풀이시간] 40분(30분 + 10분)
 * [한줄평] 이렇게까지 오래걸릴 문제는 아니었는데, 어떻게 최솟값을 구할 수 있을지 힌트를 보기전까진 접근법을 떠올리지 못했다. 문제 자체는 정말 쉬운 문제다..
 * v1. 순열(실패 - 정확성 테스트 2~16 실패, 효율성 테스트 모두 실패)
 * - A에서는 무조건 1,2,3,,, 순서로 뽑는다고 가정하고 B만 순열으로 경우의 수를 구해서 재귀로 풀었는데 시간초과가 났다.
 * v2. 정렬(성공)
 * 두 원소의 곱들의 합이 최소가 되기 위해서는 (큰값 * 작은값)을 해야 한다는 점이다!!
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12941">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/37415">힌트</a>
 */
class Solution12941 {
    public static void main(String[] args) {
        // 29
        int[] A1 = {1, 4, 2};
        int[] B1 = {5, 4, 4};
        System.out.println(solution(A1, B1));

        // 10
        int[] A2 = {1, 2};
        int[] B2 = {3, 4};
        System.out.println(solution(A2, B2));
    }

    /**
     * @param A
     * @param B
     * 배열 A, B의 크기 : 1,000 이하의 자연수
     * 배열 A, B의 원소의 크기 : 1,000 이하의 자연수
     * @return 최종적으로 누적된 최솟값
     * 배열 A, B에서 각각 한 개의 숫자를 뽑아 두 수를 곱합니다. 이러한 과정을 배열의 길이만큼 반복하며, 두 수를 곱한 값을 누적하여 더합니다.
     */
    public static int solution(int []A, int []B) {
        int answer = 0;
        int n = A.length;
        // A 오름차순, B 내림차순(내림차순은 시간이 오래걸리기 때문에, 오름차순을 하고 마지막 인덱스부터 탐색하는 방향으로 구현함)
        Arrays.sort(A);
        Arrays.sort(B);
        for(int i = 0; i < n; i++) {
            answer += A[i] * B[n - 1 - i];
        }
        return answer;
    }
}