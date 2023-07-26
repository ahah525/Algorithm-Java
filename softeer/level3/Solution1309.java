package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] [HSAT 5회 정기 코딩 인증평가 기출] 성적 평가
 * [풀이시간] 34분
 * [한줄평] 반복되는 코드가 많긴 했지만 정렬 코드를 짤 수 있으면 어렵지 않게 풀 수 있는 문제다.
 * 1_v1. 정렬, 구현(성공)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=1309">문제</a>
 */
class Solution1309 {
    static int n;
    static class Person {
        int id;
        int score;
        Person(int id, int score) {
            this.id = id;
            this.score = score;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        Person[] total = new Person[n];
        for(int i = 0; i < n; i++) {
            total[i] = new Person(i, 0);
        }
        for(int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Queue<Person> pq = new PriorityQueue<>((o1, o2) -> o2.score - o1.score);
            for(int j = 0; j < n; j++) {
                int score = Integer.parseInt(st.nextToken());
                pq.add(new Person(j, score));
                total[j].score += score;
            }
            printArr(calc(pq));
        }
        //
        Arrays.sort(total, (o1, o2) -> o2.score - o1.score);
        printArr(calc(total));
    }

    // 각 참가자의 등수를 배열로 리턴
    public static int[] calc(Person[] arr) {
        int[] res = new int[n];
        int prev = -1;  // 이전 점수
        int prevIdx = 1; // 이전 등수
        int idx = 1;    // 사람수
        for(Person p : arr) {
            if(p.score != prev) {
                // 이전 점수와 다르면,
                res[p.id] = idx;
                prev = p.score;
                prevIdx = idx;
            } else {
                // 이전 점수와 같으면, 같은 등수
                res[p.id] = prevIdx;
            }
            idx++;
        }
        return res;
    }

    // 각 참가자의 등수를 배열로 리턴
    public static int[] calc(Queue<Person> pq) {
        int[] res = new int[n];
        int prev = -1;  // 이전 점수
        int prevIdx = 1; // 이전 등수
        int idx = 1;    // 사람수
        while(!pq.isEmpty()) {
            Person p = pq.poll();
            if(p.score != prev) {
                // 이전 점수와 다르면,
                res[p.id] = idx;
                prev = p.score;
                prevIdx = idx;
            } else {
                // 이전 점수와 같으면, 같은 등수
                res[p.id] = prevIdx;
            }
            idx++;
        }
        return res;
    }

    // 각 참가자의 등수 출력
    public static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(arr[i]);
            if(i == n - 1) break;
            sb.append(" ");
        }
        System.out.println(sb);
    }
}