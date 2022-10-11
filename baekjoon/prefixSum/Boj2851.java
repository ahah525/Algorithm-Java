package baekjoon.prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 입력
 * 각 버섯의 점수(100 보다 작거나 같은 양의 정수) 10개
 * --------------------------------------------
 * 출력
 * 마리오가 받는 점수(100에 가까운 수가 2개라면 큰 값 선택)
 * -> 처음부터 버섯을 연속 x개 선택했을 때, 누적합 중 100에 가장 가까운 수
 * -> |누적합 - 100| 최소가 되는 누적합 구하기
 * -> 버섯의 점수가 100이하의 양의 정수이기 때문에, 누적합은 계속 증가하므로 최솟값보다 값이 커질 때 바로 braek
 * --------------------------------------------
 *
 */
public class Boj2851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 10개의 버섯 점수 입력받기
        int n = 10;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int minAbs = Integer.MAX_VALUE; // |누적합 - 100| 최솟값
        int res = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            int abs = Math.abs(sum - 100);
            if(abs <= minAbs) {
                minAbs = abs;
                res = sum;
            } else {
                break;
            }
        }
        System.out.println(res);
    }
}
