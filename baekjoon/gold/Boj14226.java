package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 이모티콘
 * [풀이시간] (14분)
 * [한줄평]
 * 1_v1. BFS(실패-메모리 초과)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/14226">문제</a>
 */
class Boj14226 {
    static class Emoticon {
        int screen;
        int clipboard;
        int cnt;
        Emoticon(int screen, int clipboard, int cnt) {
            this.screen = screen;
            this.clipboard = clipboard;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());    // 만들어야하는 이모티콘수
        Queue<Emoticon> q = new LinkedList<>();
        q.add(new Emoticon(1, 0, 0));
        int min = 0;
        while(!q.isEmpty()) {
            Emoticon e = q.poll();
            if(e.screen == s) {
                min = e.cnt;
                break;
            }
            // 1. 화면 이모티콘을 클립보드에 저장
            q.add(new Emoticon(e.screen, e.screen, e.cnt + 1));
            // 2. 클립보드 이모티콘을 화면에 붙여넣기
            q.add(new Emoticon(e.screen + e.clipboard, e.clipboard, e.cnt + 1));
            // 3. 화면에 있는 이모티콘 중 1개를 삭제
            q.add(new Emoticon(e.screen - 1, e.clipboard, e.cnt + 1));
        }
        System.out.println(min);
    }
}