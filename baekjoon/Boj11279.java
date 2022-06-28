package baekjoon;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Boj11279 {
    public static void main(String[] args) throws IOException {
        // 빠른 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());    // 연산 개수

        // 최대힙(내림차순 정렬)
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());    //  정수
            if(x == 0) {
                if(q.isEmpty()) {
                    // 비었으면 0 출력
                    bw.write("0\n");
                } else {
                    // 있으면 최대값 출력 후 제거
                    bw.write(q.poll()+"\n");
                }
            } else {
                q.add(x);   // 추가
            }
        }
        bw.flush();
        bw.close();
    }
}
