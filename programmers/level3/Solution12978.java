package programmers.level3;


/**
 * [문제명] 배달
 * [풀이시간] 3시간
 * [한줄평] 이해하는데 너무 오래걸렸다... 나중에 복습 무조건 해야할 문제다.
 * 1_v1. 다익스트라(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12978">문제</a>
 * @See <a href="https://velog.io/@yanghl98/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%B0%B0%EB%8B%AC-JAVA%EC%9E%90%EB%B0%94">풀이 참고</a>
 */
class Solution12978 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param N 마을의 개수
     * @param road 각 마을을 연결하는 도로의 정보
     * @param K 음식 배달이 가능한 시간
     * @return 음식 주문을 받을 수 있는 마을의 개수
     */
    public int solution(int N, int[][] road, int K) {
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
        // 시작 노드를 제외한 나머지 (N - 1)개의 노드에 대해 반복
        for(int i = 2; i <= N; i++) {
            // 3. 방문하지 않은 노드 중에서 가장 최단 거리가 짧은 노드를 구해서 방문하기
            int idx = 1;   // 선택한 노드
            int min = INF;
            for(int j = 1; j <= N; j++) {
                if(!visited[j] && min > d[j]) {
                    min = d[j];
                    idx = j;
                }
            }
            visited[idx] = true;
            // 4. 해당 노드를 거쳐 다른 노드로 가는 비용을 계산하여 최단 거리 배열을 갱신
            for(int j = 1; j <= N; j++) {
                // 1) 해당 노드를 거치는 것과 2) 거치지 않는 것 중 최솟값으로 갱신
                if(d[idx] + map[idx][j] < d[j]) {
                    d[j] = d[idx] + map[idx][j];
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
}