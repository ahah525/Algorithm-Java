package programmers.level2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [문제명] 시소 짝꿍
 * [풀이시간] (24분)
 * [한줄평]
 * 1_v1. (실패 - 2~15 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/152996">문제</a>
 */
class Solution152996 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public long solution(int[] weights) {
        long answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int w: weights) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        // System.out.println(map);
        List<Integer> keys = new ArrayList<>(map.keySet());
        int n = keys.size();
        for(int i = 0; i < n; i++) {
            int w1 = keys.get(i);
            if(map.get(w1) > 1 && isPossible(w1, w1)) answer++;
            for(int j = i + 1; j < n; j++) {
                if(isPossible(w1, keys.get(j))) answer++;
            }
        }
        return answer;
    }

    public boolean isPossible(int a, int b) {
        int[] dis = {2, 3, 4};
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(a * dis[i] == b * dis[j]) return true;
            }
        }
        return false;
    }
}