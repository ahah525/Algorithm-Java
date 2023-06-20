package programmers.level2;


/**
 * [문제명] N-Queen
 * [풀이시간] 27분
 * [한줄평] 문제 유형에 대한 힌트를 보고 풀었던 문제로 꼭 복습이 필요하다.
 * 1_v1. DFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12952">문제</a>
 */
class Solution12952 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

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
}