package programmers.level2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [문제명] 시소 짝꿍
 * [풀이시간] 34분(24분+10분)
 * [한줄평] 처음에는 전체에서 2명씩 뽑아서 경우의 수를 구하려다가 시간 초과가 날 것 같아서 Map으로 풀었던 문제다.
 * 1_v1. HashMap(실패 - 2~15 실패)
 * - 몸무게가 같은 사람들의 수를 생각하지 않고 몸무게 쌍으로만 경우의 수를 셈
 * 1_v2. HashMap(성공)
 * [반례] [100, 100, 100] >> 3
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/152996">문제</a>
 */
class Solution152996 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    /**
     * @param weights 사람들의 몸무게 목록
     * @return 시소 짝꿍이 몇 쌍 존재하는지
     */
    public long solution(int[] weights) {
        // 1. (몸무게, 사람수) 구하기
        Map<Integer, Integer> map = new HashMap<>();
        for(int w: weights) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        // 2. 몸무게 목록에서 2명씩 뽑는 경우의 수 계산
        List<Integer> keys = new ArrayList<>(map.keySet());
        int n = keys.size();
        long answer = 0;
        for(int i = 0; i < n; i++) {
            int w1 = keys.get(i);
            int cnt = map.get(w1);
            // 3. 몸무게가 w1 인 그룹에서 2명 뽑기
            if(cnt > 1 && isPossible(w1, w1)) {
                answer += (long) cnt * (cnt - 1) / 2;
            }
            // 4. 몸무게가 w1, w2인 그룹에서 각 1명씩 뽑기
            for(int j = i + 1; j < n; j++) {
                int w2 = keys.get(j);
                if(isPossible(w1, w2)) {
                    answer += (long) map.get(w1) * map.get(w2);
                }
            }
        }
        return answer;
    }

    // 몸무게 a, b 가 짝꿍이 될 수 있는지 여부
    public boolean isPossible(int a, int b) {
        if(a == b) return true;
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        if(max * 2 == min * 3) return true;
        if(max * 2 == min * 4) return true;
        if(max * 3 == min * 4) return true;
        return false;
    }
}