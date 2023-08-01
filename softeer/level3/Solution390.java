package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 징검다리
 * [풀이시간] (10분)
 * [한줄평]
 * 1_v1. (실패)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=390">문제</a>
 */
class Solution390 {
    // 1_v1
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //
        int max = 1;
        int[] dp = new int[n];
        dp[n - 1] = 1;
        for(int i = n - 2; i >= 0; i--) {
            if(arr[i] < arr[i + 1]) {
                dp[i] = dp[i + 1] + 1;
                max = Math.max(max, dp[i]);
            } else {
                dp[i] = 1;
            }
        }
        System.out.println(max);
    }
}