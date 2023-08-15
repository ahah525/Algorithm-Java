package programmers.level1;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 달리기 경주
 * [풀이시간] 15분 / 16분 / 12분
 * [한줄평] 매번 처음부터 탐색을 하면 시간초과가 날 것이 분명해서 HashMap 으로 등수를 관리하여 풀었다.
 * / 쉬운 문제였는데, 등수를 기록할 때 엉뚱한 배열을 사용해서 오래걸렸다.
 * / 더 이상 풀어볼 필요 없는 쉬운 문제였다.
 * 1_v1. HashMap(성공)
 * [풀이] HashMap에 (이름, 등수)를 저장, String[]에 등수별 이름을 저장
 * 2_v1. HashMap(성공)
 * [풀이] 1_v1 동일
 * 3_v1. HashMap(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/178871">문제</a>
 */
class Solution178871 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1, 3_v1
    /**
     * @param players 선수들의 이름이 1등부터 현재 등수 순서대로 담긴 문자열 배열
     * @param callings 해설진이 부른 이름을 담은 문자열 배열
     * @return 경주가 끝났을 때 선수들의 이름을 1등부터 등수 순서대로 배열에 담아 return
     */
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        // 1. (이름, 등수) 초기화
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < players.length; i++) {
            answer[i] = players[i];
            map.put(players[i], i);
        }
        // 등수 계산
        for(String call : callings) {
            int idx = map.get(call);    // 호출 선수의 현재 등수
            String prev = answer[idx - 1]; // 호출 선수의 앞 선수 이름
            // 2. 현재 선수의 순서를 1개 앞으로
            answer[idx - 1] = call;
            map.put(call, idx - 1);
            // 3. 앞 선수의 순서를 1개 뒤로
            answer[idx] = prev;
            map.put(prev, idx);
        }
        return answer;
    }
}