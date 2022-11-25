package programmers.level2;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 모음사전
 * [한줄평] DFS 로 풀어야겠다고 빨리 떠올려서 쉽게 풀 수 있었던 문제였다. 수학적으로 규칙을 찾아서 풀 수 있다는 것을 다른 풀이를 보고 알게되었다.
 * v1. DFS(성공)
 * >> answer 을 0이 아닌 -1 로 초기화한 이유
 * - 최초에 dfs 가 호출될 때 즉, depth = 0 일 때 answer 값이 0이어야 하기 때문이다.
 * - 만약 0으로 초기화했다면 dfs(0, ,) 이 호출됬을 때 이미 answer 값이 1으로 시작하기 때문에 실제 값보다 1 크게 나오는 문제가 생긴다.
 * v2. 수학적 접근(더 빠른 방법)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/84512/solution_groups?language=java">다른 풀이</a>
 */
class Solution84512 {
    private static boolean isFind = false;
    private static int answer = -1;
    private static char[] alpha = {'A', 'E', 'I', 'O', 'U'};

    public static void main(String[] args) {
        // 6
        String word1 = "AAAAE";
        int answer1 = solution1(word1);
        System.out.println(answer1);

        // 10
        reset();
        String word2 = "AAAE";
        int answer2 = solution1(word2);
        System.out.println(answer2);

        // 1563
        reset();
        String word3 = "I";
        int answer3 = solution1(word3);
        System.out.println(answer3);

        // 1189
        reset();
        String word4 = "EIO";
        int answer4 = solution1(word4);
        System.out.println(answer4);
    }

    /**
     *
     * @param word
     * - 길이는 1 이상 5 이하
     * - 알파벳 대문자 'A', 'E', 'I', 'O', 'U' 로만 이루어짐
     * @return 단어 하나 word가 매개변수로 주어질 때, 이 단어가 사전에서 몇 번째 단어인지 return
     */
    public static int solution1(String word) {
        dfs(0, "", word);
        return answer;
    }

    public static void dfs(int depth, String word, String target) {
        if(depth == 6) return;
        if(isFind) return;
        answer++;
        if(word.equals(target)) {
            isFind = true;
            return;
        }
        // A, E, I, O, U
        for(char c : alpha) {
            dfs(depth + 1, word + c, target);
        }
    }

    // 출력 테스트를 위한 메서드
    public static void reset() {
        isFind = false;
        answer = -1;
    }
}