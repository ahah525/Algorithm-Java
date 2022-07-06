package baekjoon;

import java.io.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj21939 {
    static class Problem {
        int p;  // 문제 번호
        int l;  // 난이도

        public Problem(int p, int l) {
            this.p = p;
            this.l = l;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Problem> minHeap = new PriorityQueue<>((o1, o2) -> {
            if(o1.l == o2.l) {
                // 난이도가 같으면 번호로 비교
                return o1.p - o2.p;
            }
            return o1.l - o2.l;
        });  // 최소힙(오름차순)
        PriorityQueue<Problem> maxHeap = new PriorityQueue<>((o1, o2) -> {
            if (o1.l == o2.l) {
                // 난이도가 같으면 번호로 비교
                return o2.p - o1.p;
            }
            return o2.l - o1.l;
        }); // 최대힙(내림차순)
        StringTokenizer st;
        HashSet<Integer> solved = new HashSet<>();  // 푼 문제 리스트
        int n = Integer.parseInt(br.readLine());    // 문제의 개수
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());    // 공백 구분 입력
            int p = Integer.parseInt(st.nextToken()); // 문제 번호
            int l = Integer.parseInt(st.nextToken()); // 난이도
            Problem problem = new Problem(p, l);
            minHeap.offer(problem);     // 최소힙에 삽입
            maxHeap.offer(problem);     // 최대힙에 삽입

        }
        // 명령문 입력
        int m = Integer.parseInt(br.readLine());    // 명령문 개수
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());    // 명령문
            int p, l;
            switch(st.nextToken()) {
                case "recommend":
                    int x = Integer.parseInt(st.nextToken());
                    // 1이면 최고 난이도 문제 번호 출력
                    if (x == 1) {
                        while(true) {
                            // solved에 있으면 하나 뺴고 다시 진행
                            if(solved.contains(maxHeap.peek().p)) {
                                maxHeap.poll();
                                solved.remove(maxHeap.peek().p);
                            } else {
                                // 난이도 가장 높은 문제 번호 출력
                                bw.write(maxHeap.peek().p + "\n");
                                break;
                            }
                        }
                    } else if (x == -1) {
                        while(true) {
                            // solved에 있으면 하나 뺴고 다시 진행
                            if(solved.contains(minHeap.peek().p)) {
                                minHeap.poll();
                                solved.remove(minHeap.peek().p);
                            } else {
                                // 난이도 가장 낮은 문제 번호 출력
                                bw.write(minHeap.peek().p + "\n");
                                break;
                            }
                        }
                    }
                    // -1이면 최저 난이도 문제 번호 출력
                    break;
                case "add":
                    // 난이도가 L, 문제번호 P인 문제 추가
                    p = Integer.parseInt(st.nextToken());
                    l = Integer.parseInt(st.nextToken());
                    Problem problem = new Problem(p, l);
                    minHeap.offer(problem);
                    maxHeap.offer(problem);   // 최대힙 삽입
                    break;
                case "solved":
                    // 문제번호 P인 문제 제거
                    p = Integer.parseInt(st.nextToken());
                    solved.add(p);   // solved에 삽입
                    break;
            }
        }
        bw.flush();
        bw.close();
    }
}