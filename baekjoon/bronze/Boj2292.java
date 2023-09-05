package baekjoon.bronze;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [문제명] 벌집
 * [풀이시간] 18분22
 * [한줄평] 규칙만 찾으면 쉽게 풀 수 있는 문제여서 더 안풀어봐도 될 것 같다.
 * 1_v1. 수학(성공)
 * [풀이] 한 육각형의 숫자 개수가 1, 6, 12, 18... 6의 배수로 늘어난다는 규칙을 적용하여 풀었다.
 * 1번째 육각형: 1 ~ 1(1개)
 * 2번째 육각형: 2 ~ 7(6개)
 * 3번째 육각형: 8 ~ 19(12개)
 * 3번째 육각형: 20 ~ 37(18개)
 * @See <a href="https://www.acmicpc.net/problem/2292">문제</a>
 */
class Boj2292 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 1;
        int num = 1;
        //
        while(n > num) {
            num += 6 * cnt;
            cnt++;
        }
        System.out.println(cnt);
    }
}