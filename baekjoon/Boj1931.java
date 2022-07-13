package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 회의 수
        StringTokenizer st;

        int[][] arr = new int[n][2];
        int res = 0;    // 최대 회의 개수
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());   // 시작시각
            arr[i][1] = Integer.parseInt(st.nextToken());   // 끝시각
        }
        // 끝 시각 -> 시작 시각 오름차순 정렬
        Arrays.sort(arr, (o1, o2) -> {
            // 끝시각이 같으면 시작값으로 비교
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        int i = 0;
        int prevEnd = -1;   // 이전 회의 끝난 시각
        while (i < n) {
            // 현재 회의 시작 시각이 이전 끝난 시각 이후면 개수 1증가
            if(prevEnd <= arr[i][0]) {
                prevEnd = arr[i][1];
                res++;
            }
            i++;
        }
        System.out.println(res);
    }
}
