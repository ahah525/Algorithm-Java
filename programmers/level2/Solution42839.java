package programmers.level2;


import java.util.HashSet;
import java.util.Set;

/**
 * [문제명] 소수 찾기
 * [한줄평] 순열 문제로 전형적인 DFS 문제였기때문에 쉽게 풀 수 있었다. 문자열을 자르고 붙이는데 생기는 부하를 생각해 StringBuilder 를 이용하였는데,
 * 일반적으로 사용하는 char[] 를 사용했을 때와 효율을 비교해볼 필요가 있다.
 * v1.DFS(v1.성공)
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42839">문제</a>
 */
class Solution42839 {
    private static String s;
    private static int n;
    private static boolean[] visited;
    private static StringBuilder path = new StringBuilder();
//    private static char[] path;
    private static Set set;


    public static void main(String[] args) {
        // 3
        String numbers1 = "17";
        System.out.println(solution(numbers1));

        // 2
        String numbers2 = "011";
        System.out.println(solution(numbers2));
    }

    /**
     * @param numbers 숫자가 적힌 문자열
     * @return 종이 조각으로 만들 수 있는 소수가 몇 개인지 return
     */
    public static int solution(String numbers) {
        s = numbers;
        n = numbers.length();
        visited = new boolean[n];
        set = new HashSet();

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
            if(n % i == 0)
                return false;
        }
        return true;
    }
}