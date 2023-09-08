package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [문제명] 2048 (Easy)
 * [풀이시간] 30분
 * [한줄평] 어렵지 않게 풀 수 있는 문제였고 여러 알고리즘이 섞인 좋은 문제같다.
 * 1_v1. 완전탐색, DFS(성공)
 * [풀이] DFS로 상하좌우로 이동하여 5번을 반복한다.원본 보드의 값에서 최댓값을 초기화하고 블록이 합쳐질 때 마다 최댓값을 갱신한다.
 * @See <a href="https://www.acmicpc.net/problem/12100">문제</a>
 */
class Boj12100 {
    // 1_v1
    static int max;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // 보드의 크기
        int[][] map = new int[n][n];
        // 1. 원본 보드에서 최댓값 초기화
        max = 0;
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }
        dfs(0, map);
        System.out.println(max);
    }

    public static void dfs(int depth, int[][] map) {
        // 1. 5번 이동했으면 종료
        if(depth == 5) return;
        // 2. 상하좌우 이동
        dfs(depth + 1, moveUp(map));
        dfs(depth + 1, moveDown(map));
        dfs(depth + 1, moveLeft(map));
        dfs(depth + 1, moveRight(map));
    }

    public static int[][] moveLeft(int[][] map) {
        int[][] res = new int[n][n];
        for(int i = 0; i < n; i++) {
            int j = 0;
            Stack<Integer> stack = new Stack<>();
            for(int k = 0; k < n; k++) {
                int cur = map[i][k];
                if(cur == 0) continue;
                if(stack.isEmpty()) {
                    stack.push(cur);
                } else {
                    int prev = stack.pop();
                    if(prev == cur) {
                        res[i][j++] = cur * 2;
                        max = Math.max(max, cur * 2);
                    } else {
                        res[i][j++] = prev;
                        stack.push(cur);
                    }
                }
            }
            if(!stack.isEmpty()) res[i][j++] = stack.pop();
        }
        return res;
    }

    public static int[][] moveRight(int[][] map) {
        int[][] res = new int[n][n];
        for(int i = 0; i < n; i++) {
            int j = n - 1;
            Stack<Integer> stack = new Stack<>();
            for(int k = n - 1; k >= 0; k--) {
                int cur = map[i][k];
                if(cur == 0) continue;
                if(stack.isEmpty()) {
                    stack.push(cur);
                } else {
                    int prev = stack.pop();
                    if(prev == cur) {
                        res[i][j--] = cur * 2;
                        max = Math.max(max, cur * 2);
                    } else {
                        res[i][j--] = prev;
                        stack.push(cur);
                    }
                }
            }
            if(!stack.isEmpty()) res[i][j--] = stack.pop();
        }
        return res;
    }

    public static int[][] moveUp(int[][] map) {
        int[][] res = new int[n][n];
        for(int j = 0; j < n; j++) {
            int i = 0;
            Stack<Integer> stack = new Stack<>();
            for(int k = 0; k < n; k++) {
                int cur = map[k][j];
                if(cur == 0) continue;
                if(stack.isEmpty()) {
                    stack.push(cur);
                } else {
                    int prev = stack.pop();
                    if(prev == cur) {
                        res[i++][j] = cur * 2;
                        max = Math.max(max, cur * 2);
                    } else {
                        res[i++][j] = prev;
                        stack.push(cur);
                    }
                }
            }
            if(!stack.isEmpty()) res[i++][j] = stack.pop();
        }
        return res;
    }

    public static int[][] moveDown(int[][] map) {
        int[][] res = new int[n][n];
        for(int j = 0; j < n; j++) {
            int i = n - 1;
            Stack<Integer> stack = new Stack<>();
            for(int k = n - 1; k >= 0; k--) {
                int cur = map[k][j];
                if(cur == 0) continue;
                if(stack.isEmpty()) {
                    stack.push(cur);
                } else {
                    int prev = stack.pop();
                    if(prev == cur) {
                        res[i--][j] = cur * 2;
                        max = Math.max(max, cur * 2);
                    } else {
                        res[i--][j] = prev;
                        stack.push(cur);
                    }
                }
            }
            if(!stack.isEmpty()) res[i--][j] = stack.pop();
        }
        return res;
    }
}