package baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Boj1174 {
    private static int n;
    private static List<Integer> temp = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // 음이 아닌 정수

        bfs();
    }

    public static void bfs() {
        int cnt = 0;
        Queue<Long> q = new LinkedList<>();
        // 초기화
        for(long i = 0; i <= 9; i++) {
            q.offer(i);
        }

        while(!q.isEmpty()) {
            long v = q.poll();
            cnt++;
            if(cnt == n) {
                System.out.println(v);
                return;
            }
            String s = Long.toString(v);
            int end = s.charAt(s.length() - 1) - '0';
            for(int i = 0; i < end; i++) {
                char ss = (char) (i + '0');
                long nv = Long.parseLong(s + ss);
                q.offer(nv);
            }
        }
        System.out.println(-1);
        return;
    }
}
