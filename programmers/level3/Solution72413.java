package programmers.level3;


import java.util.Arrays;

/**
 * [문제명] 합승 택시 요금
 * [풀이시간] 1시간 20분
 * [한줄평] 최단 거리를 구하는 문제의 일종으로 다익스트라로 접근해야한다는 것은 알았는데 그 이후에 어떻게 해야할지 몰라 결국 풀이 힌트를 보고 풀었다.
 * 1_v1. 다익스트라(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72413">문제</a>
 * @See <a href="https://velog.io/@pppp0722/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-Level3-%ED%95%A9%EC%8A%B9-%ED%83%9D%EC%8B%9C-%EC%9A%94%EA%B8%88-Java">풀이 힌트</a>
 */
class Solution72413 {
    public static void main(String[] args) {
        // 82
        int[][] fares1 = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        System.out.println(solution(6, 4, 6, 2, fares1));

        // 14
        int[][] fares2 = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
        System.out.println(solution(7, 3, 4, 1, fares2));
    }

    static int INF = 100001;    // 무한대

    /**
     * @param n 지점갯수
     * @param s 출발지점
     * @param a A의 도착지점
     * @param b B의 도착지점
     * @param fares 지점 사이의 예상 택시요금
     * @return
     */
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        // 1. 인접 행렬 구하기
        int[][] map = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                map[i][j] = INF;
            }
        }
        // 간선의 가중치 값 반영
        for(int[] f : fares) {
            map[f[0]][f[1]] = f[2];
            map[f[1]][f[0]] = f[2];
        }
        // s -> 모든 지점 최단 거리
        int[] together = dijkstra(s, n, map);
        int min = Integer.MAX_VALUE;
        // s -> i : 같이
        // i -> a, i -> b : 각자
        for(int i = 1; i <= n; i++) {
            int[] alone = dijkstra(i, n, map);
            min = Math.min(min, together[i] + alone[a] + alone[b]);
        }
        return min;
    }

    /**
     * @param s 시작지점
     * @param n 모든 노드 개수
     * @param map 인접행렬
     * @return 최단 거리 배열
     */
    public static int[] dijkstra(int s, int n, int[][] map) {
        // 2. 시작노드 설정
        boolean[] visited = new boolean[n + 1];
        visited[s] = true;
        // 3. 최단 거리 배열 초기화
        int[] d = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            d[i] = map[s][i];
        }
        // n - 1 개의 노드 반복
        for(int i = 2; i <= n; i++) {
            // 최단 거리 배열 최솟값 찾기
            int min = INF;
            int idx = -1;
            for(int j = 1; j <= n; j++) {
                if(!visited[j] && min > d[j]) {
                    min = d[j];
                    idx = j;
                }
            }
            if(idx == -1) break;
            visited[idx] = true;
            // s -> idx -> j or s -> j
            for(int j = 1; j <= n; j++) {
                if(!visited[j] && d[idx] + map[idx][j] < d[j]) {
                    d[j] = d[idx] + map[idx][j];
                }
            }
        }
        System.out.println(Arrays.toString(d));
        return d;
    }
}