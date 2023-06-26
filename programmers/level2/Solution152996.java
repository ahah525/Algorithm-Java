package programmers.level2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [문제명] 시소 짝꿍
 * [풀이시간] 34분(24분+10분) / 24분(22분+2분)
 * [한줄평] 처음에는 전체에서 2명씩 뽑아서 경우의 수를 구하려다가 시간 초과가 날 것 같아서 Map으로 풀었던 문제다.
 * 1_v1. HashMap(실패 - 2~15 실패)
 * 1_v2. HashMap(성공)
 * [반례] [100, 100, 100] >> 3
 * [해결] 몸무게가 같은 사람들의 집단에서도 2명을 뽑는 경우의 수를 센다.
 * 2_v1. HashMap(실패 - 12~15 실패)
 * 2_v2. HashMap(성공)
 * [해결] 전체 경우의 수(answer)은 long 타입이기 때문에 더해줄 때 long 으로 형변환 해주어야 한다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/152996">문제</a>
 */
class Solution152996 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2, 2_v2
    /**
     * @param weights 사람들의 몸무게 목록
     * @return 시소 짝꿍이 몇 쌍 존재하는지
     */
    public long solution(int[] weights) {
        long answer = 0;
        // 1. (몸무게, 사람수) 초기화
        Map<Integer, Integer> map = new HashMap<>();
        for(int w : weights) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        // 2. 몸무게 리스트 초기화(중복X)
        List<Integer> list = new ArrayList<>(map.keySet());
        // 3. n개의 몸무게 리스트에서 순서 상관없이 2개 뽑기
        for(int i = 0; i < list.size(); i++) {
            int a = list.get(i);
            // 4. 몸무게가 a인 집단에서 2개 뽑기
            int cnt = map.get(a);
            if(cnt > 1) {
                answer += (long) cnt * (cnt - 1) / 2;
            }
            // 5. 몸무게가 a, b인 집단에서 1명씩 뽑기
            for(int j = i + 1; j < list.size(); j++) {
                int b = list.get(j);
                if(isSame(a, b)) {
                    answer += (long) map.get(a) * map.get(b);
                }
            }
        }
        return answer;
    }

    // 몸무게 a, b 가 짝꿍이 될 수 있는지 여부
    public boolean isSame(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        if(min * 4 == max * 2) return true;
        if(min * 4 == max * 3) return true;
        if(min * 3 == max * 2) return true;
        return false;
    }
}