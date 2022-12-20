package programmers.level2;


import java.util.Stack;

/**
 * [문제명] 괄호변환
 * [풀이시간] 1시간
 * [한줄평] 문제에 나온대로 구현하기만 하면 되는 문제로 굳이 따지자면 분할정복, 구현에 속하는 문제인 것 같다. 다만 조건들을 처리하고 재귀로 구현하는게 조금 익숙치 않아서 생각보다 오래걸렸던 문제였다.
 * v1. stack, 재귀(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/60058">문제</a>
 */
class Solution60058 {
    public static void main(String[] args) {
        // "(()())()"
        String p1 = "(()())()";
        System.out.println(solution(p1));

        // "()"
        String p2 = ")(";
        System.out.println(solution(p2));

        // "()(())()"
        String p3 = "()))((()";
        System.out.println(solution(p3));
    }

    /**
     * @param p "균형잡힌 괄호 문자열"
     * @return 주어진 알고리즘을 수행해 "올바른 괄호 문자열"로 변환한 결과
     */
    public static String solution(String p) {
        // 이미 올바른 문자열이면 바로 리턴
        if(isValid(p)) return p;
        return divide(p);
    }

    // 주어진 균형잡힌 괄호 문자열 -> 올바른 괄호 문자열로 변환한 결과
    public static String divide(String p) {
        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        if(p.length() == 0) return "";
        // 2. 문자열 p를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
        int a = 0;  // '(' 개수
        int b = 0;  // ')' 개수
        // '(' 와 ')' 의 개수가 같아지는 최초 인덱스 구하기
        int splitIdx = 0;
        for(int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '(') {
                a++;
            } else {
                b++;
            }
            if(a == b) {
                splitIdx = i;
                break;
            }
        }
        String u = p.substring(0, splitIdx + 1);
        String v = p.substring(splitIdx + 1);
        if(isValid(u)) {
            // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
            return u + divide(v);
        } else {
            // 4. u가 올바른 괄호 문자열이 아니면, 아래 과정 수행
            // 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
            // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
            // 4-3. ')'를 다시 붙입니다.
            StringBuilder sb = new StringBuilder();
            sb.append('(').append(divide(v)).append(')');
            // 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
            String uu = u.substring(1, u.length() - 1);
            // 빈문자열이 아닐때만 괄호 방향 뒤집기
            if(uu.length() != 0) {
                for(char c : uu.toCharArray()) {
                    if(c == '(') {
                        sb.append(')');
                    } else {
                        sb.append('(');
                    }
                }
            }
            // 4-5. 생성된 문자열을 반환합니다.
            return sb.toString();
        }
    }

    // 올바른 괄호 문자열인지 여부
    public static boolean isValid(String s) {
        if(s.length() == 0) return true;
        Stack stack = new Stack();
        for(char c : s.toCharArray()) {
            if(c == '(') {
                stack.push(c);
            } else {
                if(stack.empty()) return false;
                stack.pop();
            }
        }
        return stack.empty() ? true : false;
    }
}