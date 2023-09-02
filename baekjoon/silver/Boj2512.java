package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 예산
 * [풀이시간] (15분)
 * [한줄평]
 * 1_v1. (실패)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/2512">문제</a>
 */
class Boj2512 {

    static int m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 지방수
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n]; // 지방별 예산 요청
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());   // 총 예산

        int max = 0;
        int start = 0;
        int end = m;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(isPossible(mid)) {
                max = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(max);
    }

    public static boolean isPossible(int standard) {
        int sum = 0;
        for(int n : arr) {
            sum += Math.min(n, standard);
            if(sum > m) return false;
        }
        return true;
    }
}