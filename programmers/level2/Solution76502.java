package programmers.level2;


import java.util.Stack;

/**
 * [문제명] 괄호 회전하기
 * [풀이시간] 18분
 * [한줄평] 괄호 문제는 전형적인 stack 으로 풀 수 있는 쉬운 문제였다. 다만 substring 으로 문자를 매번 새로 생성하지 않고 배열 인덱스를 조정해서 풀면 더 효율을 높일 수 있을 것 같다.
 * 1_v1. Stack(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/76502">문제</a>
 */
class Solution76502 {
    public static void main(String[] args) {
        // 3
        System.out.println(solution("[](){}"	));
    }

    /**
     * @param s 대괄호, 중괄호, 그리고 소괄호로 이루어진 문자열
     * @return s를 왼쪽으로 x (0 ≤ x < (s의 길이)) 칸만큼 회전시켰을 때 s가 올바른 괄호 문자열이 되게 하는 x의 개수
     */
    public static int solution(String s) {
        int answer = 0;
        for(int i = 0; i < s.length(); i++) {
            if(isCorrect(s.substring(i) + s.substring(0, i)))
                answer++;
        }
        return answer;
    }

    // s 가 올바른 문자열인지 판별
    public static boolean isCorrect(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c == '[' || c == '(' || c == '{') {
                stack.push(c);
            } else {
                if(stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();
                boolean flag = false;
                if((c == ']' && pop == '[') || (c == ')' && pop == '(') || (c == '}' && pop == '{'))
                    flag = true;
                if(!flag) return false;
            }
        }
        return stack.isEmpty() ? true : false;
    }
}