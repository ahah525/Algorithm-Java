package baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj2110 {
    private static int n, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 집 개수
        c = Integer.parseInt(st.nextToken());   // 공유기 개수
        List<Integer> arr = new ArrayList<>(); // 집의 좌표
        for(int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(arr);  // 오름차순 정렬

        // start = 1, end = 집 간 최대 거리(첫번째 집 ~ 마지막 집)
        int res = binarySearch(arr, 1, arr.get(n - 1) - arr.get(0));
        System.out.println(res);
    }
    public static int binarySearch(List<Integer> arr, int start, int end) {
        int res = 0;
        while (start <= end) {
            int mid = (int) ((start + end) / 2);
            int prev = 0;  // 이전 집 좌표
            int cnt = 1;    // 설치한 공유기 개수(0번째 집 선택)
            for(int i = 1; i < n; i++) {
                // 현재 집과 이전 집의 거리가 mid 이상인 집의 개수 세기
                if (arr.get(i) - arr.get(prev) >= mid) {
                    cnt++;
                    prev = i;
                }
            }

            if(cnt >= c) {
                // 최소 거리가 mid 이상으로 설치할 수 있는 공유기가 c 이상이면 mid를 늘려야 함
                start = mid + 1;
                res = mid;
            } else {
                // 최소 거리가 mid 이상으로 설치할 수 있는 공유기가 c 미만이면 mid를 줄여야 함
                end = mid - 1;
            }
        }
        return res;
    }
}
