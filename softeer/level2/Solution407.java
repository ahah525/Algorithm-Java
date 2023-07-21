package softeer.level2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 바이러스
 * [풀이시간] 4분
 * [한줄평] 쉽게 풀 수 있는 문제였다.
 * 1_v1. 수학(성공)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=407">문제</a>
 */
class Solution407 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        // 1초당 p배 증가
        long res = k;
        for(long i = 1; i <= n; i++) {
            res = (res * p) % 1000000007;
        }
        System.out.println(res);
    }
}