package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 신기한 소수
 * [풀이시간] 9분
 * [한줄평] 아주 기초적인 문제여서 쉽게 풀 수 있었고 더 안풀어봐도 될 것 같다.
 * 1_v1. DFS/백트래킹(성공)
 * [풀이] 끝에 어떤 숫자 1개를 추가했을 때 소수일 경우에만 탐색한다.
 * @See <a href="https://www.acmicpc.net/problem/2023">문제</a>
 */
class Boj2023 {
    static Queue<Integer> q;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    //
        q = new PriorityQueue<>();
        dfs(0, 0);
        while(!q.isEmpty()) {
            System.out.println(q.poll());
        }
    }

    public static void dfs(int depth, int num) {
        if(depth == n) {
            q.add(num);
            return;
        }
        for(int i = 1; i < 10; i++) {
            int res = num * 10 + i;
            if(isPrime(res)) dfs(depth + 1, res);
        }
    }

    public static boolean isPrime(int num) {
        if(num == 1) return false;
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}