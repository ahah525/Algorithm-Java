package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 가르침
 * [풀이시간] 52분(32분 + 20분)
 * [한줄평] 쉬운 문제였는데, 푸는데 너무 오래걸렸던 문제라 다시 풀어봐야할 것 같다.
 * 1_v1. 완전탐색, DFS(실패-시간초과)
 * [풀이] 문자 26개 중에서 K개 선택하기
 * 1_v2. 완전탐색, DFS(성공)
 * [풀이] 문자 21개 중에서 (K - 5)개 선택하기
 * @See <a href="https://www.acmicpc.net/problem/1062">문제</a>
 */
class Boj1062 {
    static int max = 0;
    static boolean[] visited;
    static String[] words;
    // 1_v1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 단어의 총 개수
        int k = Integer.parseInt(st.nextToken());   // 가르칠 문자의 개수
        words = new String[n];
        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            words[i] = s.substring(4, s.length() - 4);
        }
        // 1. a, c, i, n, t 는 무조건 선택
        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['c' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        // 2. 최소한 고를 수 있는 문자가 5개 이상이어야 읽을 수 있음
        if(k >= 5) comb(5, -1, k);
        System.out.println(max);
    }

    public static void comb(int depth, int prev, int k) {
        if(depth == k) {
            // 1. 읽을 수 있는 단어 개수 세기
            int cnt = 0;
            for(String w : words) {
                if(isReadable(w)) cnt++;
            }
            // 2. 최댓값 갱신
            max = Math.max(max, cnt);
            return;
        }
        for(int i = prev + 1; i < 26; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            comb(depth + 1, i, k);
            visited[i] = false;
        }
    }

    public static boolean isReadable(String w) {
        for(int c : w.toCharArray()) {
            if(!visited[c - 'a']) return false;
        }
        return true;
    }
}