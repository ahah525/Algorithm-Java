package baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * [문제명] 괄호의 값
 * [풀이시간] / 37분
 * [한줄평]
 * / stack으로 푸는 괄호 문제를 응용한 것으로 다시 한 번 꼭 풀어봐야겠다.
 * 1_v1. Stack(성공)
 * 2_v1. Stack(성공)
 * @See <a href="https://www.acmicpc.net/problem/2504">문제</a>
 */
public class Boj2504 {
    // 1_v1
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        Stack<Character> stack = new Stack<>();
//        int res = 0;    // 최종 계산 결과(덧셈)
//        int cnt = 1;    // 중간 계산 결과(곱셈)
//        boolean flag = true;    // 올바른 괄호열 여부
//
//        String s = br.readLine();   // 괄호열을 나타내는 문자열
//        for(int i = 0; i < s.length(); i++) {
//            // 올바르지 못한 괄호열이면 바로 탈출
//            if(!flag) break;
//            switch(s.charAt(i)) {
//                case '(':
//                    stack.push('(');
//                    cnt *= 2;
//                    break;
//                case '[':
//                    stack.push('[');
//                    cnt *= 3;
//                    break;
//                case ')':
//                    if(stack.isEmpty() || stack.peek() != '(') {
//                        // 스택이 비었거나 top이 '('가 아니면 flag값 변경 후 탈출
//                        flag = false;
//                        break;
//                    }
//                    // 이전 괄호가 '('이면 중간 계산 결과를 더해주기
//                    if(s.charAt(i - 1) == '(') res += cnt;
//                    stack.pop();
//                    cnt /= 2;
//                    break;
//                case ']':
//                    if(stack.isEmpty() || stack.peek() != '[') {
//                        // 스택이 비었거나 top이 '['가 아니면 flag값 변경 후 탈출
//                        flag = false;
//                        break;
//                    }
//                    // 이전 괄호가 '['이면 중간 계산 결과를 더해주기
//                    if(s.charAt(i - 1) == '[') res += cnt;
//                    stack.pop();
//                    cnt /= 3;
//                    break;
//            }
//        }
//        // 올바른 괄호열이고 스택이 비어있어야 올바른 괄호열임
//        if(flag && stack.isEmpty()) {
//            System.out.println(res);
//        } else {
//            System.out.println(0);
//        }
//    }

    // 2_v1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        System.out.println(calc(arr));
    }

    public static int calc(char[] arr) {
        Stack<Integer> stack = new Stack<>();
        for(char c : arr) {
            // 1. 열린 괄호인 경우, stack에 '( '-> 0, '[' -> 1 치환해서 넣기
            if(c == '(') stack.push(0);
            else if(c == '[') stack.push(1);
            // 2. 닫힌 괄호인 경우
            else if(c == ')') {
                // 3. 열린 괄호가 아닌 값들을 모두 더한 중간 결과값 계산
                int x = 0;
                while (!stack.isEmpty() && stack.peek() > 1) {
                    x += stack.pop();
                }
                // 4. 스택이 비었거나 닫힌 괄호의 짝이 아닌 경우, 0 리턴
                if(stack.isEmpty() || stack.pop() != 0) return 0;
                // 5. 중간 결과값이 0이면, ()인 경우이므로 stack에 2 넣기
                if(x == 0) stack.push(2);
                // 6. 중간 결과값이 0이 아니면, (x)인 경우이므로 stack에 2*x 넣기
                else stack.push(2 * x);
            } else if(c == ']') {
                // 3. 열린 괄호가 아닌 값들을 모두 더한 중간 결과값 계산
                int x = 0;
                while (!stack.isEmpty() && stack.peek() > 1) {
                    x += stack.pop();
                }
                // 4. 스택이 비었거나 닫힌 괄호의 짝이 아닌 경우, 0 리턴
                if(stack.isEmpty() || stack.pop() != 1) return 0;
                // 5. 중간 결과값이 0이면, []인 경우이므로 stack에 3 넣기
                if(x == 0) stack.push(3);
                // 6. 중간 결과값이 0이 아니면, [x]인 경우이므로 stack에 3*x 넣기
                else stack.push(3 * x);
            }
//            System.out.println(stack);
        }
        // 7. XY의 형태의 괄호 결과값 계산하기
        int res = 0;
        while(!stack.isEmpty()) {
            int x = stack.pop();
            // 8. 열린 괄호면 리턴
            if(x == 0 || x == 1) return 0;
            res += x;
        }
        return res;
    }
}
