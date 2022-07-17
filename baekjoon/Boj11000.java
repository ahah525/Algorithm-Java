package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj11000 {
    static class Class {
        int start;  // 시작 시각
        int end;    // 끝 시각

        public Class(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Class> classes = new ArrayList();
        StringTokenizer st;
        int res = 0;     // 강의실 개수
        int n = Integer.parseInt(br.readLine());    // 수업 개수

        // n개의 수업 시작 시각, 끝 시각 입력받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            classes.add(new Class(s, e));
        }
        // 시작 시간 -> 끝 시작 시간 기준 오름차순 정렬
        Collections.sort(classes, (Class o1, Class o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });

        // 끝 시각, 시작 시각 오름차순 정렬되도록 우선순위 큐 생성
        PriorityQueue<Class> q = new PriorityQueue<>((o1, o2) -> {
            if (o1.end == o2.end) {
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        });
        // 강의실에 수업 배치하기
        for (int i = 0; i < n; i++) {
            // 큐가 비어있으면 그냥 넣기
            if(q.isEmpty()){
                q.add(classes.get(i));
                continue;
            }
            Class c = q.peek(); // 끝나는 시각이 가장 빠른 수업
            // 진행되고 있는 수업 중 가장 빨리 끝나는 수업이 현재 진행해야할 수업의 시작 시각보다 빠르다면
            if(c.end <= classes.get(i).start){
                q.poll();   // 큐에서 빼기
            }
            q.add(classes.get(i));  // 현재 선택한 수업 큐에 넣기
            if(res < q.size()) res = q.size();  // 지금 사용되고 있는 강의실 수가 지금껏 최댓값이면 업데이트
        }
        System.out.println(res);
    }
}
