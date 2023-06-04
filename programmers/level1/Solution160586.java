package programmers.level1;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 대충 만든 자판
 * [풀이시간] 10분 / 11분
 * [한줄평] 아주 쉽게 풀 수 있는 문제였다. / 두번째 풀때는 Map 대신 1차원 배열에 값을 저장해 풀었다.
 * 1_v1. HashMap(성공)
 * 2_v1. 구현(성공) -> 빠름
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/160586">문제</a>
 */
class Solution160586 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    /**
     * @param keymap 1번 키부터 차례대로 할당된 문자들이 순서대로 담긴 문자열배열
     * @param targets 입력하려는 문자열들이 담긴 문자열 배열
     * @return 각 문자열을 작성하기 위해 키를 최소 몇 번씩 눌러야 하는지 순서대로 배열에 담아 return
     */
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];

        Map<Character, Integer> map = new HashMap<>();
        for(String key : keymap) {
            int i = 1;
            for(char c : key.toCharArray()) {
                int n = map.getOrDefault(c, 101);
                map.put(c, Math.min(n, i));
                i++;
            }
        }
        // System.out.println(map);
        for(int i = 0; i < targets.length; i++) {
            int cnt = 0;
            for(char c : targets[i].toCharArray()) {
                if(!map.containsKey(c)) {
                    cnt = -1;
                    break;
                }
                cnt += map.get(c);
            }
            answer[i] = cnt;
        }
        return answer;
    }

    // 2_v1
    int[] alpha;
    int INF = Integer.MAX_VALUE;
    public int[] solution2(String[] keymap, String[] targets) {
        alpha = new int[26];
        // 1. 최댓값으로 초기화
        Arrays.fill(alpha, INF);
        // 2. 해당 알파벳을 작성하기 위해 눌러야하는 최소 횟수 저장
        for(String key : keymap) {
            for(int i = 0; i < key.length(); i++) {
                if(alpha[key.charAt(i) - 'A'] > i)
                    alpha[key.charAt(i) - 'A'] = i + 1;
            }
        }
        // 3. 각 문자열 계산
        int[] answer = new int[targets.length];
        for(int i = 0; i < targets.length; i++) {
            answer[i] = getCnt(targets[i]);
        }
        return answer;
    }

    // s를 작성하기 위해 키를 눌러야하는 최소 횟수
    public int getCnt(String s) {
        int sum = 0;
        for(char c : s.toCharArray()) {
            if(alpha[c - 'A'] == INF) return -1;
            sum += alpha[c - 'A'];
        }
        return sum;
    }
}