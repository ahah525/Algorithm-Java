package programmers.level2;


import java.util.HashSet;
import java.util.Set;

/**
 * [문제명] 소수 찾기
 * [풀이시간] / 16분
 * [한줄평] 순열 문제로 전형적인 DFS 문제였기때문에 쉽게 풀 수 있었다. / DFS 로 풀 수 있는 완전탐색 문제였다.
 * 1_v1. DFS(성공) -> 더 빠름
 * - 문자열을 자르고 붙이는데 생기는 부하를 고려해 StringBuilder 사용
 * 2_v1. 완전탐색, DFS(성공)
 * - String 사용
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42839">문제</a>
 */
class Solution42839 {
    public static void main(String[] args) {
        // 3
        System.out.println(solution("17"));

        // 2
        System.out.println(solution("011"));
    }

    // 1_v1
    private static String s;
    private static int n;
    private static boolean[] visited;
    private static StringBuilder path = new StringBuilder();
    private static Set<Integer> set;

    /**
     * @param numbers 숫자가 적힌 문자열
     * @return 종이 조각으로 만들 수 있는 소수가 몇 개인지 return
     */
    public static int solution(String numbers) {
        s = numbers;
        n = numbers.length();
        visited = new boolean[n];
        set = new HashSet<>();

        dfs(-1);

        return set.size();
    }

    // 순열
    public static void dfs(int depth) {
        // 종료조건
        if(depth == n) {
            return;
        }
        // 소수 판별
        if(path.length() != 0) {
            int n = Integer.valueOf(path.toString());
            if(isPrime(n)) {
                set.add(n);
            }
        }
        for(int i = 0; i < n; i++) {
            // 방문하지 않았으면
            if(!visited[i]) {
                // 방문 처리(맨 끝에 추가)
                path.append(s.charAt(i));
                visited[i] = true;
                dfs(depth + 1);
                // 방문 취소(맨 끝 문자 제거)
                path.deleteCharAt(depth + 1);
                visited[i] = false;
            }
        }
    }

    // 소수인지 판별
    public static boolean isPrime(int n) {
        if(n == 0 || n == 1) return false;
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return true;
    }

    // 1_v2
    public static int solution2(String numbers) {
        set = new HashSet<>();

        dfs(0, new boolean[numbers.length()], "", numbers.toCharArray());

        return set.size();
    }

    public static void dfs(int depth, boolean[] visited, String s, char[] arr) {
        // 0번째 고를 차례가 아닐 때만(s 가 ""이 아닐때만)
        if(depth != 0) {
            int n = Integer.parseInt(s);
            if(isPrime(n))
                set.add(n);
        }
        // 문자열의 길이만큼 모두 골랐으면 리턴
        if(depth == arr.length) return;
        // 순열
        for(int i = 0; i < arr.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, visited, s + arr[i], arr);
                visited[i] = false;
            }
        }
    }
}