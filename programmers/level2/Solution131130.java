package programmers.level2;


import java.util.*;

/**
 * [문제명] 혼자 놀기의 달인
 * [풀이시간] 18분(16분 + 2분) / 15분 / 16분
 * [한줄평] 이 문제는 숫자를 뽑는 순서는 중요하지 않고 단순히 숫자를 그룹핑하기만 하면 쉽게 풀 수 있었다.
 * / 반복문으로 푸는 것이 빠르긴 했지만 재귀로도 풀 수 있는 문제였다.
 * / 쉬운 문제였지만 다시 풀어봐도 좋을 문제다.
 * 1_v1. DFS(실패 - 2 런타임 에러)
 * 1_v2. DFS(성공) -> 빠름
 * [풀이] 반복문
 * [반례] 1번 상자 그룹을 제외하고 남는 상자가 없으면 이때 획득하는 점수는 0점 / [2, 1] => 0
 * 2_v1. DFS(성공)
 * [풀이] 재귀
 * 3_v1. DFS(성공)
 * [풀이] 2_v1 동일
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

    // 2_v1
    int cnt = 0;
    boolean[] visited;
    public int solution2(int[] cards) {
        // 1. 내림차순 정렬
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        visited = new boolean[cards.length];
        // 2. 아직 선택하지 않은 상자 dfs
        for(int i = 0; i < cards.length; i++) {
            if(!visited[i]) {
                cnt = 0;
                dfs(i, cards);
                pq.add(cnt);
            }
        }
        return (pq.size() < 2) ? 0 : pq.poll() * pq.poll();
    }

    public void dfs(int n, int[] cards) {
        // n번째 인덱스 선택했으면 종료
        if(visited[n]) return;
        cnt++;
        visited[n] = true;
        dfs(cards[n] - 1, cards);
    }

    // 3_v1
//    boolean[] visited;
//    Queue<Integer> pq;
//    public int solution(int[] cards) {
//        visited = new boolean[cards.length];
//        pq = new PriorityQueue<>(Collections.reverseOrder());
//        for(int i = 0; i < cards.length; i++) {
//            if(visited[i]) continue;
//            dfs(i, 0, cards);
//        }
//        // System.out.println(pq);
//        if(pq.size() < 2) return 0;
//        return pq.poll() * pq.poll();
//    }
//
//    public void dfs(int now, int cnt, int[] cards) {
//        if(visited[now]) {
//            pq.add(cnt);
//            return;
//        }
//        //
//        visited[now] = true;
//        dfs(cards[now] - 1, cnt + 1, cards);
//    }
}