package programmers.level2;


/**
 * [문제명] N-Queen
 * [풀이시간] 27분 / 32분
 * [한줄평] 문제 유형에 대한 힌트를 보고 풀었던 문제로 꼭 복습이 필요하다.
 * / 1차원 배열로 풀어야겠다는 것을 빨리 떠올려서 저번보다 쉽게 풀긴했지만 다시 한 번 풀어봐야할 것 같다.
 * 1_v1. DFS(성공)
 * [풀이] 좌표 선택 여부를 2차원 배열이 아닌 1차원 배열으로 기록한다.
 * - path[i]: i번째 행에서 선택한 열
 * 2_v1. DFS(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12952">문제</a>
 */
class Solution12952 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    int answer;
    int[] path; // path[i]: i번째 행에 선택한 열

    /**
     * @param n 체스판의 가로 세로의 세로의 길이
     * @return n개의 퀸이 조건에 만족 하도록 배치할 수 있는 방법의 수
     */
    public int solution(int n) {
        answer = 0;
        path = new int[n];
        dfs(0, n);
        return answer;
    }

    public void dfs(int depth, int n) {
        // n개를 모두 선택했으면 카운트
        if(depth == n) answer++;
        // depth 행에서 선택 가능한 c열 방문
        for(int c = 0; c < n; c++) {
            if(isPossible(depth, c)) {
                path[depth] = c;
                dfs(depth + 1, n);
            }
        }
    }

    // (depth, c)를 선택할 수 있는지 검사
    public boolean isPossible(int depth, int c) {
        for(int r = 0; r < depth; r++) {
            // 같은 열 혹은 대각선에 위치한 좌표를 이미 선택했다면 (depth, c) 선택 불가
            if(c == path[r] || (depth - r) == Math.abs(c - path[r])) {
                return false;
            }
        }
        return true;
    }

    // 2_v1
//    boolean[] visited;
//    int[] path;
//    int answer;
//    public int solution(int n) {
//        answer = 0;
//        visited = new boolean[n];
//        path = new int[n];
//        dfs(0, n);
//        return answer;
//    }
//
//    public void dfs(int depth, int n) {
//        if(depth == n) {
//            answer++;
//            return;
//        }
//        //
//        for(int i = 0; i < n; i++) {
//            if(visited[i]) continue;
//            if(depth == 0 || isPossible(depth, i)) {
//                path[depth] = i;
//                visited[i] = true;
//                dfs(depth + 1, n);
//                path[depth] = -1;
//                visited[i] = false;
//            }
//        }
//    }
//
//    public boolean isPossible(int depth, int i) {
//        for(int j = 0; j < depth; j++) {
//            int dx = Math.abs(depth - j);
//            int dy = Math.abs(i - path[j]);
//            if(dx == dy) return false;
//        }
//        return true;
//    }
}