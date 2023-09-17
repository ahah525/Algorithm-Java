package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 퇴사
 * [풀이시간] 10분, 15분
 * [한줄평] 이전에 풀었던 15486과 범위만 다르고 완전 동일한 문제여서 쉽게 풀 수 있었고 더 안풀어봐도 될 것 같다.
 * 1_v1. DP(성공)
 * 1_v2. DFS,백트래킹(성공)
 * [풀이] i번째 상담을 선택하는 경우, 선택하지 않는 경우 2가지로 재귀를 수행한다.
 * @See <a href="https://www.acmicpc.net/problem/14501">문제</a>
 */
class Boj14501 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());    //
//        int[] t = new int[n + 2];
//        int[] p = new int[n + 2];
//        StringTokenizer st;
//        for(int i = 1; i <= n; i++) {
//            st = new StringTokenizer(br.readLine());
//            t[i] = Integer.parseInt(st.nextToken());
//            p[i] = Integer.parseInt(st.nextToken());
//        }
//        //
//        int[] dp = new int[n + 2];
//        for(int i = n; i > 0; i--) {
//            if(i + t[i] > n + 1) {
//                // i번째 상담을 기간 내에 할 수 없는 경우
//                dp[i] = dp[i + 1];
//            } else {
//                //
//                dp[i] = Math.max(dp[i + 1], p[i] + dp[i + t[i]]);
//            }
//        }
////        System.out.println(Arrays.toString(dp));
//        System.out.println(dp[1]);
//    }

    static int max, n;
    static int[] t, p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        max = 0;
        n = Integer.parseInt(br.readLine());
        t = new int[n + 1];
        p = new int[n + 1];
        StringTokenizer st;
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }
        dfs(1, 0);
        System.out.println(max);
    }

    public static void dfs(int idx, int sum) {
        // 1. 가능한 상담을 모두 골랐으면 최댓값 갱신
        if(idx > n) {
            max = Math.max(max, sum);
            return;
        }
        // 2. idx번째 상담을 선택하는 경우
        if(idx + t[idx] <= n + 1) dfs(idx + t[idx], sum + p[idx]);
        // 3. idx번째 상담을 선택하지 않는 경우
        dfs(idx + 1, sum);
    }
}