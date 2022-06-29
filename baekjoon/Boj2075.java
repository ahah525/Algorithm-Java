package baekjoon;

import java.io.*;
import java.util.PriorityQueue;

public class Boj2075 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 맵 크기

        // 최소힙(오름차순)
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            String[] arr = br.readLine().split(" ");  // 공백 구분
            for(int j = 0; j < n; j++) {
                if (i == 0) {
                    // 1. n개가 아직 안찼으면 넣기(첫번째 행이면)
                    q.add(Integer.parseInt(arr[j]));
                } else {
                    // 2. n개가 다 찼으면 최소값과 비교후, 더 크면 꺼내고 넣기
                    if(q.peek() < Integer.parseInt(arr[j])) {
                        q.poll();
                        q.add(Integer.parseInt(arr[j]));
                    }
                }
            }
        }
        // n번째 큰 수 출력
        System.out.println(q.poll());
    }
}
