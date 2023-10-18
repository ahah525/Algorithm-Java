package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 이모티콘
 * [풀이시간] 30분(14분+16분) / 25분
 * [한줄평] 반례를 찾는데 시간이 좀 걸리긴 했지만 구현은 쉬운 편이었고 다시 풀어봐도 좋을 문제다.
 * / 기초적인 BFS 문제였고
 * 1_v1. BFS(실패-메모리 초과)
 * [반례] 3가지 연산을 수행할 수 있는 조건을 고려하지 않음
 * 1_v2. BFS(성공) -> 빠름
 * [해결] 이미 나왔던 조합(i, j)은 더 볼필요가 없음
 * - visited[i][j] : 화면에 i개, 클립보드에 j개가 만들어졌던 경우가 있었는지 여부
 * 2_v1. BFS(성공)
 * @See <a href="https://www.acmicpc.net/problem/14226">문제</a>
 * @See <a href="https://hanil0623.tistory.com/3">반례</a>
 */
class Boj14226 {
    // 1_v2
    static class Emoticon {
        int screen;     // 화면 이모티콘 수
        int clipboard;  // 클립보드 이모티콘 수
        int cnt;        // 화면과 클립보드에 이모티콘을 만드는데 걸리는 시간
        Emoticon(int screen, int clipboard, int cnt) {
            this.screen = screen;
            this.clipboard = clipboard;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());    // 만들어야하는 이모티콘수
        System.out.println(bfs(s));
    }

    // 화면에 이모티콘 s개를 만드는데 걸리는 최소 시간
    public static int bfs(int s) {
        Queue<Emoticon> q = new LinkedList<>();
        boolean[][] visited = new boolean[2001][2001];  // 2001로 해야하는 이유
        // 1. 시작 방문처리(화면에 1개, 클립보드에 0개)
        q.add(new Emoticon(1, 0, 0));
        visited[1][0] = true;
        while(!q.isEmpty()) {
            Emoticon e = q.poll();
            // 2. 화면에 이모티콘 s개가 만들어졌으면 최소 시간 저장후 종료
            if(e.screen == s) return e.cnt;
            // 3가지 연산을 사용하여 다음 스텝 진행
            // 1. 화면 이모티콘을 클립보드에 저장(화면에 0개면 패스)
            if(0 < e.screen && e.screen < s && !visited[e.screen][e.screen]) {
                q.add(new Emoticon(e.screen, e.screen, e.cnt + 1));
                visited[e.screen][e.screen] = true;
            }
            // 2. 클립보드 이모티콘을 화면에 붙여넣기(클립보드에 0개면 패스)
            if(0 < e.clipboard && e.screen < s && !visited[e.screen + e.clipboard][e.clipboard]) {
                q.add(new Emoticon(e.screen + e.clipboard, e.clipboard, e.cnt + 1));
                visited[e.screen + e.clipboard][e.clipboard] = true;
            }
            // 3. 화면에 있는 이모티콘 1개 삭제(화면에 1개 미만이면 패스)
            if(0 < e.screen && !visited[e.screen - 1][e.clipboard]) {
                q.add(new Emoticon(e.screen - 1, e.clipboard, e.cnt + 1));
                visited[e.screen - 1][e.clipboard] = true;
            }
        }
        return 0;
    }

    // 2_v1
//    static class Emoji {
//        int monitor;
//        int clipboard;
//
//        Emoji(int monitor, int clipboard) {
//            this.monitor = monitor;
//            this.clipboard = clipboard;
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int s = Integer.parseInt(br.readLine());    //
//        int MAX = 2000;
//        // 1 -> s
//        int[][] visited = new int[MAX][MAX];
//        Queue<Emoji> q = new LinkedList<>();
//        q.add(new Emoji(1, 0));
//        visited[1][0] = 1;
//        //
//        while(!q.isEmpty()) {
//            Emoji e = q.poll();
//            if(e.monitor == s) {
//                System.out.println(visited[e.monitor][e.clipboard] - 1);
//                break;
//            }
//            // 1. 화면에 있는 이모티콘을 클립보드에 저장
//            if(0 < e.monitor && e.monitor < s && visited[e.monitor][e.monitor] == 0) {
//                q.add(new Emoji(e.monitor, e.monitor));
//                visited[e.monitor][e.monitor] = visited[e.monitor][e.clipboard] + 1;
//            }
//            // 2. 클립보드에 있는 이모티콘 화면에 붙여넣기
//            if(0 < e.clipboard && e.monitor < s && visited[e.monitor + e.clipboard][e.clipboard] == 0) {
//                q.add(new Emoji(e.monitor + e.clipboard, e.clipboard));
//                visited[e.monitor + e.clipboard][e.clipboard] = visited[e.monitor][e.clipboard] + 1;
//            }
//            // 3. 화면에 있는 이모티콘 1개 삭제
//            if(0 < e.monitor && visited[e.monitor - 1][e.clipboard] == 0) {
//                q.add(new Emoji(e.monitor - 1 , e.clipboard));
//                visited[e.monitor - 1][e.clipboard] = visited[e.monitor][e.clipboard] + 1;
//            }
//        }
//    }
}