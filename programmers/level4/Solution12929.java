package programmers.level4;


/**
 * [문제명] 올바른 괄호의 갯수
 * [풀이시간] 27분,5분 / 8분
 * [한줄평] DFS로 풀면 시간초과가 나지않을까 했는데 통과해서 놀랐다. 하지만 스택을 사용하지 않아도 풀 수 있다는 풀이가 더 좋은 방식이다.
 * / 저번에 풀었던 기억이 있어서 비교적 쉽게 풀었다. 올바른 괄호 조건을 만족하는 경우로만 열린괄호나 닫힌 괄호를 선택하는 것을 떠올리면 스택을 사용하지 않아도 됐다.
 * 1_v1. DFS, Stack(성공)
 * [풀이] DFS로 2*n개의 올바른 괄호를 선택했을 때 개수를 카운팅한다.
 * 1. 스택이 비었으면, 무조건 열린 괄호를 선택해야한다.
 * 2. 그렇지 않으면, 열린 괄호를 선택하거나 닫힌 괄호를 선택한다.
 * 1_v2. DFS(성공) -> 추천
 * [풀이] n개의 열린 괄호를 선택하지 않았으면 열린 괄호를 선택한다. 선택한 열린 괄호 개수가 닫힌 괄호 개수보다 클 때만 닫힌 괄호를 선택한다.
 * 2_v1. DFS, 백트래킹(성공)
 * [풀이] 1_v2 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12929">문제</a>
 */
class Solution12929 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
//    int answer;
//    public int solution(int n) {
//        answer = 0;
//        dfs(0, 0, new Stack<>(), n);
//        return answer;
//    }
//
//    /**
//     * n개의 괄호쌍으로 만들 수 있는 올바른 괄호 문자열 개수 구하기
//     * @param depth 선택한 괄호의 총 개수
//     * @param cnt 선택한 열린 괄호의 개수
//     * @param stack
//     */
//    public void dfs(int depth, int cnt, Stack<String> stack, int n) {
//        if(depth == 2 * n) {
//            answer++;
//            return;
//        }
//        if(stack.isEmpty()) {
//            // 1. 스택이 비었으면
//            // 열린 괄호 넣기
//            stack.push("(");
//            dfs(depth + 1, cnt + 1, stack, n);
//            stack.pop();
//        } else {
//            // 2. 스택이 비지 않았으면
//            // n개의 열린 괄호를 다 선택하지 않았으면, 열린 괄호 넣기
//            if(cnt < n) {
//                stack.push("(");
//                dfs(depth + 1, cnt + 1, stack, n);
//                stack.pop();
//            }
//            // 열린 괄호 꺼내기(=닫힌 괄호 선택)
//            stack.pop();
//            dfs(depth + 1, cnt, stack, n);
//            stack.push("(");
//        }
//    }

    // 1_v2, 2_v1
    int answer;
    public int solution2(int n) {
        answer = 0;
        dfs2(0, 0, n);
        return answer;
    }

    public void dfs2(int open, int close, int n) {
        // 1. 닫힌 괄호를 n개 선택했으면, 올바른 괄호 문자열이므로 카운팅
        if(close == n) {
            answer++;
            return;
        }
        // 열린 괄호 개수 < 선택할 수 있는 열린 괄호 개수, 열린 괄호 선택
        if(open < n) {
            dfs2(open + 1, close, n);
        }
        // 열린 괄호 개수 > 닫힌 괄호 개수, 닫힌 괄호 선택
        if(open > close) {
            dfs2(open, close + 1, n);
        }
    }
}