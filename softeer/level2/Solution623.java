package softeer.level2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [문제명] [21년 재직자 대회 예선] 비밀 메뉴
 * [풀이시간] 5분
 * [한줄평] 정석적인 풀이는 아니라 쉽게 풀 수 있었던 문제다.
 * 1_v1. 문자열(성공)
 * [풀이] contains()
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=623">문제</a>
 */
class Solution623 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        String secret = br.readLine();
        String s = br.readLine();
        if(s.contains(secret)) System.out.println("secret");
        else System.out.println("normal");
    }
}