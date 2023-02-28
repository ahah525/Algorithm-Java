package programmers.level3;


import java.util.Arrays;

/**
 * [문제명] 섬 연결하기
 * [풀이시간] 1시간 20분
 * [한줄평] 어떻게 접근해야할지 몰라서 풀이를 보고 이해했던 문제였다.
 * 1_v1. 크루스칼 알고리즘(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42861">문제</a>
 * @See <a href="https://maetdori.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%84%AC-%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0">풀이 참고</a>
 * @See <a href="https://velog.io/@qodlstjd12/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%84%AC-%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0-Java">풀이 참고</a>
 */
class Solution42861 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    int[] parent;

    /**
     * @param n 섬의 개수
     * @param costs n개의 섬 사이에 다리를 건설하는 비용
     * @return 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용
     */
    public int solution(int n, int[][] costs) {
        int answer = 0;
        // 1. 간선 비용 오름차순 정렬
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        // 2. 각 노드들의 부모 노드를 자기 자신으로 초기화
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        // 3. 현재 그래프에서 해당 간선을 선택했을 때 사이클이 발생하는지 확인
        for(int[] edge : costs) {
            // 4. 두 노드의 부모가 다르면, 사이클 발생X -> 선택
            if(findParent(edge[0]) != findParent(edge[1])) {
                union(edge[0], edge[1]);
                answer += edge[2];
            }
        }
        return answer;
    }

    // (a, b)를 잇는 간선을 그래프에 포함시키기
    public void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        // 두 노드 중 더 작은 노드를 부모로 설정
        if(a > b) parent[a] = b;
        else parent[b] = a;
    }

    // node 의 부모 노드 찾기
    public int findParent(int node) {
        // 1. 부모가 자기 자신이면 바로 리턴
        if(parent[node] == node) return node;
        // 2. 부모가 다른 노드면, 최상위 부모를 찾기
        return findParent(parent[node]);
    }
}