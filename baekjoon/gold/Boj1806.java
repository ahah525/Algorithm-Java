package baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 부분합
 * [풀이시간] / 35분(15분+20분)
 * [한줄평]
 * / 투포인터의 아주 기초적인 문제였는데, 누적합 힌트만 보고 어렵게 풀면서 시간이 오래걸렸다.
 * 1_v1. 투포인터(성공)
 * [풀이]
 * 1. 누적합 < s, l 포인터 오른쪽으로 1칸
 * 2. 누적합 >= s, 수열 길이 최솟값 갱신, r 포인터 오른쪽으로 1칸
 * 2_v1. 누적합, 완전탐색(실패-시간초과)
 * 2_v2. 투포인터(성공)
 * @See <a href="https://www.acmicpc.net/problem/1806">문제</a>
 */
public class Boj1806 {
    // 1_v1, 2_v2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n]; // 수열
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int min = n + 1;
        int sum = 0;
        int l = 0;
        int r = 0;
        while(l <= r) {
            if(sum < s) {
                if(r == n) break;
                sum += arr[r];
                r++;
            } else if(sum >= s) {
                min = Math.min(min, r - l);
                sum -= arr[l];
                l++;
            }
        }
        if(min == n + 1) min = 0;
        System.out.println(min);
    }
}
