package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 수퍼바이러스
 * [풀이시간] (17분)
 * [한줄평]
 * 1_v1. (실패)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=391">문제</a>
 */
class Solution391 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        long n = Long.parseLong(st.nextToken());

        long res = k;
        for(long i = 1; i <= 10 * n; i++) {
            res = (res * p) % 1000000007;
        }
        System.out.println(res);
    }
}