package baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 빗물
 * [풀이시간] / 1시간 5분
 * [한줄평]
 * / 감소했다가 증가할 때 빗물이 고일 수 있다는 건 알았는데, 막상 구현하기가 어려워서 결국 풀이를 참고해서 풀었다.
 * 1_v1. 구현(성공)
 * [풀이]
 * 2_v1. 구현(성공)
 * [풀이] i번째 블록 위에 고일 수 있는 빗물의 양 = Math.min(왼쪽 블록 높이 중 최댓값, 오른쪽 블록 높이 중 최댓값) - 현재 블록 높이
 * 2_v2. 구현(성공) -> 빠름
 * [풀이] ->, <- 방향으로 한 번씩 탐색해서 i번째 블록 기준 왼쪽, 오른쪽 범위의 최댓값을 구해서 배열에 저장함
 * @See <a href="https://www.acmicpc.net/problem/14719">문제</a>
 * @See <a href="https://youngest-programming.tistory.com/415">풀이 참고</a>
 */
public class Boj14719 {
    // 1_v1
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int h = Integer.parseInt(st.nextToken());   // 세로
//        int w = Integer.parseInt(st.nextToken());   // 가로
//        int res = 0;    // 고인 빗물 총량
//        st = new StringTokenizer(br.readLine());
//        // 블록이 쌓이 높이
//        int[] arr = new int[w];
//        for (int i = 0; i < w; i++) {
//            arr[i] = Integer.parseInt(st.nextToken());
//        }
//        int s = w, e = 0;
//        int maxP = -1;   // 최대 높이를 가진 블록 위치
//        int maxH = 0;   // 최대 높이
//        // 첫번째 시작 값 찾기
//        for (int i = 0; i < w; i++) {
//            if (arr[i] != 0) {
//                s = i;
//                break;
//            }
//        }
//
//        //
//        int i = s;
//        while (i < w) {
//            // 1. start값 이상의 값이 나오거나
//            // 2. start 보다 작은 값 중에서 더 큰 값 나오면
//            if (arr[s] <= arr[i] || (arr[s] > arr[i] && maxH < arr[i])) {
//                maxP = i;
//                maxH = arr[i];
//            }
//            // 마지막 블록 검사했는데 s의 값보다 작으면 지금까지 나온 것중 최댓값 위치로 이동
//            if (arr[s] <= arr[i]) {
//                // 빗물 더해주기
//                for (int j = s + 1; j <= maxP - 1; j++) {
//                    res += arr[s] - arr[j];
//                }
//                s = maxP;
//                maxP = -1;
//                maxH = 0;
//                i = s;
//            } else if (i == w - 1 && maxH != 0) {
//                // 빗물 더해주기
//                for (int j = s + 1; j <= maxP - 1; j++) {
//                    res += arr[maxP] - arr[j];
//                }
//                s = maxP;
//                maxP = -1;
//                maxH = 0;
//                i = s;
//            }
//            i++;
//        }
//        System.out.println(res);
//    }

    // 2_v1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());   // 세로
        int w = Integer.parseInt(st.nextToken());   // 가로
        int[] arr = new int[w];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 1. 각 블록 위에 고이는 빗물의 양 계산(0, w-1 번째 블록은 양끝이기 때문에 절대로 물이 고일 수가 없으므로 제외)
        int sum = 0;
        for(int i = 1; i < w - 1; i++) {
            // 2. i번째 기준 왼쪽, 오른쪽 범위의 최댓값 구하기
            int cur = arr[i];
            int maxL = 0;
            int maxR = 0;
            // 3. [0, i) 범위 중 최댓값
            for(int j = 0; j < i; j++) {
                maxL = Math.max(maxL, arr[j]);
            }
            // 4. [i+1, w) 범위 중 최댓값
            for(int j = i + 1; j < w; j++) {
                maxR = Math.max(maxR, arr[j]);
            }
            // 5. i번째 블록 위에 고이는 빗물의 양 계산
            int v = Math.min(maxL, maxR) - cur;
            // 6. 빗물의 양이 양수일 때만 누적
            if(v > 0) sum += v;
        }
        System.out.println(sum);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());   // 세로
        int w = Integer.parseInt(st.nextToken());   // 가로
        int[] arr = new int[w];
        st = new StringTokenizer(br.readLine());
        int[] maxL = new int[w];
        int[] maxR = new int[w];
        for(int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 1. i번째 블록 왼쪽 범위의 최댓값 저장
        int max = 0;
        for(int i = 1; i < w - 1; i++) {
            maxL[i] = Math.max(max, arr[i - 1]);
        }
        // 2. i번째 블록 오른쪽 범위의 최댓값 저장
        max = 0;
        for(int i = w - 2; i > 0; i--) {
            maxR[i] = Math.max(max, arr[i + 1]);
        }
        // 3. 각 블록 위에 고이는 빗물의 양 계산(0, w-1 번째 블록은 양끝이기 때문에 절대로 물이 고일 수가 없으므로 제외)
        int sum = 0;
        for(int i = 1; i < w - 1; i++) {
            // 4. i번째 블록 위에 고이는 빗물의 양 계산
            int v = Math.min(maxL[i], maxR[i]) - arr[i];
            // 5. 빗물의 양이 양수일 때만 누적
            if(v > 0) sum += v;
        }
        System.out.println(sum);
    }
}
