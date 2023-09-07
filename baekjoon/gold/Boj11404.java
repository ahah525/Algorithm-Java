package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 플로이드
 * [풀이시간] 20분
 * [한줄평] 그냥 플로이드 워셜 알고리즘을 구현하는 문제로 연습용으로 좋다.
 * 1_v1. 그래프(성공)
 * [풀이] 플로이드 워셜
 * @See <a href="https://www.acmicpc.net/problem/11404">문제</a>
 */
class Boj11404 {
    // 1_v1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 노드 수
        int m = Integer.parseInt(br.readLine());    // 간선 수
        int INF = 10000000;
        // 1. 인접행렬 초기화
        int[][] map = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(i == j) continue;
                map[i][j] = INF;
            }
        }
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[a][b] = Math.min(map[a][b], c);
        }
        // 2.
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    // i ~> j, i ~> k ~> j
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        //
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(map[i][j] == INF) sb.append(0);
                else sb.append(map[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}