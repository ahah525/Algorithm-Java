package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [문제명] A와 B
 * [풀이시간] 11분
 * [한줄평] 그냥 그대로 구현만 하면 간단하게 풀 수 있는 문제였다.
 * 1_v1. 그리디, 문자열(성공)
 * [풀이] s->t로 만들 수 있는지는 t->s로 만들 수 있는지 검사하는 것과 같다. 2가지 연산 모두 뒤에 문자를 추가하는 것이기 때문에 끝문자만 반복적으로 검사하면 된다.
 * @See <a href="https://www.acmicpc.net/problem/12904">문제</a>
 */
class Boj12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        StringBuilder sb = new StringBuilder(t);
        // t를 s로 만들 수 있는지 검사
        while(sb.length() > s.length()) {
            if(sb.charAt(sb.length() - 1) == 'A') {
                // 1. t의 끝문자가 A이면, A제거
                sb.deleteCharAt(sb.length() - 1);
            } else {
                // 2. t의 끝문자가 B이면, B제거 후 T 뒤집기
                sb.deleteCharAt(sb.length() - 1).reverse();
            }
        }
        int res = 0;
        if(s.equals(sb.toString())) res = 1;
        System.out.println(res);
    }
}