package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [문제명] 카드 정렬하기
 * [풀이시간] (11분)
 * [한줄평]
 * 1_v1. 그리디(실패-틀림)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/1715">문제</a>
 */
class Boj1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        //
        Arrays.sort(arr);
        int sum = arr[0] + arr[1];
        for(int i = 2; i < n; i++) {
            sum += sum + arr[i];
        }
        System.out.println(sum);
    }
}