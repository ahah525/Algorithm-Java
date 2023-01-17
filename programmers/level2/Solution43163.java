package programmers.level2;


/**
 * [문제명] 단어 변환
 * [풀이시간] 30분
 * [한줄평] DFS로 풀 수 있는 간단한 문제였다.
 * v1. DFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43163">문제</a>
 */
class Solution43163 {
    public static void main(String[] args) {
        // 4
        String[] words1 = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(solution("hit", "cog", words1));

        // 0
        String[] words2 = {"hot", "dot", "dog", "lot", "log"};
        System.out.println(solution("hit", "cog", words2));
    }

    static int min; // 최소 과정수

    /**
     * @param begin 시작 단어
     * @param target 목표 단어
     * @param words 단어의 집합
     * @return 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지(변환할 수 없는 경우에는 0)
     */
    public static int solution(String begin, String target, String[] words) {
        int n = words.length;
        min = n + 1;    //
        permu(0, begin, new boolean[n], n, target, words);
        if(min == n + 1)
            return 0;
        return min;
    }

    /**
     * 순열
     * @param depth 지금까지 선택한 단어개수
     * @param prev 직전에 선택한 단어
     * @param visited 단어 선택 여부
     * @param n 단어 개수
     * @param target 목표 단어
     * @param words 단어의 집합
     */
    public static void permu(int depth, String prev, boolean[] visited, int n, String target, String[] words) {
        // n개를 모두 선택헀으면 종료
        if(depth == n) {
            return;
        }
        // 이전 단어가 목표 단어와 같다면 최솟값 업데이트
        if(prev.equals(target)) {
            min = Math.min(min, depth);
            return;
        }
        // 아직 선택하지 않고 변환될 수 있는 단어에 대해 재귀
        for(int i = 0; i < n; i++) {
            if(!visited[i] && canChange(prev, words[i])) {
                visited[i] = true;
                permu(depth + 1, words[i], visited, n, target, words);
                visited[i] = false;
            }
        }
    }

    // s1 -> s2 로 변환가능한지 체크
    public static boolean canChange(String s1, String s2) {
        if(s1.equals(s2)) return false;
        int diff = 0;   // 다른 문자의 개수
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
            // 1. 다른 문자가 2개 이상이면 변환X
            if(diff >= 2)
                return false;
        }
        // 2. 같은 문자로는 변환X
        return diff == 0  ? false : true;
    }
}