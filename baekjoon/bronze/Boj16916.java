package baekjoon.bronze;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [문제명] 부분 문자열
 * [풀이시간] 25분
 * [한줄평]
 * 1_v1. (실패-시간초과)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/">문제</a>
 */
class Boj16916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String p = br.readLine();
        System.out.println(check(s, p));
    }

    // p가 s의 부분 문자열인지
    public static int check(String s, String p) {
        for(int i = 0; i <= s.length() - p.length(); i++) {
            boolean equal = true;
            for(int j = 0; j < p.length(); j++) {
                if(s.charAt(i + j) != p.charAt(j)) {
                    equal = false;
                    break;
                }
            }
            if(equal) return 1;
        }
        return 0;
    }
}