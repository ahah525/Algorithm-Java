package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] [카카오 인턴] 수식 최대화
 * [풀이시간] 1시간 45분(1시간 15분 + 30분) / 1시간 5분
 * [한줄평] 순열로 각 경우의 수에 따라 연산 결과를 모두 구하고 최댓값을 구해야겠다고 생각은 했는데 구현하는게 생각보다 조금 복잡했던 것 같다.
 * 로직은 맞는 것 같은데 계속 실패가 떠서 결국 반례를 봤는데 조건을 생각안하고 자료형을 잘못사용해서 통과하지 못했다는게 정말 허무했다. 다시 한번 조건을 잘 읽자..
 * 1_v1. 순열로 완전탐색 + 구현(실패 - 정확성 테스트: 11~15, 24, 27~29 실패)
 * 1_v2. 순열로 완전탐색 + 구현(성공)
 * - "expression의 중간 계산값과 최종 결괏값은 절댓값이 2^63 - 1 이하" 조건 -> int 대신 long 으로 변경
 * 2_v1. 완전탐색(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/62757">문제</a>
 */
class Solution67257 {
    public static void main(String[] args) {
        // 60420
        System.out.println(solution2("100-200*300-500+20"));

        // 300
        System.out.println(solution2("50*6-3*2"));

        // 52
        System.out.println(solution2("50+2"));
    }

    // 1_v1
    static List<Long> nums;  // 피연산자 리스트
    static String cmds;         // 연산자 리스트
    static long max;             // 연산 결과값(절댓값)의 최댓값

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
                nums.add(Long.parseLong(sb.toString()));
                sb = new StringBuilder();
                // 연산자는 문자열 뒤에 추가
                cmds += c;
            }
        }
        // StringBuilder 에 들어있는 마지막 숫자도 추가하기
        nums.add(Long.parseLong(sb.toString()));
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
            // 1. 피연산자 리스트, 연산자 문자열 생성자로 복사
            List<Long> ns = new ArrayList<>(nums);
            StringBuilder sb = new StringBuilder(cmds);

            // 2. 연산자 우선순위에 의한 연산 결과 구하기
            long res = 0;
            while(sb.length() != 0) {
                // 2-1. 가장 우선순위가 높은 연산자와 해당 인덱스 구하기
                int find = findIdx(path, sb.toString());
                char cmd = sb.charAt(find);
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
                // 2-3. 피연산자 리스트에서 해당 인덱스 값을 연산 결과값으로 바꾸고 그 다음값은 제거하기
                ns.set(find, res);
                ns.remove(find + 1);
                // 2-4. 해당 연산자를 연산자 문자열에서 삭제하기
                sb.deleteCharAt(find);
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

    // 2_v1
    public static long solution2(String expression) {
        long answer = 0;
        // 1. 연산자, 피연산자 분리
        List<Long> nums = new ArrayList<>();
        List<Character> cmds = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(char c : expression.toCharArray()) {
            if('0' <= c && c <= '9') {
                sb.append(c);
            } else {
                nums.add(Long.parseLong(sb.toString()));
                sb.setLength(0);
                cmds.add(c);
            }
        }
        nums.add(Long.parseLong(sb.toString()));    // 마지막 숫자 처리
        // 2. 연산자 우선순위 경우의 수 = 6개
        String[] paths = {"+-*", "+*-", "-+*", "-*+", "*+-", "*-+"};
        // 3. 연산자 우선순위를 적용한 계산 결괏값을 비교해 최댓값 갱신
        for(String path : paths) {
            answer = Math.max(answer, calc(path, nums, cmds));
        }
        return answer;
    }

    // 연산자 우선순위를 적용한 계산 결과의 절댓값 리턴
    public static long calc(String path, List<Long> num, List<Character> cmd) {
        // 1. 리스트 값 복사(원본을 유지하기 위함)
        List<Long> nums = new ArrayList<>();
        List<Character> cmds = new ArrayList<>();
        for(long n : num) {
            nums.add(n);
        }
        for(char c : cmd) {
            cmds.add(c);
        }
        // 2. 각 연산자 우선순위대로 계산
        for(char c : path.toCharArray()) {
            int i = 0;
            while(i < cmds.size()) {
                if(cmds.get(i) != c) {
                    i++;
                    continue;
                }
                long res;
                if(c == '+') {
                    res = nums.get(i) + nums.get(i + 1);
                } else if(c == '-') {
                    res = nums.get(i) - nums.get(i + 1);
                } else {
                    res = nums.get(i) * nums.get(i + 1);
                }
                nums.remove(i);
                nums.remove(i);
                nums.add(i, res);
                cmds.remove(i);
            }
        }
        // 3. 절댓값 리턴
        return Math.abs(nums.get(0));
    }
}