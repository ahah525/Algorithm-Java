package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [문제명] 탑
 * [풀이시간] 45분(27분+18분)
 * [한줄평] 스택으로 풀어야 한다는 힌트를 보고 겨우 아이디어를 떠올려서 풀었던 문제다. 프로그래머스에서 예전에 비슷한 문제를 풀었던 것 같은 기억이 있는데, 꼭 복습해봐야곘다.
 * 1_v1. 완전탐색(실패-시간초과)
 * [풀이] 완전 단순하게 i번째 탑의 레이저를 수신하는 탑을 찾기 위해 매번 [0, i-1] 범위의 탑을 탐색했다.
 * 1_v2. Stack(성공)
 * @See <a href="https://www.acmicpc.net/problem/2493">문제</a>
 */
class Boj2493 {
    // 1_v2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 탑 개수(5*10^5)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();
        int idx = n - 1;
        while(idx >= 0) {
            while(!stack.isEmpty()) {
                // 1. 스택의 가장 위에 있는 탑이 현재 탑보다 높으면, 스택에 있는 탑의 레이저가 현재 탑에 도달하지 못하므로 종료
                if(arr[stack.peek()] > arr[idx]) break;
                // 2. 스택의 가장 위에 있는 탑의 레이저가 가장 먼저 도달하는 탑 = 현재 탑
                answer[stack.pop()] = idx + 1;
            }
            // 3. 현재 탑을 스택에 넣기
            stack.push(idx--);
//            System.out.println(stack);
        }
        StringBuilder sb = new StringBuilder();
        for(int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
}