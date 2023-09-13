package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 크리보드
 * [풀이시간] (22분)
 * [한줄평]
 * 1_v1. BFS(실패-메모리 초과)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/11058">문제</a>
 */
class Boj11058 {
    static class Keyboard {
        int screen;
        int drag;
        int buffer;
        int depth;

        public Keyboard(int screen, int drag, int buffer, int depth) {
            this.screen = screen;
            this.drag = drag;
            this.buffer = buffer;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    //
        Queue<Keyboard> q = new LinkedList<>();
        q.add(new Keyboard(0, 0, 0, 0));
        int max = 0;
        while(!q.isEmpty()) {
            Keyboard k = q.poll();
            if(k.depth == n) {
                max = Math.max(max, k.screen);
                continue;
            }
            //
            q.add(new Keyboard(k.screen + 1, k.drag, k.buffer, k.depth + 1));
            if(k.screen != 0)
                q.add(new Keyboard(k.screen, k.screen, k.buffer, k.depth + 1));
            if(k.drag != 0)
                q.add(new Keyboard(k.screen, 0, k.drag, k.depth + 1));
            if(k.buffer != 0)
                q.add(new Keyboard(k.screen + k.buffer, k.drag, k.buffer, k.depth + 1));
        }
        System.out.println(max);
    }
}