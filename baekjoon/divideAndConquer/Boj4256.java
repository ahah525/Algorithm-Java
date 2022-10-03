package baekjoon.divideAndConquer;

import java.io.*;
import java.util.StringTokenizer;

public class Boj4256 {
    private static int[] preorder;
    private static int[] inorder;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());    // 테스트 케이스 수
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());  // 노드 개수
            // 전위 순회 결과
            preorder = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                preorder[j] = Integer.parseInt(st.nextToken());
            }
            // 중위 순회 결과
            inorder = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                inorder[j] = Integer.parseInt(st.nextToken());
            }
            // 후위 순회 결과
            dfs(0, n - 1, 0, n - 1);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
    public static void dfs(int ps, int pe, int is, int ie) throws IOException {
        if(ps > pe) {
            return;
        }

        int root = preorder[ps];
        int ir = 0;  // 중위순회 배열에서 루트 인덱스
        for(int i = is; i <= ie; i++) {
            if(root == inorder[i]) {
                ir = i;
                break;
            }
        }
        int size = ir - is; // 왼쪽 부분 배열 크기

        // 후위순회
        dfs(ps + 1, ps + size, is, ir - 1);    // 왼쪽
        dfs(ps + size + 1, pe, ir + 1, ie);      // 오른쪽
        bw.write(root + " ");
    }
}
