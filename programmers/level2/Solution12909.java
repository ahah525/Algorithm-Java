package programmers.level2;


import java.util.Stack;

/**
 * [문제명] 올바른 괄호
 * [풀이시간] 7분
 * [한줄평] 전형적인 스택으로 풀 수 있는 괄호문제였다.
 * v1. Stack(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12909">문제</a>
 */
class Solution12909 {
    public static void main(String[] args) {
        // true
        String s1 = "()()";
        System.out.println(solution(s1));

        // true
        String s2 = "(())()";
        System.out.println(solution(s2));

        // false
        String s3 = ")()(";
        System.out.println(solution(s3));

        // false
        String s4 = "(()(";
        System.out.println(solution(s4));
    }

    /**
     * @param s '(' 또는 ')' 로만 이루어진 문자열
     * @return 문자열 s가 올바른 괄호이면 true를 return 하고, 올바르지 않은 괄호이면 false를 return
     */
    public static boolean solution(String s) {
        Stack stack = new Stack();
        for(char c : s.toCharArray()) {
            if(c == '(') {
                // 1. 열린 괄호는 push
                stack.push(c);
            } else {
                // ')' 가 없는 경우(올바르지 못한 경우1)
                if(stack.empty()) return false;
                // 2. 닫힌 괄호는 pop
                stack.pop();
            }
        }
        // '(' 가 더 있는 경우(올바르지 못한 경우2)
        return stack.empty() ? true : false;
    }
}