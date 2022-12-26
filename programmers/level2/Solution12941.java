package programmers.level2;


/**
 * [문제명] 최솟값 만들기
 * [풀이시간]
 * [한줄평]
 * v1. 순열(실패 - 정확성 테스트 2~16 실패, 효율성 테스트 모두 실패)
 * - A에서는 무조건 1,2,3,,, 순서로 뽑는다고 가정하고 B만 순열으로 경우의 수를 구해서 재귀로 풀었는데 시간초과가 났다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12941">문제</a>
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

    static int min;

    /**
     * @param A
     * @param B
     * 배열 A, B의 크기 : 1,000 이하의 자연수
     * 배열 A, B의 원소의 크기 : 1,000 이하의 자연수
     * @return 최종적으로 누적된 최솟값
     * 배열 A, B에서 각각 한 개의 숫자를 뽑아 두 수를 곱합니다. 이러한 과정을 배열의 길이만큼 반복하며, 두 수를 곱한 값을 누적하여 더합니다.
     */
    public static int solution(int []A, int []B) {
        min = Integer.MAX_VALUE;
        int n = A.length;
        boolean[] visited = new boolean[n];

        dfs(0, 0, visited, A, B, n);

        return min;
    }

    public static void dfs(int depth, int sum, boolean[] visited, int[] A, int[] B, int n) {
        if(depth == n) {
            min = Math.min(min, sum);
            return;
        }
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                int res = A[depth] * B[i];
                visited[i] = true;
                dfs(depth + 1, sum + res, visited, A, B, n);
                visited[i] = false;
            }
        }
    }
}