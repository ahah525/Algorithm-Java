package baekjoon;

import java.io.*;
import java.util.Stack;

public class Boj2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();
        int res = 0;    // 최종 계산 결과(덧셈)
        int cnt = 1;    // 중간 계산 결과(곱셈)
        boolean flag = true;    // 올바른 괄호열 여부

        String s = br.readLine();   // 괄호열을 나타내는 문자열
        for(int i = 0; i < s.length(); i++) {
            // 올바르지 못한 괄호열이면 바로 탈출
            if(!flag) break;
            switch(s.charAt(i)) {
                case '(':
                    stack.push('(');
                    cnt *= 2;
                    break;
                case '[':
                    stack.push('[');
                    cnt *= 3;
                    break;
                case ')':
                    if(stack.isEmpty() || stack.peek() != '(') {
                        // 스택이 비었거나 top이 '('가 아니면 flag값 변경 후 탈출
                        flag = false;
                        break;
                    }
                    // 이전 괄호가 '('이면 중간 계산 결과를 더해주기
                    if(s.charAt(i - 1) == '(') res += cnt;
                    stack.pop();
                    cnt /= 2;
                    break;
                case ']':
                    if(stack.isEmpty() || stack.peek() != '[') {
                        // 스택이 비었거나 top이 '['가 아니면 flag값 변경 후 탈출
                        flag = false;
                        break;
                    }
                    // 이전 괄호가 '['이면 중간 계산 결과를 더해주기
                    if(s.charAt(i - 1) == '[') res += cnt;
                    stack.pop();
                    cnt /= 3;
                    break;
            }
        }
        // 올바른 괄호열이고 스택이 비어있어야 올바른 괄호열임
        if(flag && stack.isEmpty()) {
            System.out.println(res);
        } else {
            System.out.println(0);
        }
    }
}
