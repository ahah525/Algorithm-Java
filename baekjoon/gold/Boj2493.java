package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 탑
 * [풀이시간] (27분)
 * [한줄평]
 * 1_v1. 완전탐색(실패-시간초과)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/2493">문제</a>
 */
class Boj2493 {
    // 1_v1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 탑 개수(5*10^5)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
//        Stack<Top> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
//            stack.push(new Top(i, arr[i]));
        }
        //
        int[] answer = new int[n];
        for(int i = n - 1; i > 0; i--) {
            // arr[i] 이상의 높이를 가진 탑 구하기
            for(int j = i - 1; j >= 0; j--) {
                if(arr[i] <= arr[j]) {
                    answer[i] = j + 1;
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}