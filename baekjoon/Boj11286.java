package baekjoon;

import java.io.*;
import java.util.PriorityQueue;

public class Boj11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());    // 정수

        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> {
            int absA = Math.abs(a);
            int absB = Math.abs(b);
            if (absA == absB) {
                return a - b;         // 절댓값 같으면 오름차순 정렬
            } else {
                return absA - absB;   // 절댓값 다르면 절댓값 오름차순 정렬
            }
        });
        for(int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (q.isEmpty()) {
                    // 비었으면 0 출력
                    bw.write("0\n");
                } else {
                    // 있으면 절댓값 최소값 출력
                    bw.write(q.poll() + "\n");
                }
            } else {
                q.add(x);   // 추가
            }
        }
        bw.flush();
        bw.close();
    }
}
