package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [문제명] 징검다리
 * [풀이시간] 50분(10분+20분+20분)
 * [한줄평] 모든 방법을 다 해보다가 결국에 DP로 풀었던 문제다. 다음에 다시 풀어봐도 좋을 것 같다.
 * 1_v1. DP(실패)
 * [풀이] 연속으로 증가하는 경우 중 최댓값을 구하는 것으로 착각하고 잘못 풀었다.
 * 1_v2. DFS, 완전탐색(실패 - 시간초과)
 * 1_v3. DP(성공)
 * [점화식] dp[i] = Math.max(dp[i], dp[j] + 1) (0 <= j < i)
 * - dp[i]: i번째 돌을 선택했을 때 밟을 수 있는 돌의 최대 개수
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=390">문제</a>
 * @See <a href="https://velog.io/@jyleedev/softeer-%EC%A7%95%EA%B2%80%EB%8B%A4%EB%A6%AC-java">풀이 힌트</a>
 */
class Solution390 {
    // 1_v3
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int max = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        // System.out.println(Arrays.toString(dp));
        System.out.println(max);
    }
}