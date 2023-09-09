package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [문제명] 네트워크 연결
 * [풀이시간] 30분
 * [한줄평] 크루스칼 알고리즘을 몰라서 풀이를 보고 풀었고, 완전 기초 문제였다.
 * 1_v1. 그래프/크루스칼(성공)
 * [풀이] 크루스칼 알고리즘
 * @See <a href="https://www.acmicpc.net/problem/">문제</a>
 */
class Boj1922 {
    // 1_v1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 노드 수
        int m = Integer.parseInt(br.readLine());    // 간선 수
        int[][] edge = new int[m][3];   // 간선 정보
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            edge[i][0] = Integer.parseInt(st.nextToken());
            edge[i][1] = Integer.parseInt(st.nextToken());
            edge[i][2] = Integer.parseInt(st.nextToken());
        }
        System.out.println(kruskal(n, edge));
    }

    static int[] parent;
    public static int kruskal(int n, int[][] edge) {
        int cost = 0;
        parent = new int[n + 1];
        // 1. 간선 비용 오름차순 정렬
        Arrays.sort(edge, (o1, o2) -> o1[2] - o2[2]);
        // 2. 각 노드의 부모 노드를 자기 자신으로 초기화
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        // 3. 사이클을 발생시키지 않는 간선을 선택
        for(int[] e : edge) {
            if(findParent(e[0]) == findParent(e[1])) continue;
            // 2. 두 노드의 루트 노드가 다르면, union 연산으로 합침
            union(e[0], e[1]);
            cost += e[2];
        }
        System.out.println(Arrays.toString(parent));
        return cost;
    }

    // (a, b) 간선을 포함시키기
    public static void union(int a, int b) {
        // 1. 두 노드의 루트 노드 구하기
        a = findParent(a);
        b = findParent(b);
        // 2. 더 작은 노드를 루트 노드로 설정
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    // node의 루트 노드 조회
    public static int findParent(int node) {
        // 1. 부모가 자기 자신이면 바로 리턴
        if(parent[node] == node) return node;
        return findParent(parent[node]);
    }
}