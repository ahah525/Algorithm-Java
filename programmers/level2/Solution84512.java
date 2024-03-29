package programmers.level2;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 모음사전
 * [풀이시간] / 34분, 11분 / 25분 / 13분
 * [한줄평] DFS 로 풀어야겠다고 빨리 떠올려서 쉽게 풀 수 있었던 문제였다. 수학적으로 규칙을 찾아서 풀면 시간단축을 할 수 있기 떄문에 알아두면 좋을 것 같다. / 시간 단축을 위해 수학으로 풀려고 하다보니 좀 오래걸렸다.
 * / 3번째 풀다보니 확실히 푸는데 속도가 빨라졌다.
 * 1_v1. DFS(성공)
 * >> answer 을 0이 아닌 -1 로 초기화한 이유
 * - 최초에 dfs 가 호출될 때 즉, depth = 0 일 때 answer 값이 0이어야 하기 때문이다.
 * - 만약 0으로 초기화했다면 dfs(0, ,) 이 호출됬을 때 이미 answer 값이 1으로 시작하기 때문에 실제 값보다 1 크게 나오는 문제가 생긴다.
 * 1_v2. 수학(성공)
 * 2_v1. 수학(성공) -> 추천(더 빠름)
 * [접근법] 각 자리수의 문자가 바뀔때마다 얼마의 값이 증가하는지 규칙을 찾아 수학적으로 접근
 * - 1번째 자리수의 문자가 바뀔때 증가폭 = 5^4 + ... + 5^0 = 781
 * - 2번째 자리수의 문자가 바뀔때 증가폭 = 5^3 + ... + 5^0 = 156
 * - 3번째 자리수의 문자가 바뀔때 증가폭 = 5^2 + ... + 5^0 = 31
 * - 4번째 자리수의 문자가 바뀔때 증가폭 = 5^1 + 5^0 = 6
 * - 5번째 자리수의 문자가 바뀔때 증가폭 = 5^0 = 1
 * [예시]
 * EIO 의 비교대상 = AAA(원래 문자를 모두 A로 바꾼 것)
 * EIO -> AAA 의 각 자리수의 차 = 1, 2, 3
 * EIO 순서 = AAA 의 순서 + (EIO, AAA)의 각 자리수 문자의 차이의 합
 * 2_v2. DFS(성공)
 * 3_v1. 수학(성공)
 * 4_v1. DFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/84512">문제</a>
 */
class Solution84512 {
    public static void main(String[] args) {
        // 6
        System.out.println(solution2("AAAAE"));

        // 10
        System.out.println(solution2("AAAE"));

        // 1563
        System.out.println(solution2("I"));

        // 1189
        System.out.println(solution2("EIO"));
    }

    // 1_v1, 2_v2
    private static boolean isFind;
    private static int answer;
    private static char[] alpha ={'A', 'E', 'I', 'O', 'U'};

    /**
     * @param word 단어(길이는 1 이상 5 이하, 알파벳 대문자 'A', 'E', 'I', 'O', 'U' 로만 이루어짐)
     * @return 이 단어가 사전에서 몇 번째 단어인지
     */
    public static int solution1(String word) {
        isFind = false;
        answer = -1;

        dfs(0, "", word);

        return answer;
    }

    public static void dfs(int depth, String path, String word) {
        // 1. path 길이가 6이거나 이미 word 를 찾았으면 리턴
        if(depth == 6 || isFind) return;
        // 2. 카운트
        answer++;
        // 3. path 가 word 이면 찾음 표시, 리턴
        if(path.equals(word)) {
            isFind = true;
            return;
        }
        // A, E, I, O, U
        for(char c : alpha) {
            dfs(depth + 1, path + c, word);
        }
    }

    // 1_v2, 2_v1, 3_v1
    public static int solution2(String word) {
        // 1. arr[i] = 단어의 i번째 문자가 바뀔 때 증가폭 계산
        int[] arr = {781, 156, 31, 6, 1};
        // 2. 'A' 와 ? 문자의 차이
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('E', 1);
        map.put('I', 2);
        map.put('O', 3);
        map.put('U', 4);
        // 3. word 길이로 answer 값 초기화
        int answer = word.length();
        // 4. word 의 각 자리수의 차 구하기
        for(int i = 0; i < word.length(); i++)
            answer += arr[i] * map.get(word.charAt(i));
        return answer;
    }
}