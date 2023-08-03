package softeer.level4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] H-클린알파
 * [풀이시간] 10분
 * [한줄평] 레벨이 잘못 표기된 것 같은 너무 쉬운 문제였다. 다시 풀어볼 필요도 없다.
 * 1_v1. 구현(성공)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=423">문제</a>
 */
class Solution423 {
    public static void main(String args[]) throws IOException {
        int MOD = 1000000007;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long p = Long.parseLong(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long cnt = 0;
        for(int i = 0; i < n; i++) {
            long in = Long.parseLong(st.nextToken());
            cnt = (cnt * p + in) % MOD;
        }
        System.out.println(cnt);
    }
}