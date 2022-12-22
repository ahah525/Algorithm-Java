package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] [카카오 인턴] 수식 최대화
 * [풀이시간] (1시간 15분 + )
 * [한줄평]
 * v1. (실패 - 정확성 테스트: 11~15, 24, 27~29 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/62757">문제</a>
 */
class Solution62757 {
    public static void main(String[] args) {
        // 60420
        String expression1 = "100-200*300-500+20";
        System.out.println(solution(expression1));

        // 300
        String expression2 = "50*6-3*2";
        System.out.println(solution(expression2));

        // 52
        String expression3 = "50+2";
        System.out.println(solution(expression3));
    }

    static List<Integer> nums;  // 피연산자 리스트
    static String cmds;         // 연산자 리스트
    static int max;             // 연산 결과값(절댓값)의 최댓값

    /**
     * @param expression 공백문자, 괄호문자 없이 오로지 숫자와 3가지의 연산자(+, -, *) 만으로 이루어진 올바른 중위표기법(연산의 두 대상 사이에 연산기호를 사용하는 방식)으로 표현된 연산식
     * @return 가능한 연산자 우선순위 조합으로 계산한 수식의 결과값(절댓값)의 최댓값
     */
    public static long solution(String expression) {
        max = 0;
        // 1. 연산자 종류와 개수 구하기
        char[] cmd = {'+', '-', '*'};
        List<Character> list = new ArrayList<>();
        for(char c : cmd) {
            if(expression.contains(c + ""))
                list.add(c);
        }
        int n = list.size();        // 연산자 종류수
        // 2. 피연산자와 연산자를 분리하여 각각 리스트, 문자열로 저장
        nums = new ArrayList<>();   // 피연산자
        cmds = "";                  // 연산자
        StringBuilder sb = new StringBuilder();
        for(char c : expression.toCharArray()) {
            if(Character.isDigit(c)) {
                sb.append(c);
            } else {
                // StringBuilder 담긴 숫자를 정수로 변한하여 리스트에 추가
                nums.add(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
                // 연산자는 문자열 뒤에 추가
                cmds += c;
            }
        }
        // StringBuilder 에 들어있는 마지막 숫자도 추가하기
        nums.add(Integer.parseInt(sb.toString()));
        // 3.
        char[] path = new char[n];          // 연산자 우선순위(앞에 있을 수록 우선순위가 크다)
        boolean[] visited = new boolean[n]; // 방문여부
        dfs(0, list, visited, path, n);
        return max;
    }

    /**
     * @param depth 깊이
     * @param list 연산자 종류
     * @param visited 방문여부
     * @param path 연산자 우선순위(순열 결과)
     * @param n 연산자 종류수
     */
    public static void dfs(int depth, List<Character> list, boolean[] visited, char[] path, int n) {
        if(depth == n) {
            System.out.print("*");
            // 1. 피연산자 리스트, 연산자 문자열 deep copy -> 원본 값을 유지해야하기 때문에
            List<Integer> ns = new ArrayList<>();
            ns.addAll(nums);
            String cs = new String(cmds);
//            System.out.print("path =");
//            for(char c : path) {
//                System.out.print(c);
//            }
//            System.out.println();

            // 2. 연산자 우선순위에 의한 연산 결과 구하기
            int res = 0;
            while(cs.length() != 0) {
                // 2-1. 가장 우선순위가 높은 연산자와 해당 인덱스 구하기
                int find = findIdx(path, cs);
                char cmd = cs.charAt(find);
                // 2-2. 연산 결과 구하기
                switch (cmd) {
                    case '+':
                        res = ns.get(find) + ns.get(find + 1);
                        break;
                    case '-':
                        res = ns.get(find) - ns.get(find + 1);
                        break;
                    case '*':
                        res = ns.get(find) * ns.get(find + 1);
                        break;
                }
//                System.out.println("ns = " + ns);
//                System.out.println("cs = " + cs);
//                System.out.println("res = " + res);
                // 2-3. 피연산자 리스트에서 해당 인덱스 값을 연산 결과값으로 바꾸고 그 다음값은 제거하기
                ns.set(find, res);
                ns.remove(find + 1);
                // 2-4. 해당 연산자를 연산자 문자열에서 삭제하기
                StringBuilder sb = new StringBuilder(cs);
                sb.deleteCharAt(find);
                cs = sb.toString();
            }
            // 3. 최댓값 업데이트
            max = Math.max(max, Math.abs(res));
            return;
        }

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                path[depth] = list.get(i);
                visited[i] = true;
                dfs(depth + 1, list, visited, path, n);
                visited[i] = false;
            }
        }
    }

    /**
     * @param path 연산자 우선수위
     * @param s 연산자 문자열
     * @return 연산자 문자열에서 우선순위가 높은 연산자부터 탐색하여 제일 먼저 발견된 연산자의 인덱스를 리턴
     */
    public static int findIdx(char[] path, String s) {
        for(char c : path) {
            int idx = s.indexOf(c);
            if(idx != -1) {
                return idx;
            }
        }
        return 0;
    }
}