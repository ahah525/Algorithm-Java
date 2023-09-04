package baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] N번째 큰 수
 * [풀이시간] 5분
 * [한줄평] 너무 기초적인 최대힙 문제여서 더 이상 풀어볼 필요가 없다.
 * 1_v1. PriorityQueue(성공)
 * [풀이] 최대힙
 * 2_v1. PriorityQueue(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://www.acmicpc.net/problem/2075">문제</a>
 */
public class Boj2075 {
    // 1_v1
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());    // 맵 크기
//
//        // 최소힙(오름차순)
//        PriorityQueue<Integer> q = new PriorityQueue<>();
//        for (int i = 0; i < n; i++) {
//            String[] arr = br.readLine().split(" ");  // 공백 구분
//            for(int j = 0; j < n; j++) {
//                if (i == 0) {
//                    // 1. n개가 아직 안찼으면 넣기(첫번째 행이면)
//                    q.add(Integer.parseInt(arr[j]));
//                } else {
//                    // 2. n개가 다 찼으면 최소값과 비교후, 더 크면 꺼내고 넣기
//                    if(q.peek() < Integer.parseInt(arr[j])) {
//                        q.poll();
//                        q.add(Integer.parseInt(arr[j]));
//                    }
//                }
//            }
//        }
//        // n번째 큰 수 출력
//        System.out.println(q.poll());
//    }

    // 2_v1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                maxHeap.add(Integer.parseInt(st.nextToken()));
            }
        }
        //
        for(int i = 0; i < n - 1; i++) {
            maxHeap.poll();
        }
        System.out.println(maxHeap.poll());
    }
}
