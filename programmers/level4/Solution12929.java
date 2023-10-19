package programmers.level4;


import java.util.Stack;

/**
 * [문제명] 올바른 괄호의 갯수
 * [풀이시간] 27분
 * [한줄평] DFS로 풀면 시간초과가 나지않을까 했는데 통과해서 놀랐다.
 * 1_v1. DFS, Stack(성공)
 * [풀이] DFS로 2*n개의 올바른 괄호를 선택했을 때 개수를 카운팅한다.
 * 1. 스택이 비었으면, 무조건 열린 괄호를 선택해야한다.
 * 2. 그렇지 않으면, 열린 괄호를 선택하거나 닫힌 괄호를 선택한다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12929">문제</a>
 */
class Solution12929 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    int answer;
    public int solution(int n) {
        answer = 0;
        dfs(0, 0, new Stack<>(), n);
        return answer;
    }

    /**
     * n개의 괄호쌍으로 만들 수 있는 올바른 괄호 문자열 개수 구하기
     * @param depth 선택한 괄호의 총 개수
     * @param cnt 선택한 열린 괄호의 개수
     * @param stack
     */
    public void dfs(int depth, int cnt, Stack<String> stack, int n) {
        if(depth == 2 * n) {
            answer++;
            return;
        }
        if(stack.isEmpty()) {
            // 1. 스택이 비었으면
            // 열린 괄호 넣기
            stack.push("(");
            dfs(depth + 1, cnt + 1, stack, n);
            stack.pop();
        } else {
            // 2. 스택이 비지 않았으면
            // n개의 열린 괄호를 다 선택하지 않았으면, 열린 괄호 넣기
            if(cnt < n) {
                stack.push("(");
                dfs(depth + 1, cnt + 1, stack, n);
                stack.pop();
            }
            // 열린 괄호 꺼내기(=닫힌 괄호 선택)
            stack.pop();
            dfs(depth + 1, cnt, stack, n);
            stack.push("(");
        }
    }
}