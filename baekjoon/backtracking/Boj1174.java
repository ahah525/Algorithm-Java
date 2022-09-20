package baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 입력
 * N (1,000,000보다 작거나 같은 자연수)
 * -------------------------------
 * 출력
 * N번째 작은 줄어드는 수(그러한 수가 없을 때는 -1)
 */
public class Boj1174 {
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // 음이 아닌 정수

        bfs();
    }

    /**
     * q : 줄어드는 수 기록 용도
     * cnt : 줄어드는 수 개수(q에서 꺼낼 때마다 개수 세기)
     * 종료 조건 : cnt == n(n번째 작은 줄어드는 수를 찾았을 때)
     */
    public static void bfs() {
        int cnt = 0;
        Queue<Long> q = new LinkedList<>();
        // 0 ~ 9 넣기
        for(long i = 0; i <= 9; i++) {
            q.offer(i);
        }

        while(!q.isEmpty()) {
            long v = q.poll();
            cnt++;
            System.out.println("v = " + v);

            if(cnt == n) {
                System.out.println(v);
                return;
            }

            String s = Long.toString(v);
            int end = s.charAt(s.length() - 1) - '0';   // 현재 숫자의 마지막 숫자
            for(int i = 0; i < end; i++) {
                char c = (char) (i + '0');              // 뒤에 붙일 숫자(0 ~ end - 1)
                long nv = Long.parseLong(s + c);    // 뒤에 숫자를 붙여 새롭게 만든 숫자
                q.offer(nv);
            }
        }
        System.out.println(-1);
        return;
    }
}
