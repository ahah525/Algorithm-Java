package programmers.level2;


import java.util.Stack;

/**
 * [문제명] 짝지어 제거하기
 * [풀이시간] 45분(15분 + 30분)
 * [한줄평] 이 문제는 StringBuilder 로 delete 해가면서 푸는 게 아니라, 스택을 이용해야만 시간초과를 해결할 수 있는 문제로 시간복잡도 계산이 필요하다!!
 * v1. StringBuilder(실패 - 효율성 테스트 모두 시간초과)
 * - 시간초과가 날 것을 고려해 StringBuilder 를 써서 포인터를 옮겨가는 방식으로 풀었는데 시간초과가 났다.
 * - 제한시간 1초일 때, 약 1억개 연산이 가능하다. 즉, N이 1억(10^8) 이상이면 시간초과가 난다.
 * - while 문 = O(N), StringBuilder 의 delete() = O(N) -> 대략 O(N^2) 인데, 문자열의 길이가 최대 10^6 이기때문에 O(10^12) 으로는 시간초과가 난다.
 * v2. Stack(성공)
 * -
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12973">문제</a>
 */
class Solution12973 {
    public static void main(String[] args) {
        // 1
        System.out.println(solution2("baabaa"));

        // 0
        System.out.println(solution2("cdcd"));
    }

    // 실패
    public static int solution1(String s) {
        int i = 0;  // 포인터
        StringBuilder sb = new StringBuilder(s);
        while(true) {
            // 1. 문자열의 길이가 0이면 모두 제거된 것이므로 1 리턴
            if(sb.length() == 0) {
                return 1;
            }
            // 2. 문자열의 길이가 0이 아닌데, 포인터가 마지막에 도달했으면 모두 제거할 수 없으므로 0 리턴
            if(i == sb.length() - 1) {
                return 0;
            }
            if(sb.charAt(i) == sb.charAt(i + 1)) {
                // 3. 같으면 삭제, 포인터 왼쪽 이동
                sb.delete(i, i + 2);
                i--;
                if(i < 0) i = 0;    // 포인터 음수값에 대한 처리
            } else {
                // 4. 다르면 포인터 오른쪽 이동
                i++;
            }
        }
    }

    /**
     * @param s 알파벳 소문자로 이루어진 문자열(길이는 1,000,000이하)
     * @return 짝지어 제거하기를 성공적으로 수행할 수 있는지(성공적으로 수행할 수 있으면 1을, 아닐 경우 0)
     */
    public static int solution2(String s) {
        Stack<Character> stack = new Stack();
        for(char c : s.toCharArray()) {
            if(stack.isEmpty()) {
                // 1. 스택이 비었으면 넣기
                stack.push(c);
            } else {
                if(c == stack.peek()) {
                    // 2. 맨위에 값이랑 같으면 pop
                    stack.pop();
                } else {
                    // 3. 맨위에 값이랑 다르면 넣기
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }
}