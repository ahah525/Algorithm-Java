package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 성적 평균
 * [풀이시간] 12분
 * [한줄평] 구간합을 구하는 문제로 누적합을 이용하면 쉽게 풀 수 있다.
 * 1_v1. 누적합(성공)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=389">문제</a>
 */
class Solution389 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        // 1. 누적합 구하기
        int[] score = new int[n + 1];
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += Integer.parseInt(st.nextToken());
            score[i + 1] = sum;
        }
        // 2.
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            double avg = (double) (score[b] - score[a - 1]) / (b - a + 1);
            System.out.println(String.format("%.2f", avg));
        }
    }
}