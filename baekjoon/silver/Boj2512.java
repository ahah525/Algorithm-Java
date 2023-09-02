package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 예산
 * [풀이시간] 30분(15분+15분)
 * [한줄평] 이분탐색 문제라는 것은 빨리 알아차렸는데 반례를 찾지 못해 결국 풀이를 확인했던 문제로 꼭 복습이 필요하다.
 * 1_v1. 이분탐색(실패)
 * [반례] 상한액 탐색 범위의 최댓값: "총예산"으로 초기화
 * 1_v2. 이분탐색(성공)
 * [해결] 상한액 탐색 범위의 최댓값: "예산요청 금액 중 최댓값"으로 초기화
 * @See <a href="https://www.acmicpc.net/problem/2512">문제</a>
 */
class Boj2512 {
    // 1_v2
    static int m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 지방수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = 0;  // 상한액 최솟값으로 초기화
        int end = 0;    // 상한액 최댓값으로 초기화
        arr = new int[n]; // 지방별 예산 요청
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, arr[i]);
        }
        m = Integer.parseInt(br.readLine());   // 총 예산

        int max = 0;
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

    // 상한액이 standard 일 때 배정한 금액이 예산이하의 금액인지
    public static boolean isPossible(int standard) {
        int sum = 0;
        for(int n : arr) {
            sum += Math.min(n, standard);
            if(sum > m) return false;
        }
        return true;
    }
}