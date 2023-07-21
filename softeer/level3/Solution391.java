package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 수퍼바이러스
 * [풀이시간] 40분(17분+23분)
 * [한줄평] 
 * 1_v1. (실패)
 * [반례] n이 최대 10^16이기 때문에 반복문이 최대 10^17 수행될 수 있어 시간초과가 난다.
 * 1_v2. 분할정복(실패)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=391">문제</a>
 */
class Solution391 {
    static int MOD = 1000000007;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long k = Long.parseLong(st.nextToken());
        long p = Long.parseLong(st.nextToken());
        long n = Long.parseLong(st.nextToken());
        // 1초 증가량 구하기
        // for(long i = 1; i <= 10; i++) {
        //     p = (p * p) % 1000000007;
        // }
        n *= 10;
        long res = k * f(n, p);
        // System.out.println(p);
        System.out.println(res);
    }

    public static long f(long n, long p) {
        if(n == 1) return p;
        long res = f(n / 2, p);
        if(n % 2 == 0) return (res * res) % MOD;
        return (res * res * p) % MOD;
    }
}