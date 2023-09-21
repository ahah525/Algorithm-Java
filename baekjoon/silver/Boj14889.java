package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 스타트와 링크
 * [풀이시간] 30분
 * [한줄평] 어떻게 효율적으로 풀 수 있을지 고민하다가 절반을 뽑았을 때마다 각 팀의 능력치 합을 구하는 방식으로 풀었다. 2번 풀이가 각 팀의 점수를 더 효율적으로 계산할 수 있다.
 * 1_v1. DFS, 완전탐색(성공)
 * [풀이] 각 팀의 조합을 구하기 위해 리스트를 사용해서 합을 구했다.
 * 1. visited[i]가 true면 listA에 넣고, false면 listB에 넣는다.
 * 2. 각 리스트에서 2개씩 뽑는 경우의 수를 구해서 합을 구한다.
 * 1_v2. DFS, 완전탐색(성공) -> 추천
 * [풀이] visited[i]값이 같을 때만 더해주는 방식으로 합을 구했다.
 * 1. visited[i] == true && visited[j] == true -> A팀 점수에 누적, visited[i] == false && visited[j] == false -> B팀 점수에 누적
 * @See <a href="https://www.acmicpc.net/problem/14889">문제</a>
 * @See <a href="https://st-lab.tistory.com/122">다른 풀이</a>
 */
class Boj14889 {
    static int min, n;
    static int[][] s;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        min = Integer.MAX_VALUE;
        n = Integer.parseInt(br.readLine());
        s = new int[n][n];
        visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //
        dfs(0, 0);
        System.out.println(min);
    }

    // n개에서 절반을 선택하는 조합 구하기
    public static void dfs(int depth, int start) {
        // 1. 절반을 다 선택했으면 두 팀의 계산
        if(depth == n / 2) {
            min = Math.min(min, calc());
            return;
        }
        for(int i = start; i < n; i++) {
            visited[i] = true;
            dfs(depth + 1, i + 1);
            // 이미 최솟값이 0이면, 다른 경우를 더 확인할 필요가 없으므로 리턴
            if(min == 0) return;
            visited[i] = false;
        }
    }

    // 두 팀의 차 계산
    public static int calc() {
        int a = 0;
        int b = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(visited[i] == true && visited[j] == true) a += s[i][j] + s[j][i];
                else if(visited[i] == false && visited[j] == false) b += s[i][j] + s[j][i];
            }
        }
        return Math.abs(a - b);
    }
}