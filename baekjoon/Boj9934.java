package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj9934 {
    static int k;   // 트리의 깊이
    static int[] graph; // 빌딩 방문 순서
    static ArrayList<Integer>[] tree;   // 트리 정보
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n =  (int)Math.pow(2, k) - 1;   // 노드 개수
        graph = new int[n];
        tree = new ArrayList[k];
        for(int i = 0; i < k; i++) {
            tree[i] = new ArrayList<>();
        }
        // 빌딩 방문 순서 저장
        for(int i = 0; i < n; i++) {
            graph[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0, n - 1);
        // 트리 출력
        for(int i = 0; i < k; i++) {
            for(int v : tree[i])
                bw.write(v + " ");
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static void dfs(int depth, int start, int end) {
        // 엇갈리면 종료
        if(start > end) return;
        // 중앙값 넣기
        int mid = (start + end) / 2;
        tree[depth].add(graph[mid]);
        // 중앙 기준 왼쪽, 오른쪽에 대해 dfs 수행
        dfs(depth + 1, start, mid - 1); // 왼쪽
        dfs(depth + 1, mid + 1, end);   // 오른쪽
    }
}
