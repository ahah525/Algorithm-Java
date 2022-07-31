package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1차원 배열 풀이
public class Boj12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());    // 물품 수
        int k = Integer.parseInt(st.nextToken());    // 버틸수 있는 무게
        int a[][] = new int[n + 1][2];  // a[i]: i번째 물건의 무게, 가치
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i][0] = Integer.parseInt(st.nextToken());   // 무게
            a[i][1] = Integer.parseInt(st.nextToken());   // 가치
        }
        int[] d = new int[k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = k; j >= a[i][0]; j--) {
                d[j] = Math.max(d[j], a[i][1] + d[j - a[i][0]]);
            }
        }
        System.out.println(d[k]);
    }
}

// 2차원 배열 풀이
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());    // 물품 수
//        int k = Integer.parseInt(st.nextToken());    // 버틸수 있는 무게
//        int a[][] = new int[n + 1][2];  // a[i]: i번째 물건의 무게, 가치
//        for (int i = 1; i <= n; i++) {
//            st = new StringTokenizer(br.readLine());
//            a[i][0] = Integer.parseInt(st.nextToken());   // 무게
//            a[i][1] = Integer.parseInt(st.nextToken());   // 가치
//        }
//        int[][] d = new int[n + 1][k + 1];
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= k; j++) {
//                if(j >= a[i][0])
//                    d[i][j] = Math.max(d[i - 1][j], a[i][1] + d[i - 1][j - a[i][0]]);
//                else{
//                    d[i][j] = d[i - 1][j];
//                }
//            }
//        }
//        System.out.println(d[n][k]);
//    }
//}

