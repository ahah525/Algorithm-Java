package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 케빈 베이컨의 6단계 법칙
 * [풀이시간] 21분
 * [한줄평] 플로이드 워셜 알고리즘으로 풀 수 있는 전형적인 문제였다.
 * 1_v1. 그래프/플로이드-워셜(성공)
 * [풀이] n이 최대 100이기때문에, 플로이드-워셜 알고리즘으로 모든 노드에서 모든 노드로 최단 거리를 구할 수 있다.
 * @See <a href="https://www.acmicpc.net/problem/1389">문제</a>
 */
class Boj1389 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 노드수
        int m = Integer.parseInt(st.nextToken());   // 간선수
        int INF = n;
        // 1. 인접행렬 초기화
        int[][] map = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                map[i][j] = INF;
            }
        }
        // 2. 가중치값을 1로 저장
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
            map[b][a] = 1;
        }
        // 3. 모든 노드 -> 모든 노드 최단거리 갱신
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    // i -> j, i -> k -> j
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        // 4. (한 노드에서 다른 모든 노드로 가는 거리의 합) 중 최솟값 구하기
        int min = Integer.MAX_VALUE;
        int minIdx = 1;
        for(int i = 1; i <= n; i++) {
            int sum = 0;
            for(int j = 1; j <= n; j++) {
                sum += map[i][j];
            }
            if(min > sum) {
                min = sum;
                minIdx = i;
            }
        }
        System.out.println(minIdx);
    }
}