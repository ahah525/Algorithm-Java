package programmers.level2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [문제명] 혼자 놀기의 달인
 * [풀이시간] 18분(16분 + 2분)
 * [한줄평] 이 문제는 숫자를 뽑는 순서는 중요하지 않고 단순히 숫자를 그룹핑하기만 하면 쉽게 풀 수 있었다.
 * 1_v1. 구현(실패 - 2 런타임 에러)
 * 1_v2. 구현(성공)
 * [반례] 1번 상자 그룹을 제외하고 남는 상자가 없으면 이때 획득하는 점수는 0점 / [2, 1] => 0
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
        boolean[] visited = new boolean[n + 1]; // 상자 선택 여부
        // 1. 상자 그룹핑 하기
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
        // 2. 그룹이 1개 이면 0 리턴
        if(list.size() == 1) return 0;
        // 3. 내림차순 정렬 후 가장 큰 수와 두번째로 큰 수의 곱 반환
        Collections.sort(list, Collections.reverseOrder());
        return list.get(0) * list.get(1);
    }
}