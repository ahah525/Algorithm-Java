package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 연산자 끼워넣기
 * [풀이시간] 19분
 * [한줄평] 아주 기초적인 완전탐색 문제로 더 안풀어봐도 될 것 같다.
 * 1_v1. 완전탐색, DFS(성공)
 * [풀이] (n-1)개의 연산자를 나열하는 모든 경우의 수에 대해 결과값 구한다.
 * @See <a href="https://www.acmicpc.net/problem/14888">문제</a>
 */
class Boj14888 {
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int n;
    static int[] num;
    static int[] op;   // +-*/
    static int[] use;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // 수의 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int[n];
        for(int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        op = new int[4];
        use = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, num[0]);
        //
        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int depth, int res) {
        if(depth == n - 1) {
            // (n - 1)개의 연산자를 다 선택했으면
            min = Math.min(min, res);
            max = Math.max(max, res);
            return;
        }
        //
        for(int i = 0; i < 4; i++) {
            if(use[i] == op[i]) continue;
            use[i]++;
            switch(i) {
                case 0:
                    dfs(depth + 1, plus(res, num[depth + 1]));
                    break;
                case 1:
                    dfs(depth + 1, minus(res, num[depth + 1]));
                    break;
                case 2:
                    dfs(depth + 1, multiply(res, num[depth + 1]));
                    break;
                case 3:
                    dfs(depth + 1, divide(res, num[depth + 1]));
                    break;
            }
            use[i]--;
        }
    }

    public static int plus(int a, int b) {
        return a + b;
    }

    public static int minus(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        // 1. 양수 / 양수
        if(a >= 0) return a / b;
        // 2. 음수 / 양수
        return -(-a / b);
    }
}