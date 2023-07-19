package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] [21년 재직자 대회 본선] 비밀 메뉴2
 * [풀이시간] 1시간(25분+35분)
 * [한줄평] 처음에 완전탐색으로 풀었다가 시간초과가 나서 결국에는 풀이를 보고 해결했다. 나중에 꼭 다시 풀어봐야할 문제다.
 * 1_v1. 완전탐색(실패)
 * [풀이]
 * 1_v2. DP(성공)
 * [풀이] LCS 알고리즘
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=633">문제</a>
 * @See <a href="https://gaegosoo.tistory.com/104">풀이 참고</a>
 * @See <a href="https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence">LCS 알고리즘</a>
 */
class Solution633 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st.nextToken();
        // 1. split 해서 각 배열에 숫자 저장
        int[] a = new int[N];
        int[] b = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        // 2. 최장 공통 문자열 찾기
        int max = 0;
        int[][] dp = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if(a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.println(max);
    }
}