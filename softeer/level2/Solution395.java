package softeer.level2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] 금고털이
 * [풀이시간] 18분
 * [한줄평] 정렬을 하거나 PriorityQueue 로 쉽게 풀 수 있는 문제였다.
 * 1_v1. PriorityQueue(성공)
 * [풀이] 무게 당 가격이 높은 보석부터 배낭을 채운다.
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=395">문제</a>
 */
class Solution395 {
    static class Jewel {
        int weight;
        int price;
        Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());   // 배낭 무게
        int n = Integer.parseInt(st.nextToken());   // 귀금속 종류
        // 1. 무게당 가격 내림차순 정렬
        Queue<Jewel> pq = new PriorityQueue<>((o1, o2) -> o2.price - o1.price);
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            pq.add(new Jewel(weight, price));
        }
        // 2. 최대 가격 계산
        int sum = 0;
        while(w > 0) {
            // 3. 더이상 채울 보석이 없으면 종료
            if(pq.isEmpty()) break;
            Jewel j = pq.poll();
            int min = Math.min(w, j.weight); // 배낭에 넣을 수 있는 보석 무게
            // 4. 꺼낸 보석 무게만큼 차감, 가격 합산
            w -= min;
            sum += min * j.price;
        }
        System.out.println(sum);
    }
}