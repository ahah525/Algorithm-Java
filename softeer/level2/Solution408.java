package softeer.level2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [문제명] 8단 변속기
 * [풀이시간] 8분
 * [한줄평] 다시 풀 필요도 없는 너무 쉬운 문제였다.
 * 1_v1. 문자열(성공)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=408">문제</a>
 */
class Solution408 {
    public static void main(String args[]) throws IOException {
        String s1 = "1 2 3 4 5 6 7 8";
        String s2 = "8 7 6 5 4 3 2 1";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if(s.equals(s1)) {
            System.out.println("ascending");
        } else if(s.equals(s2)) {
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }
    }
}