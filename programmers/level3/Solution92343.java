package programmers.level3;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 양과 늑대
 * [풀이시간] 1시간 30분
 * [한줄평] 처음보는 유형의 DFS 문제였고 풀이를 보고도 이해하는데 오래걸렸다. 꼭 다시 풀어봐야할 문제다.
 * 1_v1. DFS(성공)
 * [풀이] 현재 노드에서 갈 수 있는 노드 리스트 = (현재 노드룰 제외하고)부모 노드에서 갈 수 있는 노드 리스트 + 현재 노드의 자식 노드 리스트
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/92343">문제</a>
 * @See <a href="https://jangcenter.tistory.com/120">풀이 참고1</a>
 * @See <a href="https://g-egg.tistory.com/76">풀이 참고2</a>
 */
class Solution92343 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    List<Integer>[] graph; // graph[i]: i번 노드의 자식 노드 리스트
    int answer;
    public int solution(int[] info, int[][] edges) {
        answer = 0;
        // 인접그래프 초기화
        int n = info.length;
        graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] e : edges) {
            graph[e[0]].add(e[1]);
        }
        dfs(0, 0, 0, new ArrayList<>(), info);
        return answer;
    }

    /**
     * @param node 현재 노드
     * @param sheep 양 개수
     * @param wolf 늑대 개수
     * @param list 현재 노드의 부모 노드에서 갈 수 있는 노드 리스트
     * @param info
     */
    public void dfs(int node, int sheep, int wolf, List<Integer> list, int[] info) {
        // 1. 양, 늑대 개수 세기
        if(info[node] == 0) sheep++;
        else wolf++;
        // 2. 양 <= 늑대, 유효하지 않으므로 리턴
        if(sheep <= wolf) return;
        // 3. 양 > 늑대, 양 최댓값 갱신
        answer = Math.max(answer, sheep);
        // 4. 현재 노드에서 갈 수 있는 노드 리스트 구하기
        // 4.1. 부모 노드에서 갈 수 있는 노드 추가
        List<Integer> next = new ArrayList<>(list);
        // 4.2. 현재 본인 노드 제외
        next.remove(Integer.valueOf(node));
        // 4.3. 현재 노드의 자식 노드 추가
        if(graph[node].size() > 0) {
            next.addAll(graph[node]);
        }
        // 5. 현재 노드에서 갈 수 있는 노드 방문
        for(int num : next) {
            dfs(num, sheep, wolf, next, info);
        }
    }
}