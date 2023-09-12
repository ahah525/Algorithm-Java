package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [문제명] 도시 분할 계획
 * [풀이시간] 17분
 * [한줄평] 크루스칼 알고리즘을 구현할 수 있으면 쉽게 풀 수 있는 문제였다.
 * 1_v1. 그래프/크루스칼(성공)
 * [풀이] 크루스칼 알고리즘으로 최소신장트리를 구하고 그 중 가중치가 가장 큰 간선을 끊어 분리하는 것이 가중치 합의 최솟값이 된다.
 * @See <a href="https://www.acmicpc.net/problem/1647">문제</a>
 */
class Boj1647 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 노드 개수
        int m = Integer.parseInt(st.nextToken());   // 간선 개수
        int[][] edge = new int[m][3];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edge[i][0] = Integer.parseInt(st.nextToken());
            edge[i][1] = Integer.parseInt(st.nextToken());
            edge[i][2] = Integer.parseInt(st.nextToken());
        }
        // 1. 가중치 오름차순 정렬
        Arrays.sort(edge, (o1, o2) -> o1[2] - o2[2]);
        // 2. 부모 노드를 본인으로 갱신
        parent = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        int sum = 0;    // 선택한 간선의 가중치의 합
        int max = 0;    // 최소신장트리에서 간선의 가중치의 최댓값
        for(int[] e : edge) {
            // 3. 간선을 선택했을 때 사이클이 만들어지면 패스
            if(findParent(e[0]) == findParent(e[1])) continue;
            // 4. 간선 선택
            union(e[0], e[1]);
            max = e[2];
            sum += e[2];
        }
        System.out.println(sum - max);
    }

    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        // 더작은 노드를 부모 노드로 설정
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static int findParent(int node) {
        if(parent[node] == node) return node;
        return findParent(parent[node]);
    }
}