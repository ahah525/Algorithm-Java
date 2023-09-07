package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [문제명] 퇴사 2
 * [풀이시간] (35분)
 * [한줄평]
 * 1_v1. DP(실패)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/15486">문제</a>
 */
class Boj15486 {
    // 1_v1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    //
        int[] t = new int[n + 1];   // 기간
        int[] p = new int[n + 1];   // 이익
        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }
        //
        int[] d = new int[n + 2];
        for(int i = n; i >= 1; i--) {
            // 상담 종료일이 기간을 벗어나면, 해당 상담은 선택X
            if(i + t[i] - 1 > n) continue;
            // i일 상담을 선택하는 경우 & i일 상담을 선택하지 않는 경우 중 최댓값
            d[i] = Math.max(p[i] + d[i + t[i]], d[i + 1]);
        }
        System.out.println(Arrays.toString(d));
        System.out.println(d[1]);
    }
}