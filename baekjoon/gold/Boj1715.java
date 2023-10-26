package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 카드 정렬하기
 * [풀이시간] 20분(11분+9분)
 * [한줄평] 아이디어만 떠올리면 아주 쉽게 풀 수 있는 문제였다.
 * 1_v1. 그리디(실패-틀림)
 * [풀이] 오름차순 정렬하고, 앞에서부터 차례로 합친다.
 * 1_v2. 그리디, 우선순위큐(성공)
 * [풀이] 가장 작은 카드 2개를 뽑아서 1개로 만든다.
 * @See <a href="https://www.acmicpc.net/problem/1715">문제</a>
 */
class Boj1715 {
    // 1_v2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
        int sum = 0;
        while(pq.size() > 1) {
            int cnt = pq.poll() + pq.poll();
            pq.add(cnt);
            sum += cnt;
        }
        System.out.println(sum);
    }
}