package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 주유소
 * [풀이시간] 23분
 * [한줄평] N의 범위를 보고 완전탐색으로 풀 수는 없다고 생각했고 아이디어를 빨리 떠올려서 쉽게 풀었던 문제다. 한번 쯤 더 풀어봐도 좋을 것 같다.
 * 1_v1. 그리디(성공)
 * [풀이] 최소비용은 주유 가격이 싼 곳에서 최대한 많은 주유를 하는 것이다.
 * @See <a href="https://www.acmicpc.net/problem/">문제</a>
 */
class Boj13305 {
    // 1_v1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dis = new int[n - 1]; // i ~ (i+1) 도시를 연결하는 도로의 길이
        int[] price = new int[n];   // i 도시 주유소의 리터당 가격
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n - 1; i++) {
            dis[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }
        long cost = 0;  // 최소 비용
        int i = 0;
        while(i < n - 1) {
            int sum = 0;
            int j = i;
            // 현재 주유소(i) 가격 <= 다른 주유소(j) 이면, 현재 주유소에서 모두 주유하는 것이 최소 비용
            while(j < n - 1 && price[i] <= price[j]) {
                sum += dis[j];
                j++;
            }
            cost += (long) price[i] * sum;
//            System.out.println(sum);
            i = j;
        }
        System.out.println(cost);
    }
}