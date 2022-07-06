package baekjoon;

import java.io.*;
import java.util.*;

public class Boj7662 {
    static HashMap<Integer, Integer> map;   // 이중 우선순위 큐
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());    // 테스트 케이스 수
        for(int i = 0; i < t; i++) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();   // 최소힙
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());    //  최대힙
            map = new HashMap<>();    // (정수, 개수)

            int k = Integer.parseInt(br.readLine());    // 연산 개수
            for (int j = 0; j < k; j++) {
                // 공백 구분 입력
                StringTokenizer st = new StringTokenizer(br.readLine());
                String mode = st.nextToken();               // 연산 종류(D, I)
                int n = Integer.parseInt(st.nextToken());   // 정수
                switch(mode) {
                    case "D":
                        // 이중 우선순위 큐가 비었으면 삭제 연산 패스
                        if(map.isEmpty()) break;
                        if(n == 1) {
                            // 최댓값 삭제
                            delete(maxHeap);
                        } else {
                            // 최솟값 삭제
                            delete(minHeap);
                        }
                        break;
                    case "I":
                        // 최소힙, 최대힙에 모두 삽입
                        minHeap.offer(n);
                        maxHeap.offer(n);
                        map.put(n, map.getOrDefault(n, 0) + 1); // 원래 값에 + 1
                        break;
                }
                //System.out.println(map.keySet());
            }
            // 이중 우선순위 큐의 최댓값, 최솟값 출력
            if(map.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                int max = delete(maxHeap);  // 최댓값
                int min = (map.isEmpty()) ? max : delete(minHeap);    // 최솟값
                System.out.println(max + " " + min);
            }
        }
    }
    static int delete(PriorityQueue<Integer> q) {
        int cur = 0;    // 진짜 최소/최대 값
        while(true) {
            // 일단 삭제
            cur = q.poll(); // 꺼낸 값
            int cnt = map.getOrDefault(cur, 0); // 꺼낸 값의 개수
            // 1. 꺼낸 값이 이중 우선순위 큐에 없으면(과거에 삭제되었어야할 값이었으면), 한번 더 꺼내기
            if (cnt == 0) continue;
            // 2. 꺼낸 값이 이중 우선순위 큐에 있으면, 이중 우선순위 큐의 값 변경하기
            if (cnt == 1) {
                // 2-1. 1개 있으면 key, value 삭제
                map.remove(cur);
            } else {
                // 2-2. 2개 이상이면 value - 1 로 수정
                map.put(cur, cnt - 1);
            }
            break;
        }
        return cur;
    }
}
