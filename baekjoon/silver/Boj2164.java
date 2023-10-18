package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 카드2
 * [풀이시간] 5분
 * [한줄평] 큐를 이용해서 그대로 풀기만 하면 아주 기초적인 문제로 더 풀어볼 가치도 없다.
 * 1_v1. LinkedList(성공)
 * @See <a href="https://www.acmicpc.net/problem/2164">문제</a>
 */
class Boj2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            q.add(i);
        }
        while(q.size() > 1) {
            q.poll();
            q.add(q.poll());
        }
        System.out.println(q.poll());
    }
}