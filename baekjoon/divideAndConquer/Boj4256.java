package baekjoon.divideAndConquer;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 1 ~ n 노드로 이루어진 이진트리의 전위, 중위 순회 결과 -> 후위 순회 결과 출력
 *
 * 1
 * 4
 * 3 2 1 4
 * 2 3 4 1
 * --------
 * 2 4 1 3
 */
public class Boj4256 {
    private static int[] preorder;  // 전위 순회 결과
    private static int[] inorder;   // 중위 순회 결과
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

    // ps, pe = preorder 부분 배열의 (시작 인덱스, 끝 인덱스)
    // is, ie = inorder 부분 배열의 (시작 인덱스, 끝 인덱스)
    public static void dfs(int ps, int pe, int is, int ie) throws IOException {
        System.out.printf("(%d, %d, %d, %d)\n", ps, pe, is, ie);
        // 엇갈리면 바로 종료
        if(ps > pe) {
            return;
        }

        // 1. preorder 에서 루트 노드를 찾는다
        int root = preorder[ps];
        // 2. inorder 에서 루트 노드를 기준으로 (왼쪽 부분 배열, 오른쪽 부분배열) 로 분리한다
        int ir = 0; // 루트 노드 인덱스
        for(int i = is; i <= ie; i++) {
            if(root == inorder[i]) {
                ir = i;
                break;
            }
        }
        int size = ir - is; // 왼쪽 부분 배열 크기

        // 3. 분리된 부분 배열에 대해 재귀
        dfs(ps + 1, ps + size, is, ir - 1);    // 왼쪽
        dfs(ps + size + 1, pe, ir + 1, ie);      // 오른쪽
        // 후위 순회이기 때문에 마지막에 출력
        bw.write(root + " ");
    }
}
