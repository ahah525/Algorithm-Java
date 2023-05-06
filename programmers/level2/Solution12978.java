package programmers.level2;


import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 배달
 * [풀이시간] 3시간 / 40분 / 55분
 * [한줄평] 이해하는데 너무 오래걸렸다... 나중에 복습 무조건 해야할 문제다. / 유형에 대한 문제 풀이 복습이 꼭 필요하다.
 * 1_v1. 다익스트라(성공)
 * [접근법] 최단 거리가 가장 짧은 노드를 선택하기 위해 for문 으로 직접 탐색
 * 2_v1. 다익스트라(성공)
 * [접근법] 최단 거리가 가장 짧은 노드를 선택하기 위해 PriorityQueue 사용
 * 3_v1. 다익스트라(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12978">문제</a>
 * @See <a href="https://velog.io/@yanghl98/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%B0%B0%EB%8B%AC-JAVA%EC%9E%90%EB%B0%94">풀이 참고</a>
 */
class Solution12978 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    /**
     * @param N 마을의 개수
     * @param road 각 마을을 연결하는 도로의 정보
     * @param K 음식 배달이 가능한 시간
     * @return 음식 주문을 받을 수 있는 마을의 개수
     */
    public int solution(int N, int[][] road, int K) {
        int INF = 500001;   // 거리 최댓값
        int[][] map = new int[N + 1][N + 1];    // map[i][j]: i -> j 직선 거리
        // 1. 노드 간 직선 거리 구하기
        // 1-1. 무한대로 초기화(i == j 경우를 제외하고)
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;    // i -> i 최단거리 = 0
                map[i][j] = INF;
            }
        }
        // 1-2. 간선 정보로부터 직선 거리 저장
        for(int[] r : road) {
            // 간선이 여러개 있을 수 있기 때문에 최솟값으로 저장
            if(map[r[0]][r[1]] > r[2]) {
                map[r[0]][r[1]] = r[2];
                map[r[1]][r[0]] = r[2];
            }
        }
        // 2. 시작 노드 설정하기
        boolean[] visited = new boolean[N + 1]; // visited[i]: i 노드 방문 여부
        visited[1] = true;
        // 3. 시작 노드에 대한 최단 거리 배열 초기화
        int[] d = new int[N + 1];   // d[i]: 1 -> i 로 가는 최단 거리
        for(int i = 1; i <= N; i++) {
            d[i] = map[1][i];
        }
        // 시작 노드를 제외한 나머지 (N - 1)개의 노드에 대해 반복
        for(int i = 2; i <= N; i++) {
            // 3. 방문하지 않은 노드 중에서 1번 노드로부터 최단 거리가 가장 짧은 노드 구하기
            int idx = 1;   // 선택한 노드
            int min = INF;
            for(int j = 1; j <= N; j++) {
                if(!visited[j] && min > d[j]) {
                    min = d[j];
                    idx = j;
                }
            }
            visited[idx] = true; // 해당 노드 방문처리 = 1 -> idx 최단거리가 확정됨
            // 4. 1->idx 최단거리가 확정됨에 따라 해당 노드(idx)를 거쳐 다른 노드로 가는 최단거리 갱신
            for(int j = 1; j <= N; j++) {
                // 1. 기존에 1 -> j 최단거리 값
                // 2. 1->idx 최단거리가 갱신됨에 따라 idx를 거쳐갈 때 1->idx->j 최단거리값
                d[j] = Math.min(d[j], d[idx] + map[idx][j]);
            }
        }
        // 5. N 개의 노드 중 최단거리가 K 이하인 노드 개수 구하기
        int answer = 0;
        for(int i = 1; i <= N; i++) {
            if(d[i] <= K)
                answer++;
        }
        return answer;
    }

    // 2_v1
    public int solution2(int N, int[][] road, int K) {
        int answer = 0;
        int INF = 500001;   // 거리 최댓값
        int[][] map = new int[N + 1][N + 1];    // map[i][j]: i -> j 직선 거리
        // 1. 노드 간 직선 거리 구하기
        // 1-1. 무한대로 초기화(i == j 경우를 제외하고)
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;    // i -> i 최단거리 = 0
                map[i][j] = INF;
            }
        }
        // 1-2. 간선 정보로부터 직선 거리 저장
        for(int[] r : road) {
            // 간선이 여러개 있을 수 있기 때문에 최솟값으로 저장
            if(map[r[0]][r[1]] > r[2]) {
                map[r[0]][r[1]] = r[2];
                map[r[1]][r[0]] = r[2];
            }
        }
        // 2. 시작 노드 설정하기
        boolean[] visited = new boolean[N + 1]; // visited[i]: i 노드 방문 여부
        visited[1] = true;
        // 3. 시작 노드에 대한 최단 거리 배열 초기화
        int[] d = new int[N + 1];   // d[i]: 1 -> i 로 가는 최단 거리
        for(int i = 1; i <= N; i++) {
            d[i] = map[1][i];
        }
        Queue<Integer> q = new PriorityQueue<>((o1, o2) -> d[o1] - d[o2]);
        q.add(1);
        // 시작 노드를 제외한 나머지 (N - 1)개의 노드에 대해 반복
        while(!q.isEmpty()) {
            int idx = q.poll(); // 현재 노드
            // 현재노드와 인접한 노드 검사
            for (int j = 1; j <= N; j++) {
                // 갱신될 때 큐에 넣기
                if (d[idx] + map[idx][j] < d[j]) {
                    d[j] = d[idx] + map[idx][j];
                    q.add(idx);
                }
            }
        }
        // 5. N 개의 노드 중 최단거리가 K 이하인 노드 개수 구하기
        for(int i = 1; i <= N; i++) {
            if(d[i] <= K)
                answer++;
        }
        return answer;
    }

    // 3_v1
    public int solution3(int N, int[][] road, int K) {
        int answer = 0;
        boolean[] visited = new boolean[N + 1];
        // 1. 인접행렬 초기화
        int INF = 490001;
        int[][] map = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;
                map[i][j] = INF;
            }
        }
        for(int[] r : road) {
            if(map[r[0]][r[1]] > r[2]) {
                map[r[0]][r[1]] = r[2];
                map[r[1]][r[0]] = r[2];
            }
        }
        // 2. 최단 거리 배열 초기화
        int[] d = new int[N + 1];
        Arrays.fill(d, INF);
        d[1] = 0;
        // N개의 노드에 대해 반복하여 d[i] 구하기
        for(int i = 1; i <= N; i++) {
            // 3. 방문하지 않은 노드 중 최단 거리가 가장 짧은 노드 선택하기
            int min = INF;
            int idx = 0;
            for(int j = 1; j <= N; j++) {
                if(!visited[j] && d[j] < min) {
                    min = d[j];
                    idx = j;
                }
            }
            visited[idx] = true;
            if(d[idx] <= K) answer++;
            // 4. idx를 거쳐갈 때 더 최단거리가 나오면 갱신
            for(int j = 1; j <= N; j++) {
                if(d[j] > d[idx] + map[idx][j]) {
                    d[j] = d[idx] + map[idx][j];
                }
            }
        }
        return answer;
    }
}