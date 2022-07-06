package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Boj1991 {
    static StringBuilder sb = new StringBuilder();
    static char[][] graph;  // 트리 정보
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        graph = new char[n][2]; // 트리 정보

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char data = st.nextToken().charAt(0);   // 현재 노드
            char left = st.nextToken().charAt(0);   // 왼쪽 자식
            char right = st.nextToken().charAt(0);  // 오른쪽 자식
            // 왼쪽 자식, 오른쪽 자식 값 넣기
            graph[data - 'A'][0] = left;
            graph[data - 'A'][1] = right;
        }
        // 순회 결과 구하고 출력
        preOrder('A');
        sb.append("\n");
        inOrder('A');
        sb.append("\n");
        postOrder('A');

        System.out.println(sb);
    }
    // 전위 순회
    static void preOrder(char v) {
        // root -> L -> R
        sb.append(v);
        char left = graph[v - 'A'][0];// 왼쪽 자식
        char right = graph[v - 'A'][1];// 오른쪽 자식
        if(left != '.')
            preOrder(left);
        if(right != '.')
            preOrder(right);
    }
    // 중위 순회
    static void inOrder(char v) {
        // L -> root -> R
        char left = graph[v - 'A'][0];// 왼쪽 자식
        char right = graph[v - 'A'][1];// 오른쪽 자식
        if(left != '.')
            inOrder(left);
        sb.append(v);
        if(right != '.')
            inOrder(right);
    }
    // 후위 순회
    static void postOrder(char v) {
        // R -> L -> root
        char left = graph[v - 'A'][0];// 왼쪽 자식
        char right = graph[v - 'A'][1];// 오른쪽 자식
        if(left != '.')
            postOrder(left);
        if(right != '.')
            postOrder(right);
        sb.append(v);
    }
}

