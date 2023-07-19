package softeer.level2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [문제명] 지도 자동 구축
 * [풀이시간] 8분
 * [한줄평] 규칙을 찾아 수식화해서 푸는 쉬운 문제였다.
 * 1_v1. 수학(성공)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=413">문제</a>
 */
class Solution413 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = 2;
        for (int i = 0; i < n; i++) {
            m = 2 * m - 1;
        }
        System.out.println(m * m);
    }
}