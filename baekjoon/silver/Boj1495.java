package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 기타리스트
 * [풀이시간] (16분)
 * [한줄평]
 * 1_v1. DFS(실패-시간 초과)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/1495">문제</a>
 */
class Boj1495 {
    static int n, m, max = -1;
    static int[] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 곡의 개수
        int s = Integer.parseInt(st.nextToken());   // 시작 볼륨
        m = Integer.parseInt(st.nextToken());   // 볼륨 최댓값
        v = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, s);
        System.out.println(max);
    }
    public static void dfs(int depth, int volume) {
        if(depth == n) {
            max = Math.max(max, volume);
            return;
        }
        if(volume + v[depth] <= m) dfs(depth + 1, volume + v[depth]);
        if(volume - v[depth] >= 0) dfs(depth + 1, volume - v[depth]);
    }
}