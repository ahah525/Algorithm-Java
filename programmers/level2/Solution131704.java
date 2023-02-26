package programmers.level2;


import java.util.Stack;

/**
 * [문제명] 택배상자
 * [풀이시간] (25분)
 * [한줄평]
 * 1_v1. (실패 - 6, 7, 9, 10 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/">문제</a>
 */
class Solution131704 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[order.length + 1];
        for(int n : order) {
            for(int i = 1; i <= n; i++) {
                if(!visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
            if(stack.peek() == n) {
                stack.pop();
                answer++;
            } else {
                break;
            }
        }
        return answer;
    }
}