package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 프린터 큐
 * [풀이시간] 24분
 * [한줄평] 이전에 프로그래머스 완전 동일한 문제였기 때문에 더이상 풀어볼 필요는 없는 것 같다.
 * 1_v1. LinkedList(성공)
 * [풀이] 매번 가장 중요한 문서의 중요도를 구하기 위해 PriorityQueue를 사용
 * @See <a href="https://www.acmicpc.net/problem/1966">문제</a>
 */
class Boj1966 {
    static class Document {
        int idx;        // 처음 순서
        int priority;   // 중요도
        Document(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return String.format("%d %d", idx, priority);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());    // 테스트케이스 수
        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   // 문서 개수
            int m = Integer.parseInt(st.nextToken());   // 궁금한 문서의 현재 위치
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            System.out.println(getOrder(n, m, arr));
        }
    }

    public static int getOrder(int n, int m, int[] arr) {
        int order = 1;
        Queue<Document> q = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());   // 큐에 있는 문서의 중요도 내림차순 정렬
        // 1.
        for(int i = 0; i < n; i++) {
            q.add(new Document(i, arr[i]));
            pq.add(arr[i]);
        }
        while(!q.isEmpty()) {
            Document doc = q.poll();
            int maxPriority = pq.peek();    // 가장 중요한 문서의 중요도
            if(doc.priority >= maxPriority) {
                // 현재 문서가 가장 중요하면, 프린트
                pq.poll();
                // 내가 찾는 문서면, 프린트 순서 리턴
                if(doc.idx == m) return order;
                order++;
            } else {
                // 현재 문서보다 더 중요한 문서가 있으면, 맨 뒤에 넣기
                q.add(doc);
            }
        }
        return order;
    }
}