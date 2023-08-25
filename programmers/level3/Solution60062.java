package programmers.level3;


/**
 * [문제명] 외벽 점검
 * [풀이시간] 1시간 20분
 * [한줄평] 완전탐색으로 풀어야겠다고 접근은 했는데 어떤 식으로 풀어야할지 감을 못잡아서 결국 풀이를 보고 해결했던 문제였다.
 * 1_v1. 완전탐색, DFS(성공)
 * [풀이] 모든 친구 순열(친구를 차례로 나열하는 경우의 수)에 대해 시작점이 다른 모든 취약점에 대해 검사한다.
 * [시간복잡도] O(w^2 * d^2)
 * - w : 취약점 개수(최대 15)
 * - d : 친구 수(최대 8)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/60062">문제</a>
 * @See <a href="https://dev-note-97.tistory.com/241">풀이 참고1</a>
 * @See <a href="https://leveloper.tistory.com/101">풀이 참고2</a>
 *
 */
class Solution60062 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    int[][] weaks;  // weaks[i]: weak의 i번째 취약점부터 시작했을 때
    int[] path;
    int w, d, min, MAX;

    public int solution(int n, int[] weak, int[] dist) {
        w = weak.length;    // 취약점 개수
        d = dist.length;    // 친구 수
        MAX = d + 1;        // 최댓값
        min = MAX;          // 최솟값
        path = new int[d];  //
        // 1. 시작점이 다른 모든 경우의 수 구하기
        weaks = new int[w][w];
        for(int i = 0; i < w; i++) {
            for(int j = 0; j < w; j++) {
                if(i + j < w) weaks[i][j] = weak[i + j];
                else weaks[i][j] = weak[(i + j) % w] + n;
            }
        }
        // 2.
        permu(0, new boolean[d], dist);
        // 3. 모든 친구를 투입해도 취약점을 전부 점거할 수 없는 경우 -1 리턴
        if(min == MAX) return -1;
        return min;
    }

    // 친구 순열 구하기
    public void permu(int depth, boolean[] visited, int[] dist) {
        if(depth == d) {
            check();
            return;
        }
        for(int i = 0; i < d; i++) {
            if(visited[i]) continue;
            path[depth] = dist[i];
            visited[i] = true;
            permu(depth + 1, visited, dist);
            visited[i] = false;
        }
    }

    public void check() {
        // 1. 해당 순서로 친구가 검사를 할 때, 모든 취약점 경우를 검사
        for(int[] weak : weaks) {
            // 2. 0번째 취약점에 0번째 친구 투입했을 때, 0번째 친구가 커버할 수 있는 범위 초기화
            int s = weak[0];        // 점검 시작점
            int e = s + path[0];    // 점검 끝점
            int cnt = 1;            // 점검에 참여한 친구 수
            // 3. 모든 취약점을 점검할 수 있는지 검사
            for(int i = 1; i < w; i++) {
                // 4. 해당 취약점이 점검 범위 내면, 패스(추가로 친구 투입할 필요X)
                if(weak[i] <= e) continue;
                // 5. 해당 취약점이 범위 밖이고 더이상 친구가 없으면, 불가능함
                if(cnt == path.length) {
                    cnt = MAX;
                    break;
                }
                // 6. 새로운 친구 점검 투입, 점검 범위 갱신
                s = weak[i];
                e = s + path[cnt++];
            }
            // 7. 점검에 참여한 친구 수의 최솟값 갱신
            min = Math.min(min, cnt);
        }
    }
}