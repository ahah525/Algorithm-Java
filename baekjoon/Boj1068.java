package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj1068 {
    static ArrayList<Integer>[] graph;     // 인접 리스트
    static boolean[] visited;               // 방문 여부
    static int res = 0; // 단말 노드 개수
    static int remove = 0;  // 삭제할 노드 번호
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int root = 0;  // 루트 노드
        int n = Integer.parseInt(br.readLine());    // 노드의 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        remove = Integer.parseInt(br.readLine());
        graph = new ArrayList[n];
        visited = new boolean[n];
        // 인접 리스트 초기화
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < n; i++) {
            int v = Integer.parseInt(st.nextToken());   // i 노드의 부모 노드
            // 부모가 없다면 해당 노드가 시작노드
            if(v == -1) {
                root = i;   // 루트 노드 기록
            } else {
                // 부모가 있다면 트리 정보 저장
                graph[v].add(i);    // v의 자식은 i
            }
        }
        // 삭제할 노드가 루트 노드이면 단말노드는 항상 0개
        if(remove != root)
            dfs(root);
        System.out.println(res);
    }
    public static void dfs(int v) {
        // 현재 노드 방문처리
        visited[v] = true;
        // 자식 노드가 없거나 1개 있는데 삭제할 노드라면 단말노드 개수 1증가
        if(graph[v].size() == 0 || (graph[v].size() == 1 && graph[v].contains(remove))){
            res++;
            return;
        }
        // 현재 노드와 연결된 다른 노드 재귀 방문
        for(int i : graph[v]) {
            // 해당 노드를 방문한적이 없다면 dfs
            if(!visited[i]) {
                // 삭제해야할 노드면 방문하지 않음
                if(i == remove) continue;
                dfs(i);
            }
        }
    }
}
