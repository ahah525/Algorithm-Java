package baekjoon;

import java.io.*;
import java.util.Stack;

public class Boj1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());    // 수열을 이루는 정수
        int cnt = 0;    // 가장 최근 push 값
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());    // 현재 pop해야할 값
            if(x > cnt) {
                // 1. pop 해야할 값 > 가장 최근 push 값
                // 가장 최근에 push했던 값의 다음 값부터 x까지 push 후 pop
                for(int j = cnt + 1; j <= x; j++) {
                    stack.push(j);
                    sb.append("+").append("\n");
                }
                cnt = x;
            } else {
                // 2. pop 해야할 값 < 가장 최근 push 값
                if(stack.peek() != x) {
                    // 스택의 top이 pop해야할 값이 아니면 종료
                    System.out.println("NO");
                    return;
                }
            }
            stack.pop();
            sb.append("-").append("\n");
        }
        System.out.println(sb);
    }
}
