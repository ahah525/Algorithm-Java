package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제명] 스타트와 링크
 * [풀이시간] 30분
 * [한줄평] 어떻게 효율적으로 풀 수 있을지 고민하다가 절반을 뽑았을 때마다 각 팀의 능력치 합을 구하는 방식으로 풀었다.
 * 1_v1. DFS, 완전탐색(성공)
 * [풀이] n개서 절반을 뽑는 경우의 수를 구하고, 각 팀의 차이의 최솟값을 구한다.
 * @See <a href="https://www.acmicpc.net/problem/14889">문제</a>
 */
class Boj14889 {
    static int min, n;
    static int[][] s;
    static List<Integer> listA, listB;
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

    public static void dfs(int depth, int start) {
        if(depth == n / 2) {
            //
            listA = new ArrayList<>();
            listB = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                if(visited[i]) listA.add(i);
                else listB.add(i);
            }
            int a = 0;
            int b = 0;
            for(int i = 0; i < n / 2; i++) {
                for(int j = i + 1; j < n / 2; j++) {
                    a += s[listA.get(i)][listA.get(j)] + s[listA.get(j)][listA.get(i)];
                    b += s[listB.get(i)][listB.get(j)] + s[listB.get(j)][listB.get(i)];
                }
            }
            min = Math.min(min, Math.abs(a - b));
            return;
        }
        for(int i = start; i < n; i++) {
            visited[i] = true;
            dfs(depth + 1, i + 1);
            visited[i] = false;
        }
    }
}