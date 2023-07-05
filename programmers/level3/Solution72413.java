package programmers.level3;


/**
 * [문제명] 합승 택시 요금
 * [풀이시간] 1시간 20분 / 22분 / 38분
 * [한줄평] 최단 거리를 구하는 문제의 일종으로 다익스트라로 접근해야한다는 것은 알았는데 그 이후에 어떻게 해야할지 몰라 결국 풀이 힌트를 보고 풀었다.
 * / 다익스트라 풀이를 응용만 해서 풀 수 있는 문제였다.
 * / 다익스트라 풀이 방법이 생각나지 않아서 알고리즘을 보고 풀었다. 주기적으로 복습이 꼭 필요하다.
 * 1_v1. 그래프(성공)
 * [풀이] 다익스트라
 * 2_v1. 그래프(성공)
 * [풀이] 1_v1 동일
 * 3_v1. 그래프(성공)
 * [풀이] 1_v1 동일
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

    // 1_v1, 2_v1, 3_v1
    static int INF = 20000000;
    static int[][] map;

    /**
     * @param n 지점갯수
     * @param s 출발지점
     * @param a A의 도착지점
     * @param b B의 도착지점
     * @param fares 지점 사이의 예상 택시요금
     * @return A, B 두 사람이 s에서 출발해서 각각의 도착 지점까지 택시를 타고 간다고 가정할 때, 최저 예상 택시요금
     */
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        // 1. 인접행렬 초기화
        map = new int[n + 1][n + 1]; // 요금
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                map[i][j] = INF;
            }
        }
        // 간선 정보 반영
        for(int[] fare : fares) {
            map[fare[0]][fare[1]] = fare[2];
            map[fare[1]][fare[0]] = fare[2];
        }
        // 2. s 에서 다른 모든 노드로 가는 최단 거리 계산(같이 가는 비용)
        int[] together = dijkstra(n, s);
        int answer = INF;
        for(int i = 1; i <= n; i++) {
            // 3. i 에서 다른 모든 노드로 가는 최단 거리 계산(혼자 가는 비용)
            int[] alone = dijkstra(n, i);
            // s => i 같이, i => a, i => b 는 각자 이동
            // together[i]: s => i 노드까지 최소 비용
            // alone[a]: i => a 노드까지 최소 비용
            // alone[b]: i => b 노드까지 최소 비용
            answer = Math.min(answer, together[i] + alone[a] + alone[b]);
        }
        return answer;
    }

    /**
     * @param s 시작지점
     * @param n 모든 노드 개수
     * @return 최단 거리 배열
     */
    public static int[] dijkstra(int n, int s) {
        boolean[] visited = new boolean[n + 1];
        // 1. 최단 거리 배열 초기화
        int[] d = new int[n + 1];   // d[i] : s노드에서 i노드까지 최소 비용
        for(int i = 1; i <= n; i++) {
            d[i] = map[s][i];
        }
        // 2. 시작 노드 설정
        visited[s] = true;
        // (n - 1)개의 노드에 대해 반복
        for(int i = 1; i <= n; i++) {
            // 4. 방문하지 않은 노드 중 최단 거리 노드(idx) 찾기
            int min = INF;
            int idx = -1; // 최단 거리 노드
            for(int j = 1; j <= n; j++) {
                if(!visited[j] && min > d[j]) {
                    min = d[j];
                    idx = j;
                }
            }
            // 5. 방문하지 않은 노드 중 s에서 갈 수 있는 노드가 더이상 없다면 break
            if(idx == -1) break;
            // 6. 최단 거리 노드 방문 처리
            visited[idx] = true;
            // 7. 최단 거리 노드를 거쳐 다른 노드로 가는 최단 거리 갱신
            for(int j = 1; j <= n; j++) {
                if(visited[j]) continue;
                // s => j, s => idx -> j 중 최솟값으로 갱신
                d[j] = Math.min(d[j], d[idx] + map[idx][j]);
            }
        }
        // System.out.println(Arrays.toString(d));
        return d;
    }
}