package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());   // 세로
        int w = Integer.parseInt(st.nextToken());   // 가로
        int res = 0;    // 고인 빗물 총량
        st = new StringTokenizer(br.readLine());
        // 블록이 쌓이 높이
        int[] arr = new int[w];
        for (int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int s = w, e = 0;
        int maxP = -1;   // 최대 높이를 가진 블록 위치
        int maxH = 0;   // 최대 높이
        // 첫번째 시작 값 찾기
        for (int i = 0; i < w; i++) {
            if (arr[i] != 0) {
                s = i;
                break;
            }
        }

        //
        int i = s;
        while (i < w) {
            // 1. start값 이상의 값이 나오거나
            // 2. start 보다 작은 값 중에서 더 큰 값 나오면
            if (arr[s] <= arr[i] || (arr[s] > arr[i] && maxH < arr[i])) {
                maxP = i;
                maxH = arr[i];
            }
            // 마지막 블록 검사했는데 s의 값보다 작으면 지금까지 나온 것중 최댓값 위치로 이동
            if (arr[s] <= arr[i]) {
                // 빗물 더해주기
                for (int j = s + 1; j <= maxP - 1; j++) {
                    res += arr[s] - arr[j];
                }
                s = maxP;
                maxP = -1;
                maxH = 0;
                i = s;
            } else if (i == w - 1 && maxH != 0) {
                // 빗물 더해주기
                for (int j = s + 1; j <= maxP - 1; j++) {
                    res += arr[maxP] - arr[j];
                }
                s = maxP;
                maxP = -1;
                maxH = 0;
                i = s;
            }
            i++;
        }
        System.out.println(res);
    }
}
