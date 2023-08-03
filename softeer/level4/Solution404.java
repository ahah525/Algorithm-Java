package softeer.level4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 복잡한 조립라인1
 * [풀이시간] 1시간 30분
 * [한줄평] 문제 자체를 이해하기가 어려워서 입력 받는 코드를 작성하는 부분에서 시간이 많이 걸렸다. 저번에 풀었던 문제의 연장선이라 점화식을 세우는 것 자체는 어렵지 않았다.
 * 1_v1. DP(성공)
 * [점화식] dp[i][j] = play[i][j] + Math.min(min, dp[i - 1][k] + move[i - 1][k][j]) (0 <= k < K)
 * dp[i][j]: i작업장 j라인에서 작업을 수행할 때 최솟값
 * play[i][j]: i작업장 j라인 수행시간
 * move[i][j][k]: i작업장 j라인 -> (i+1)작업장 k라인 이동시간
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=404">문제</a>
 */
class Solution404 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());   // 라인 수
        int N = Integer.parseInt(st.nextToken());   // 작업장 수
        int[][] play = new int[N][K]; // play[i][j]: i작업장 j라인 수행시간
        int[][][] move = new int[N - 1][K][K]; // move[i][j][k]: i작업장 j라인 -> (i+1)작업장 k라인 이동시간
        int[][] dp = new int[N][K];    // dp[i][j]: i작업장 j라인에서 작업을 수행할 때 최솟값
        // 1. N줄 입력받기
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 2. i작업장 j라인 수행시간 저장
            for(int j = 0; j < K; j++) {
                play[i][j] = Integer.parseInt(st.nextToken());
            }
            if(i == N - 1) break;
            // 3. i작업장 j라인 -> (i+1)작업장 k라인 이동시간 저장(j != k)
            for(int j = 0; j < K; j++) {
                for(int k = 0; k < K; k++) {
                    if(j == k) continue;
                    move[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        // 2. dp 초기화
        for(int i = 0; i < K; i++) {
            dp[0][i] = play[0][i];
        }
        // 3. 1~(N-1)작업장에 대해 반복
        for(int i = 1; i < N; i++) {
            for(int j = 0; j < K; j++) {
                // 4. i작업장 j라인에서 작업을 수행할 때 최솟값 계산
                int min = Integer.MAX_VALUE;
                for(int k = 0; k < K; k++) {
                    // (i-1)작업장 k라인 -> i작업장 j라인
                    // (i-1)작업장 k라인 최솟값 + k->j 라인 이동 시간
                    min = Math.min(min, dp[i - 1][k] + move[i - 1][k][j]);
                }
                dp[i][j] = play[i][j] + min;
            }
        }
        // 5. (N-1)작업장 각 라인에서 작업을 끝냈을 때 중 최솟값 계산
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < K; i++) {
            answer = Math.min(answer, dp[N - 1][i]);
        }
        System.out.println(answer);
    }
}