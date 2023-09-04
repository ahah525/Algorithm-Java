package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 가르침
 * [풀이시간] (32분)
 * [한줄평]
 * 1_v1. 완전탐색(실패-시간초과)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/1062">문제</a>
 */
class Boj1062 {
    static int max = 0;
    static boolean[] visited;
    static Set<Integer>[] words;
    // 1_v1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 단어의 총 개수
        int k = Integer.parseInt(st.nextToken());   // 가르칠 문자의 개수
        words = new Set[n];
        for(int i = 0; i < n; i++) {
            words[i] = new HashSet<>();
            String s = br.readLine();
            for(char c : s.toCharArray()) {
                words[i].add(c - 'a');
            }
//            System.out.println(words[i]);
        }
        //
        visited = new boolean[26];
        Set<Integer> set = new HashSet<>();
//        set.add('a' - 'a');
//        set.add('n' - 'a');
//        set.add('t' - 'a');
//        set.add('i' - 'a');
//        set.add('c' - 'a');
//        System.out.println(set);
//        for(int i : set) {
//            visited[i] = true;
//        }
        comb(0, -1, k);
        System.out.println(max);
    }

    public static void comb(int depth, int prev, int k) {
//        if(depth > k) return;
        if(depth == k) {
            //
            int cnt = 0;
            for(Set<Integer> w : words) {
                if(isReadable(w)) cnt++;
            }
//            System.out.println(cnt);
            max = Math.max(max, cnt);
            return;
        }
        for(int i = prev + 1; i < 26; i++) {
            visited[i] = true;
            comb(depth + 1, i, k);
            visited[i] = false;
        }
    }

    public static boolean isReadable(Set<Integer> w) {
        for(int i : w) {
            if(!visited[i]) return false;
        }
        return true;
    }
}