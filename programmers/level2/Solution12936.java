package programmers.level2;


/**
 * [문제명] 줄 서는 방법
 * [풀이시간] (20분)
 * [한줄평]
 * 1_v1. 순열(실패-효율성 테스트 모두 실패)
 * - 단순 재귀로 k번째 경우의 수를 찾을 때까지 모든 경우의 수 탐색하는 방법
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12936">문제</a>
 */
class Solution12936 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    int[] path;
    int cnt;
    public int[] solution(int n, long k) {
        boolean[] visited = new boolean[n + 1];
        path = new int[n];
        cnt = 0;

        permu(0, visited, n, k);

        return path;
    }

    public void permu(int depth, boolean[] visited, int n, long k) {
        if(cnt == k) {
            return;
        }
        if(depth == n) {
            // System.out.println(Arrays.toString(path));
            cnt++;
            return;
        }
        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                path[depth] = i;
                visited[i] = true;
                permu(depth + 1, visited, n, k);
                if(cnt == k) return;
                path[depth] = 0;
                visited[i] = false;
            }
        }
    }
}