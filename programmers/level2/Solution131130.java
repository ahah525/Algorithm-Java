package programmers.level2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [문제명] 혼자 놀기의 달인
 * [풀이시간] (16분)
 * [한줄평]
 * 1_v1. (실패 - 2 런타임 에러)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/131130">문제</a>
 */
class Solution131130 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public int solution(int[] cards) {
        int n = cards.length;
        boolean[] visited = new boolean[n + 1];
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            int start = i;
            int cnt = 0;
            while(!visited[start]) {
                visited[start] = true;
                start = cards[start] - 1;
                cnt++;
            }
            if(cnt != 0) list.add(cnt);
        }
        Collections.sort(list, Collections.reverseOrder());
        return list.get(0) * list.get(1);
    }
}